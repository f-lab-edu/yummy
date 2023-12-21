package com.huijin.yummy.user.controller;

import com.huijin.yummy.user.entity.User;
import com.huijin.yummy.user.service.JoinService;
import com.huijin.yummy.user.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/join")
public class JoinController {

    private final JoinService joinService;

    @Autowired
    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @GetMapping("/joinPage") // 로그인 페이지 이동
    public String joinPage () {
        return "/join/joinPage";
    }

    @PostMapping("/join") // 로그인 처리
    public String login (User user) {
        System.err.println(user.getAddress());
        System.err.println(user.getUserId());
        user.setJoinedDate(LocalDateTime.now());
        joinService.join(user);
        return "redirect:/mainPage"; // 로그인 폼을 다시 보여줌
    }
}
