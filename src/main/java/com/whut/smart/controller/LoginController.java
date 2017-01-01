package com.whut.smart.controller;

import com.whut.smart.dto.ResultDto;
import com.whut.smart.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * login
 *
 * Created by null on 2017/1/1.
 */
@Controller
public class LoginController {

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
    public ResultDto<String> login(@Valid @RequestBody UserDto userDto) {
        ResultDto<String> resultDto = new ResultDto<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            resultDto.setCode(HttpStatus.OK.value());
            resultDto.setMessage("认证成功");
            resultDto.setResult("请不要重复登录");
            return resultDto;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUsername(), userDto.getPassword());
        token.setRememberMe(userDto.getRememberMe());
        subject.login(token);
        resultDto.setCode(HttpStatus.OK.value());
        resultDto.setMessage("认证成功");
        resultDto.setResult("通过表单登录");
        return resultDto;
    }
}
