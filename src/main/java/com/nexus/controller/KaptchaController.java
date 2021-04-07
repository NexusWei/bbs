package com.nexus.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Controller
public class KaptchaController {

    @Autowired
    private DefaultKaptcha kaptcha;

    @GetMapping("/kaptcha")
    public void defaultKaptcha(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws Exception {
        byte[] captchaOutputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String verifyCode = kaptcha.createText();
            servletRequest.getSession().setAttribute("verifyCode", verifyCode);
            BufferedImage image = kaptcha.createImage(verifyCode);
            ImageIO.write(image, "jpg", outputStream);
        }catch (IllegalArgumentException e) {
            servletResponse.sendError(servletResponse.SC_NOT_FOUND);
            return;
        }

        captchaOutputStream = outputStream.toByteArray();
        servletResponse.setHeader("Cache-Control", "no-store");
        servletResponse.setHeader("Pragma", "no-cache");
        servletResponse.setDateHeader("Expires", 0);
        servletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = servletResponse.getOutputStream();
        responseOutputStream.write(captchaOutputStream);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    @GetMapping("/verify")
    @ResponseBody
    public String verify(@RequestParam("code") String code, HttpSession session) {
        if (StringUtils.isEmpty(code)) {
            return "验证码不能为空";
        }
        String verifyCode = session.getAttribute("verifyCode") + "";
        System.out.println("打印验证码看看结果:" + verifyCode);
        if (StringUtils.isEmpty(verifyCode) || !code.equals(verifyCode)) {
            return "验证码错误";
        }
        return "验证成功";
    }
}
