package com.whut.smart.service;

import com.whut.smart.dto.ResultDto;
import com.whut.smart.dto.UserDto;

/**
 * Created by null on 2016/12/31.
 */
public interface UserService {

    UserDto getByUsername(String username);

    ResultDto<?> login(UserDto userDto);
}
