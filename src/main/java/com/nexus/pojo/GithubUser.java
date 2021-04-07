package com.nexus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 获取user
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubUser {
    // github的id
    private String id;
    // github的name
    private String name;
    // github的描述信息
    private String bio;
    // github的登录用户名
    private String login;
}
