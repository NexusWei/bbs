package com.nexus.controller;

import com.nexus.pojo.Page;
import com.nexus.pojo.Publish;
import com.nexus.service.PublishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    PublishService publishService;

    // RequestMapping表示访问的时候不需要额外地址
    // 即http://localhost:8080/ 就可以跳转到首页
//    @RequestMapping("")

    // http://localhost:8080/ 可以跳转到首页
    // http://localhost:8080/index 也可以跳转到首页
    @GetMapping(value={"/index","/"})
    public String publishList(Model model, Page page){

        List<Map<String, Object>> maps = publishService.publishList(page);
        int total = publishService.total(page);
        page.setTotal(total);

        // 这里打印只是为了测试
//        for (Map<String, Object> map : maps) {
//            System.out.println(map);
//        }

        model.addAttribute("page", page);
        model.addAttribute("maps", maps);

        // set一个当前页，start/5+1即是当前页
        page.setCurrentPage(page.getStart()/ page.getLimit()+1);

        List<Publish> publishTagList = publishService.selectPublishTagList();
        model.addAttribute("publishTagList", publishTagList);
        return "index";
    }

    @GetMapping("/head")
    public String test() {
        return "common/head";
    }
}
