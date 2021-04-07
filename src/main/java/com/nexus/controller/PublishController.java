package com.nexus.controller;

import com.nexus.pojo.Publish;
import com.nexus.pojo.User;
import com.nexus.service.PublishService;
import com.nexus.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class PublishController {

    @Autowired
    PublishService publishService;

    @GetMapping({"/publish", "publish.html"})
    public String publishPage(HttpSession httpSession) {
        if (httpSession.getAttribute("user") == null){
            return "redirect:/login";
        }
        return "/user/publish";
    }

    @PostMapping("/publish")
    @ResponseBody
    public Result publish(String title, String description, String tag, HttpSession httpSession) {
        Publish publish = new Publish();
        publish.setPublish_id(1);
        // HtmlUtils.htmlEscape 对特殊符号转义
        publish.setTitle(HtmlUtils.htmlEscape(title));
        publish.setDescription(HtmlUtils.htmlEscape(description));
        publish.setTag(HtmlUtils.htmlEscape(tag));
        publish.setCreate_time(new Date());
        publish.setUpdate_time(new Date());

//        这4行整合到下面一行
//        User user =(User) httpSession.getAttribute("user");
//        String user_id = user.getId();
//        System.out.println(user_id);
//        publish.setUser_id(user_id);
        publish.setUser_id(((User) httpSession.getAttribute("user")).getId());
        publishService.addPublish(publish);
        return Result.success_code_message(210, "发布成功");
    }
}