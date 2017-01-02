package com.whut.smart.controller;

import com.whut.smart.dto.ResultDto;
import com.whut.smart.dto.UserDto;
import com.whut.smart.service.AccessTokenService;
import com.whut.smart.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.UUID;

/**
 * login
 *
 * Created by null on 2017/1/1.
 */
@Controller
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 登录页面
     *
     * @return /WEB-INF/pages/login.jsp
     */
    @GetMapping("/login*")
    public String login() {
        return "login";
    }

    /**
     * 登录认证
     *
     * @param userDto UserDto
     * @return ResultDto
     */
    @PostMapping("/login")
    @ResponseBody
    public ResultDto<?> login(@Valid @RequestBody UserDto userDto) {
        return userService.login(userDto);
    }
}
