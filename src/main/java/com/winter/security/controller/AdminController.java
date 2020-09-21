package com.winter.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName : AdminController
 * @Description :
 * @Author : Winter
 * @Date: 2020-09-18 15:52
 */
@RestController
@RequestMapping("/admin/api")
public class AdminController {

    @GetMapping("hello")
    public String hello(){
        return "Hello,Admin";
    }
}
