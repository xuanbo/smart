package com.whut.smart.controller;

import com.whut.smart.dto.UserDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * Created by null on 2016/12/30.
 */
@RestController
public class UserController {

    @PostMapping("/user")
    public String add(@Valid @RequestBody UserDto userDto) {
        return "{\"code\": 200, \"status\": \"新增成功\"}";
    }

}
