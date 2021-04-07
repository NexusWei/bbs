package com.nexus.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KaptchaConfig {
    @Bean
    public DefaultKaptcha getDefaultKaptcha() {
        com.google.code.kaptcha.impl.DefaultKaptcha defaultKaptcha = new com.google.code.kaptcha.impl.DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框，合法值：yes , no，默认yes
//        properties.put("kaptcha.border", "yes");
        // 边框颜色，合法值： r,g,b (and optional alpha) 或者 white,black,blue，默认值black
//        properties.put("kaptcha.border.color", "green");
        // 边框厚度，合法值：>0，默认值1
//        properties.put("kaptcha.border.thickness", "5");
        // 图片宽，默认200
        properties.put("kaptcha.image.width", "160");
        // 图片高，默认50
        properties.put("kaptcha.image.height", "40");
        // 文本集合，验证码值从此集合中获取，默认值 abcde2345678gfynmnpwx
//        properties.put("kaptcha.textproducer.char.string", "123");
        // 验证码长度，默认5
        properties.put("kaptcha.textproducer.char.length", "4");
        // 字体，默认Arial, Courier
        properties.put("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
//        properties.setProperty("kaptcha.textproducer.font.names", "楷体,微软雅黑");

        // 字体大小，默认40px
        properties.put("kaptcha.textproducer.font.size", "30");
        // 字体颜色，合法值： r,g,b  或者 white,black,blue.
        properties.put("kaptcha.textproducer.font.color", "black");
        // 文字间隔，默认2
//        properties.put("kaptcha.textproducer.char.space", "2");
        // 干扰颜色，合法值： r,g,b 或者 white,black,blue，默认 black
//        properties.put("kaptcha.noise.color", "red");
        // 背景颜色渐变，开始颜色，默认light grey
//        properties.put("kaptcha.background.clear.from", "yellow");
        // 背景颜色渐变，结束颜色，默认white
//        properties.put("kaptcha.background.clear.to", "blue");
        // session key，默认值 KAPTCHA_SESSION_KEY
//        properties.put("kaptcha.session.key", "");
        // 	session date，默认值 KAPTCHA_SESSION_DATE
//        properties.put("kaptcha.session.date", "");

        // 这里别导错包
        Config config = new Config(properties);
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }
}
