package com.whut.smart.service.impl;

import com.whut.smart.dao.mybatis.UserDao;
import com.whut.smart.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by null on 2016/12/31.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

}
