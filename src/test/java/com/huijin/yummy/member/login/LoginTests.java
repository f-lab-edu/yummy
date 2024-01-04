package com.huijin.yummy.member.login;

import com.huijin.yummy.member.controller.LoginController;
import com.huijin.yummy.member.repository.MemberRepository;
import com.huijin.yummy.member.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
@AutoConfigureMockMvc
public class LoginTests {

    @Autowired
    MockMvc mockMvc;
    //@Autowired
    MemberRepository memberRepository;
    @MockBean
    LoginService loginService;
    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    @DisplayName("아이디로 로그인: 성공")
    public void 로그인검증() throws Exception {
        String userId = "test1";
        mockMvc.perform(post("/login/login"))
                .andExpect(status().isOk());                            // 응답 코드가 200(OK)인지 확인
//        mockMvc.perform(post("/login/loginPage"))
//                .andExpect(status().isOk());
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
