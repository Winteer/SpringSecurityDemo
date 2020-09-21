package com.winter.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : AppController
 * @Description :
 * @Author : Winter
 * @Date: 2020-09-18 15:53
 */
@RestController
@RequestMapping("/app/api")
public class AppController {
    @GetMapping("hello")
    public String hello(){
        return "Hello,App";
    }
}
