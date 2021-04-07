package com.nexus.service.impl;

import com.nexus.mapper.UserMapper;
import com.nexus.pojo.GithubUser;
import com.nexus.pojo.User;
import com.nexus.service.UserService;
import com.nexus.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${bbs.domain}")
    private String domain;

    // 这里是为了给UserController使用
    @Override
    public User selectByName(String username) {
        return userMapper.selectByName(username);
    }

    // 这里是为了给UserController使用
    @Override
    public User selectByEmail(String email) {
        return userMapper.selectByEmail(email);
    }

    @Override
    public Result register(String username, String password, String email) {

        String message = "";

        // 注册用户
        User user = new User();

        // 直接set一个uuid,不从controller处接收.
        user.setId(UUIDUtils.generateUUID());

        // 通过HtmlUtils.htmlEscape(name);把账号里的特殊符号进行转义
        // 有些人在恶意注册的时候，会使用诸如 <script>alert('papapa')</script> 这样的名称，
        // 会导致网页打开就弹出一个对话框。 在转义之后，就没有这个问题了。
        username = HtmlUtils.htmlEscape(username);
        user.setUsername(username);
        // 密码利用MD5加密
        user.setPassword(PassWordUtils.md5(password));
        user.setEmail(email);
        // 注册时set一个激活码，用UUID即可。不过这里的uuid和上面的可不是同一个uuid，值并不相等。
        user.setActivationCode(UUIDUtils.generateUUID());
        // 注册时设置激活状态为300，未激活
        user.setStatus(300);

        // 注册成功发送激活邮件，注册失败不发送邮件
            // 注册用户userMapper这里返回的是int
        if (userMapper.addUser(user) > 0) {

            // 激活邮件
            Context context = new Context();
            // Context构造模板中变量需要的值
            context.setVariable("id", user.getId());

            // 设定一个路径，激活的时候使用。
            // 注意:此处的链接地址,是项目内部地址,如果我们没有正式的服务器地址,暂时无法从qq邮箱中跳转到我们自己项目的激活页面

            // 第一种激活方式：点击链接立即激活。
            // http://localhost:8080/activation/b4db77e3174841ec9a218b3bfeac1ab1
//            String url = domain + "/activation/" + user.getActivationCode();

            // 第二种激活方式：邮箱接收激活码和激活页面，在激活页面输入激活码激活，也可以集成到注册页面，注册时需要邮箱收到的验证码（激活码）。
            // http://localhost:8080/activation
            String url = domain + "/activation";

            // Context构造模板中变量需要的值
            context.setVariable("url", url);
            // 这里set进去username，是为了发邮件的模板使用${username}。
            context.setVariable("username", user.getUsername());

            // set进去激活码，激活方式二，输入激活码激活。
            context.setVariable("activationCode", user.getActivationCode());

            // 激活方式一：activationModel对应activationModel.html
            // String mail = templateEngine.process("activationModel", context);

            // 激活方式二：activationCodeVerifyModel对应activationCodeVerifyModel.html
            String mail = templateEngine.process("activationCodeVerifyModel", context);

            mailService.sendHtmlMail(email, "激活账号", mail);
            message = "注册成功";
            return Result.success_code_message(100, message);
        }
        // 注册失败
        message = "注册失败";
        return Result.fail_code_message(101 ,message);
    }

    @Override
    public User selectByActivationCode(String activationCode) {
        return userMapper.selectByActivationCode(activationCode);
    }

    @Override
    public void updateStatus(String activationCode, int status) {
        userMapper.updateStatus(activationCode, status);
    }

    @Override
    public User selectByUserNameAndPassWord(String username, String password, HttpSession httpSession) {
        // 将密码md5加密后再查询
        password = PassWordUtils.md5(password);
        return userMapper.selectByUserNameAndPassWord(username, password);
    }

    @Override
    public User selectByEmailAndPassWord(String email, String password, HttpSession httpSession) {
        // 将密码md5加密后再查询
        password = PassWordUtils.md5(password);
        return userMapper.selectByEmailAndPassWord(email, password);
    }

    @Override
    public void insertGitHubUserInfo(String github_id, String login, String access_token) {
        userMapper.insertGitHubUserInfo(github_id, login, access_token);
    }

    @Override
    public boolean selectByGithub_id(String github_id) {
        return userMapper.selectByGithub_id(github_id);
    }

}