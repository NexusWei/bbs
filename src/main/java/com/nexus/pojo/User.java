package com.nexus.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String email;
    // 激活时用的uuid
    private String activationCode;
    // 激活状态
    private int status;

//    private int type;


//    private String headerUrl;
//    private Date createTime;
}
