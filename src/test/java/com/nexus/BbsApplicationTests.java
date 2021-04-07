package com.nexus;

import com.nexus.mapper.UserMapper;
import com.nexus.pojo.User;
import com.nexus.utils.MailService;
import com.nexus.utils.PassWordUtils;
import com.nexus.utils.UUIDUtils;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@SpringBootTest

class BbsApplicationTests {

    @Autowired
    MailService mailService;

    // TemplateEngine
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    UserMapper userMapper;

//    @Test
//    void contextLoads() {
//    }
//
//    @Test
//    public void testMailService() {
//        mailService.sendHtmlMail("3163141591@qq.com","text","text");
//    }
//
//    @Test
//    public void testMailTemplate() {
//        Context context = new Context();
//        context.setVariable("username","nexus");
//        String content=templateEngine.process("mailtemplate",context);
//        mailService.sendHtmlMail("3163141591@qq.com","欢迎",content);
//    }
//
//    @Test
//    public void generateUUID() {
//        System.out.println("UUID为：" + UUIDUtils.generateUUID());
//    }
//
//    @Test
//    public void md5() {
//        System.out.println("MD5加密：" + PassWordUtils.md5("123456"));
//    }
//
//    @Test
//    public void selectByNameTest() {
//
//        // 方式一：getMapper
//
////        User user = userMapper.selectByName("第一人");
////        List<User> userList = userMapper.selectById();
////        for (User user : userList) {
////            System.out.println(user);
////        }
//
////        System.out.println(user);
//    }
//
//    public static void main(String[] args) {
//        boolean isa = false;
//
//
//        if (!isa){
//            System.out.println(isa);
//            System.out.println(!isa);
//            System.out.println(1);
//        }else{
//            System.out.println(2);
//        }
//    }
}
