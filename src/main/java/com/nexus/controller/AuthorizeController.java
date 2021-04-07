package com.nexus.controller;

import com.nexus.pojo.AccessToken;
import com.nexus.pojo.GithubParams;
import com.nexus.pojo.GithubUser;
import com.nexus.service.UserService;
import com.nexus.utils.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Autowired
    private GithubParams githubParams;

    @Autowired
    UserService userService;

    // 返回一个页面，返回值用 String
    @GetMapping("/callback")
    public String callback(@RequestParam("code") String code,
                           @RequestParam("state") String state,
                           HttpSession httpsession) {
        AccessToken accessToken = new AccessToken();
        // githubParams实际上是获取配置文件中的client_id
        accessToken.setClient_id(githubParams.getClient_id());
        // 获取配置文件中的client_secret
        accessToken.setClient_secret(githubParams.getClient_secret());
        // 这一步在前端设置
        // get https://github.com/login/oauth/authorize 收到的code
        // 即授权github登录后收到的code
        accessToken.setCode(code);
        // 获取配置文件中的redirect_uri
        accessToken.setRedirect_uri(githubParams.getRedirect_uri());
        // 不可猜测的随机字符串。 它用于防止跨站请求伪造攻击。
        // 这个纯属自己get https://github.com/login/oauth/authorize 时设置
        accessToken.setState(state);
        // AccessToken(client_id=f87bf1b0fa17b11a6eb2, client_secret=e16079d77533066a876705063c51b9bd3edede1f, code=fb61d7bf5d064a0b5277, redirect_uri=http://localhost:8080/callback, state=1)--accessToken
//        System.out.println(accessToken+"--accessToken");

        // 获取access_token
        // b64cdbe92d07b486e1895a0d4b6913726cd3a255
        String access_token = githubProvider.getAccessToken(accessToken);
//        System.out.println(access_token);

        // 根据access_token获取用户信息
        GithubUser githubUser = githubProvider.getGithubUser(access_token);
//        System.out.println(githubUser.getName());
        // GithubUser(id=46276403, name=Nexus, bio=null, login=WeiDrogan)
        // System.out.println(githubUser);

        // 数据库中有github用户信息，结果为true。没有信息，结果为flase
        boolean isExistGithubUser = userService.selectByGithub_id(githubUser.getId());

        // id不为空，且不存在用户信息，则插入数据库。
        // 将github的用户信息插入数据库
            // isExistGithubUser的结果取反。
            // 结果为true，有信息，则不能插入到数据库。
            // 结果为flase，无信息，则插入到数据库
        if (githubUser.getId() != null && !isExistGithubUser) {
            userService.insertGitHubUserInfo(githubUser.getId(), githubUser.getLogin(), access_token);
            httpsession.setAttribute("githubUser", githubUser);
            // 这里采用重定向的方式，避免刷新授权页面，导致500错误。(type=Internal Server Error, status=500).
            return "redirect:index";
        } else if (githubUser.getId() != null){
            // id不为空，设置到缓存，返回首页。
            httpsession.setAttribute("githubUser", githubUser);
            // 这里采用重定向的方式，避免刷新授权页面，导致500错误。(type=Internal Server Error, status=500).
            return "redirect:index";
        } else {
            // id为空，返回登录页。
            return "/login";
        }
    }
}
