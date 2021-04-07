package com.nexus.controller;

import com.nexus.pojo.User;
import com.nexus.service.UserService;
import com.nexus.utils.Result;
import com.nexus.utils.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    // 请求方法为 GET，在发起请求后会跳转到 register.html 中。
    @GetMapping({"/register", "register.html"})
    public String registerPage() {
        return "register";
    }

    // controller和service层不会请求id,由serviceimpl直接set一个uuid
    @PostMapping("/register")
    @ResponseBody
    public Result register(@RequestParam("username") String username,
                           @RequestParam("password") String password,
                           @RequestParam("email") String email,
                           @RequestParam("verifyCode") String verifyCode,
                           HttpSession httpSession) {

        String message = "";

        // 验证用户名是否为空
        if (StringUtils.isEmpty(username)) {
            message ="请输入用户名";
            return Result.fail_code_message(101, message);
        }

        if (StringUtils.isEmpty(password)) {
            message ="请输入密码";
            return Result.fail_code_message(101, message);
        }

        if (StringUtils.isEmpty(email)) {
            message ="请输入邮箱";
            return Result.fail_code_message(101, message);
        }

        if (userService.selectByName(username) != null) {
            message = "用户名已存在";
            return Result.fail_code_message(101, message);
        }

        if (userService.selectByEmail(email) != null) {
            message = "此邮箱已被注册";
            return Result.fail_code_message(101, message);
        }

        // 从session中获得verifyCode
        String kaptchaCode = httpSession.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            message = "验证码错误";
            return Result.fail_code_message(101, message);
        }

        // 接收注册结果, 此时注册结果已经toString
        Result register = userService.register(username, password, email);

        // 注册成功
            // 如果 100 = 100 ,则注册成功.否则注册失败
            // Result(code=100, message="注册成功", data=null)
        if (register.getCode() == ResultCode.REGISTER_SUCCESS_CODE.getCode()) {
            message = "注册成功";
            return Result.success_code_message(100, message);
        }

        // 注册失败
        message = "注册失败";
        return Result.fail_code_message(101, message);
    }

    // 点击链接即激活。
    @GetMapping("/activation/{activationCode}")
    public String activation(Model model, @PathVariable ("activationCode") String activationCode) {
        String message = "";
        User user = userService.selectByActivationCode(activationCode);
        if (user == null){

            model.addAttribute("msg", "激活码不正确");
            model.addAttribute("target","/login");
        }
        else if (user != null && user.getStatus() != 301) {
            userService.updateStatus(activationCode, 301);
            model.addAttribute("msg", "激活成功");
            model.addAttribute("target","/login");
        }
        // 如果状态等于激活成功，则返回请勿重复激活，但是不要写入到数据库。
        else if (user.getStatus() == ResultCode.ACTIVATION_SUCCESS.getCode()) {
            model.addAttribute("msg", "您已激活成功，请勿重复激活！");
            model.addAttribute("target","/login");
        }else{
            model.addAttribute("msg", "激活失败");
        }

        return "activationResult";
    }

    // 请求activation，跳转到activationCodeVerify.html页面
    @GetMapping({"/activation", "activationCodeVerify.html"})
    public String activationPage() {
        return "activationCodeVerify";
    }

    // http://localhost:8080/activation?activationCode=b4db77e3174841ec9a218b3bfeac1ab1
    @PostMapping("/activation")
    @ResponseBody
    public Result activationCode(@RequestParam("activationCode") String activationCode) {
        String message = "";

        // 根据激活码查询用户
        User user = userService.selectByActivationCode(activationCode);
        if (user == null){
            message = "激活码不正确";
            return Result.fail_code_message(303, message);
        }

        // 激活成功
        if (user != null && user.getStatus() != 301) {
            userService.updateStatus(activationCode, 301);
            message = "激活成功";
            return Result.success_code_message(301, message);
        }

        // 如果状态等于激活成功，则返回请勿重复激活，但是不要写入到数据库。
        if (user.getStatus() == ResultCode.ACTIVATION_SUCCESS.getCode()) {
            message = "您已激活成功，请勿重复激活！";
            return Result.fail_code_message(302, message);
        }

        message = "激活失败";
        return Result.fail_code_message(303, message);
    }

    @GetMapping({"/login", "login.html"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Result Login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession httpSession) {

        String message = "";

        if (StringUtils.isEmpty(username)) {
            message ="用户名不能为空";
            return Result.fail_code_message(103, message);
        }

        if (StringUtils.isEmpty(password)) {
            message ="密码不能为空";
            return Result.fail_code_message(103, message);
        }

        if (StringUtils.isEmpty(verifyCode)) {
            message ="验证码不能为空";
            return Result.fail_code_message(103, message);
        }

        // 从session中获得verifyCode
        String kaptchaCode = httpSession.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            message = "验证码错误";
            return Result.fail_code_message(103, message);
        }

        // 调用service，service调用mapper查数据库
        User user = userService.selectByUserNameAndPassWord(username, password, httpSession);

        if (user == null) {
            return Result.fail_code_message(103, "用户名或密码错误");
        }

        // 登录过的账号不能重复登录
        if (httpSession.getAttribute("user") != null) {
            return Result.fail_code_message(103, "该账号已登录，请勿重复登录");
        }

        // 未激活的用户不能登录
        if (user.getStatus() == ResultCode.ACTIVATION_NONE.getCode() || user.getStatus() == ResultCode.ACTIVATION_FAILURE.getCode()) {
            return Result.fail_code_message(103, "您的账号还未激活，请先激活");
        }

        // user不为空，不存在session，status为激活成功的，方可登录。
        if (user != null && httpSession.getAttribute("user") == null && user.getStatus() == ResultCode.ACTIVATION_SUCCESS.getCode()) {
            // 将user写入到session
            httpSession.setAttribute("user", user);
            return Result.success_code_message(102, "登录成功");

        }

        //登录失败
        return Result.fail_code_message(103, "登录失败");
    }

    @GetMapping({"/loginEmail", "loginEmail.html"})
    public String loginEmailPage() {
        return "loginEmail";
    }

    @PostMapping("/loginEmail")
    @ResponseBody
    public Result LoginEmail(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("verifyCode") String verifyCode,
                        HttpSession httpSession) {

        String message = "";

        if (StringUtils.isEmpty(email)) {
            message ="邮箱不能为空";
            return Result.fail_code_message(103, message);
        }

        if (StringUtils.isEmpty(password)) {
            message ="密码不能为空";
            return Result.fail_code_message(103, message);
        }

        if (StringUtils.isEmpty(verifyCode)) {
            message ="验证码不能为空";
            return Result.fail_code_message(103, message);
        }

        // 从session中获得verifyCode
        String kaptchaCode = httpSession.getAttribute("verifyCode") + "";
        if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
            message = "验证码错误";
            return Result.fail_code_message(103, message);
        }

        // 调用service，service调用mapper查数据库
        User user = userService.selectByEmailAndPassWord(email, password, httpSession);

        if (user == null) {
            return Result.fail_code_message(103, "用户名或密码错误");
        }

        // 登录过的账号不能重复登录
        if (httpSession.getAttribute("user") != null) {
            return Result.fail_code_message(103, "该账号已登录，请勿重复登录");
        }

        // 未激活的用户不能登录
        if (user.getStatus() == ResultCode.ACTIVATION_NONE.getCode() || user.getStatus() == ResultCode.ACTIVATION_FAILURE.getCode()) {
            return Result.fail_code_message(103, "您的账号还未激活，请先激活");
        }

        // user不为空，不存在session，status为激活成功的，方可登录。
        if (user != null && httpSession.getAttribute("user") == null && user.getStatus() == ResultCode.ACTIVATION_SUCCESS.getCode()) {
            // 将user写入到session
            httpSession.setAttribute("user", user);
            return Result.success_code_message(102, "登录成功");
        }

        //登录失败
        return Result.fail_code_message(103, "登录失败");
    }

    // 登出功能
    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        // 移除用户名登录和邮箱登录的session，user，
        httpSession.removeAttribute("user");
        // 移除github登录的session，githubUser
        httpSession.removeAttribute("githubUser");
        return "/login";
    }
}
