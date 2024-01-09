package com.huijin.yummy.member.join;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huijin.yummy.member.controller.LoginController;
import com.huijin.yummy.member.controller.SignUpController;
import com.huijin.yummy.member.entity.Member;
import com.huijin.yummy.member.repository.MemberRepository;
import com.huijin.yummy.member.service.LoginService;
import com.huijin.yummy.member.service.SignUpService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(SignUpController.class)
public class SignUpTests {

    //@Autowired
    MockMvc mockMvc;
    @MockBean
    MemberRepository memberRepository;
    @MockBean
    SignUpService signUpService;
    @MockBean
    Member member;
    //@Autowired
    private WebApplicationContext context;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context)
                .addFilter(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    private static final String MEMBER_NAME = "홍길동";
    private static final Long MEMBER_ID = 1L;

    //@Test
    @DisplayName("회원가입 페이지")
    public void 회원가입페이지검증() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders
                                .get("/signUp/signUpPage") // POST 요청
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    //@Test
    @DisplayName("회원가입 검증")
    public void 회원가입검증() throws Exception {
        Member member = new Member.Builder()
                .email("test@test.com")
                .password("test")
                .name("name")
                .address("address")
                .phoneNumber("01000000000")
                .memberType("c")
                .build();

        this.mockMvc
                .perform(post("/signUp/signUp")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(member))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
