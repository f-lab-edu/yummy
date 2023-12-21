package com.huijin.yummy.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
    @GetMapping("/myPage")
    public String myPage() {
        return "/myPage/myPage";
    }
}
