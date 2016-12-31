package com.whut.smart.controller;

import com.whut.smart.dto.ResultDto;
import com.whut.smart.dto.UserDto;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * login
 *
 * Created by xinQing on 2017/1/1.
 */
@RestController
public class LoginController {

    @PostMapping(value = "/login")
    public ResultDto<String> login(@Valid @RequestBody UserDto userDto) {
        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUsername(), userDto.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        ResultDto<String> resultDto = new ResultDto<>();
        if (subject.isAuthenticated()) {
            resultDto.setCode(HttpStatus.OK.value());
            resultDto.setMessage("认证成功");
            return resultDto;
        } else {
            // 下面永远不会调用，因为会抛出异常，然后被我捕获了
            resultDto.setCode(HttpStatus.UNAUTHORIZED.value());
            resultDto.setMessage("认证失败");
            resultDto.setResult("用户名不存在或密码错误");
            return resultDto;
        }
    }
}
