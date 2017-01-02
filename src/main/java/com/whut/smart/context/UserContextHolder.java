package com.whut.smart.context;

import com.whut.smart.dto.UserDto;

/**
 * 用户上下文经过认证filter后可以获取到当前用户
 *
 * Created by null on 2017/1/2.
 */
public class UserContextHolder {

    private static ThreadLocal<UserDto> userContext = new ThreadLocal<>();

    public static void set(UserDto userDto) {
        userContext.set(userDto);
    }

    public static UserDto get() {
        return userContext.get();
    }
}
