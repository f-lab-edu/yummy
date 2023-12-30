package com.huijin.yummy.member.controller;

import com.huijin.yummy.member.entity.Member;
import com.huijin.yummy.member.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired // DI
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/loginPage") // 로그인 페이지 이동
    public String loginPage () {
        return "login/loginPage";
    }

    @PostMapping("/login") // 로그인 처리
    public String login (@RequestParam(name = "email") String email, @RequestParam(name = "password") String password
            , Model model, HttpServletRequest request) {
        Member member = loginService.findByEmail(email);

        if (member != null && member.getPassword().equals(password)) {
            // 로그인 성공
            request.getSession().invalidate(); // 세션을 생성하기 전에 기존의 세션 파기
            HttpSession session = request.getSession(true); // Session이 없으면 생성
            session.setAttribute("memberInfo", member);

            return "redirect:/mainPage"; // 로그인 후 이동할 페이지
        } else {
            // 로그인 실패
            model.addAttribute("error", "Invalid userId or password");

            return "redirect:/loginPage"; // 로그인 폼을 다시 보여줌
        }
    }

    @GetMapping("/logout") // 로그아웃 처리
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false); // Session이 없으면 null return

        if(session != null) {
            session.invalidate();
        }

        return "redirect:/mainPage";
    }
}
