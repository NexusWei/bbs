package com.nexus.pojo;

import lombok.Data;

/**
 * @description: 获取access_token
 */

@Data
public class AccessToken {
    //客户端ID
    private String client_id;
    // 客户端密匙
    private String client_secret;
    // 响应接收的代码
    private String code;
    // 登录后跳转的地址
    private String redirect_uri;
    // 随机字符串
    private String state;
}
