package com.whut.smart.dao.mybatis;

import com.whut.smart.dto.UserDto;

/**
 * Created by null on 2016/12/30.
 */
public interface UserDao {

    UserDto getByUsername(String username);

}
