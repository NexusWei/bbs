package com.nexus.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class MailService {

    // 启用日志
    private final static Logger logger = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private JavaMailSender javaMailSender;

    // //注入properties里的username值给from
    @Value("${spring.mail.username}")
    private String from;

    public void sendHtmlMail(String to, String subject, String content){
        try {
            // 邮件内容
            MimeMessage message = javaMailSender.createMimeMessage();
            // 帮助构造邮件内容
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 发件人
            helper.setFrom(from);
            // 收件人
            helper.setTo(to);
            // 主题
            helper.setSubject(subject);
            // 内容，开启html
            helper.setText(content, true);
            javaMailSender.send(helper.getMimeMessage());
            logger.info("发送邮件成功");
        }catch (MessagingException e){
            logger.error("发送邮件失败：" + e.getMessage());
        }
    }
}
