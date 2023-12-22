//package com.huijin.yummy.user.security;
//
//import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity // Spring Security 설정을 시작
//public class WebSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//
//        http.authorizeRequests(config -> {
//            //config.requestMatchers("/**").authenticated();
//            config.requestMatchers("/", "/mainPage", "login/loginPage").permitAll();
//        });
//        /*http.formLogin(config -> {
//            config
//                    .loginPage("/login/loginPage") // 로그인 View 제공(GET)
//                    .loginProcessingUrl("/login/login") // 로그인 처리(POST)
//                    .defaultSuccessUrl("/") // 로그인 성공 시 URL
//                    .failureUrl("/login/loginPage") // 로그인 실패 시 URL
//                    .permitAll();
//        });*/
//        http.csrf(config -> {
//            config.disable();
//        });
//
//        return http.build();
//    }
//
//    /*@Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }*/
//}
