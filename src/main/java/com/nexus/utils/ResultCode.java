package com.nexus.utils;

public enum ResultCode {

    // 注册成功
    REGISTER_SUCCESS_CODE(100),

    // 注册失败
    REGISTER_FAIL_CODE(101),

    // 未激活 0
    ACTIVATION_NONE(300),

    // 激活成功
    ACTIVATION_SUCCESS(301),

    // 重复激活
    ACTIVATION_REPEAT(302),

    // 激活失败
    ACTIVATION_FAILURE(303),

    // 未登录
    LOGIN_NONE(104),

    // 登录成功
    LOGIN_SUCCESS_CODE(102),

    // 登录失败
    LOGIN_FAIL_CODE(103),

    // 发布成功
    SUCCESS_CODE(210),

    // 发布失败
    FAIL_CODE(410);


    // 构造器
    private int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
