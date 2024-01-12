package com.huijin.yummy.member.controller;

import org.springframework.web.bind.annotation.GetMapping;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @GetMapping("hello")
    public String test() {
        return "hello";
    }
}
