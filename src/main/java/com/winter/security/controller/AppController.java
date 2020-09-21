package com.winter.security.controller;

import com.winter.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
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


    @Autowired
    public MyUserDetailsService myUserDetailsService;

    @GetMapping("/name")
    public UserDetails getUserByName(String username) {
        return myUserDetailsService.loadUserByUsername(username);
    }

    @GetMapping("hello")
    public String hello() {
        return "Hello,App";
    }
}
