package com.whut.smart.service.impl;

import com.whut.smart.dao.mybatis.UserDao;
import com.whut.smart.dto.UserDto;
import com.whut.smart.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by null on 2016/12/31.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public UserDto getByUsername(String username) {
        return userDao.getByUsername(username);
    }
}
