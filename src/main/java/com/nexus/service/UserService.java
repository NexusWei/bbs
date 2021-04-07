package com.nexus.service;

import com.nexus.pojo.GithubUser;
import com.nexus.pojo.User;
import com.nexus.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;


@Service
public interface UserService {
    User selectByName(String username);

    User selectByEmail(String email);

    /**
     * 用户注册
     * @param
     * @param username
     * @param password
     * @param email
     * @return
     */
    // 开启事务，以防出现问题，但还是注册成功
    @Transactional
    Result register(String username, String password, String email);

    // 根据激活码查询用户
    User selectByActivationCode(String activationCode);

    // 更新激活状态
    void updateStatus(String activationCode, int status);

     // 根据用户名和密码查询用户
    User selectByUserNameAndPassWord(String username, String password, HttpSession httpSession);

    // 根据邮箱和密码查询用户
    User selectByEmailAndPassWord(String email, String password, HttpSession httpSession);

    // github登录时存储用户信息：id、login、access_token
    void insertGitHubUserInfo(String github_id, String login, String access_token);

    // 根据id查询github的用户信息
    boolean selectByGithub_id(String github_id);
}
