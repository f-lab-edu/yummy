package com.huijin.yummy.member.login;

import com.huijin.yummy.member.repository.MemberRepository;
import com.huijin.yummy.member.service.LoginService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginTests {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    LoginService loginService;

    @Test
    @DisplayName("아이디로 로그인: 성공")
    public void 로그인검증() {
        String userId = "test1";

//        User user = loginService.findByUserId(userId);
//        mockMvc.perform(post("/login/login") // (2)
//                        .param("userId", "test1") // (2)
//                        .param("password", "test1") // (2)
//                        .with(SecurityMockMvcRequestPostProcessors.csrf())) // (3)
//                .andExpect(status().is3xxRedirection()) // (4)
//                .andExpect(redirectedUrl("/")) // (5)
//                .andExpect(authenticated().withUsername("jaime")); // (6)
    }
}
