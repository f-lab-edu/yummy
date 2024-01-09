package com.huijin.yummy.member.controller;

import com.huijin.yummy.member.entity.Member;
import com.huijin.yummy.member.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/signUpPage")
    public String signUpPage () {
        return "signUp/signUpPage";
    }

    @PostMapping("/signUp")
    public String signUp (Member member) {
        signUpService.signUp(member);
        return "redirect:/mainPage";
    }
}
