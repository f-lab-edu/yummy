package com.huijin.yummy.user.controller;

import com.huijin.yummy.user.entity.User;
import com.huijin.yummy.user.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/join")
public class JoinController {

    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/joinPage")
    public String joinPage () {
        return "join/joinPage";
    }

    @PostMapping("/join") // 로그인 처리
    public String login (User user) {
        System.err.println(user.getAddress());
        System.err.println(user.getUserId());
        user.setJoinedDate(LocalDateTime.now());
        joinService.join(user);
        return "redirect:/mainPage";
    }
}
