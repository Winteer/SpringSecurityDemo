package com.winter.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : UserController
 * @Description :
 * @Author : Winter
 * @Date: 2020-09-18 15:53
 */
@RestController
@RequestMapping("/user/api")
public class UserController {
    @GetMapping("hello")
    public String hello() {
        return "Hello,User!";
    }
}
