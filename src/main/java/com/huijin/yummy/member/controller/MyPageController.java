package com.huijin.yummy.member.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
    @GetMapping("/myPage")
    public String myPage(HttpSession session) {
        if(session.getAttribute("memberInfo") == null) {
            return "redirect:/login/loginPage";
        }
        return "/myPage/myPage";
    }
}
