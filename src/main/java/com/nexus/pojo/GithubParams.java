package com.nexus.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @description: 获取配置文件中github的信息。
 */

@Component
// 装载配置文件信息，需要添加依赖。
@ConfigurationProperties(prefix = "github")
@Data
public class GithubParams {

    private String client_id;
    private String client_secret;
    private String redirect_uri;
    private String token_uri;
    private String user_uri;
}
