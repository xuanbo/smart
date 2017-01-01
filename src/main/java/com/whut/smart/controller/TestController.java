package com.whut.smart.controller;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 测试
 *
 * Created by null on 2017/1/1.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RequiresRoles({"ROLE_ADMIN"})
    @GetMapping("/ann")
    public String ann() {
        return "{\"message\": \"测试shiro编程式权限注解\"}";
    }

}
