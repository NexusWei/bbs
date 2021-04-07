package com.nexus.mapper;

import com.nexus.pojo.Publish;
import com.nexus.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    // 接口：根据username的值到数据库中查询username
    User selectByName(String username);

    // 查询email
    User selectByEmail(String email);

    // 添加用户
    int addUser(User user);

    // 根据激活码查询用户
    User selectByActivationCode(String activationCode);

    // 更新激活状态
    void updateStatus(String activationCode, int status);

    // 根据用户名和密码查询用户
    User selectByUserNameAndPassWord(String username, String password);

    // 根据邮箱和密码查询用户
    User selectByEmailAndPassWord(String email, String password);

    // 更改密码，后期再做
//    void updatePassWord(String password);

    // github登录时插入id、login、access_token
    // 注意github返回的name可以修改，存储意义不大
    void insertGitHubUserInfo(String github_id, String login, String access_token);

    // 根据id查询github的用户信息
    boolean selectByGithub_id(String github_id);
}
