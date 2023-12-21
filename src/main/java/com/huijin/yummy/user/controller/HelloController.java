package com.huijin.yummy.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloWorld")
    //@ResponseBody의 역할을 @RestController가 대신함.
    public String helloWorld(@RequestParam(value = "data", required = false) String data){
        data = "hello world";
        return data;
    }
}
