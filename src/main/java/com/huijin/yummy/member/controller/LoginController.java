package com.huijin.yummy.member.controller;

import com.huijin.yummy.member.dto.LoginRequestDTO;
import com.huijin.yummy.member.entity.Member;
import com.huijin.yummy.member.service.LoginService;
import com.huijin.yummy.member.service.SessionService;
import com.huijin.yummy.security.jwt.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final LoginService loginService;
    private final JwtService jwtService;
    private final SessionService sessionService;

    @Autowired // DI
    public LoginController(LoginService loginService, JwtService jwtService, SessionService sessionService) {
        this.loginService = loginService;
        this.jwtService = jwtService;
        this.sessionService = sessionService;
    }

    @GetMapping("/loginPage") // 로그인 페이지 이동
    public String loginPage () {
        return "login/loginPage";
    }


    /*public String login (@RequestParam(name = "email") String email, @RequestParam(name = "password") String password
            , Model model, HttpServletRequest request) {*/
    @PostMapping("/login") // 로그인 처리
    @ResponseBody // 이 애노테이션을 추가하여 JSON 형태로 데이터 반환
    public String login (
            LoginRequestDTO loginRequestDTO, HttpServletRequest request, HttpServletResponse response, Model model
    ) throws IOException {
            String email = loginRequestDTO.getEmail();
            String password = loginRequestDTO.getPassword();

        if (loginService.login(email, password)) {
            // 로그인 성공
            Member member = loginService.findByEmail(email);

            // session
            request.getSession().invalidate(); // 세션을 생성하기 전에 기존의 세션 파기
            HttpSession session = request.getSession(true); // Session이 없으면 생성
            session.setAttribute("memberInfo", loginService.findByEmail(email));

            String jwt = jwtService.generateJwt(member.getId());
            System.out.println("jwt : "+jwt);
            //response.sendRedirect("redirect:/mainPage");
            return jwtService.generateJwt(member.getId()); // 토큰을 클라이언트에게 전달
            //return "redirect:/mainPage";
        } else {
            // 로그인 실패
            model.addAttribute("error", "Invalid userId or password");

            throw new IllegalArgumentException("login error");
        }
    }

    @GetMapping("/logout") // 로그아웃 처리
    public String logout(@RequestHeader("Authorization") String header, HttpServletRequest request) {
        sessionService.expireSession(header.substring("bearer ".length()));
        HttpSession session = request.getSession(false); // Session이 없으면 null return

        if(session != null) {
            session.invalidate();
        }

        return "redirect:/mainPage";
    }
}
