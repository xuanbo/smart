package com.whut.smart.service.impl;

import com.whut.smart.dao.mybatis.UserDao;
import com.whut.smart.dto.ResultDto;
import com.whut.smart.dto.UserDto;
import com.whut.smart.service.AccessTokenService;
import com.whut.smart.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Created by null on 2016/12/31.
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private AccessTokenService accessTokenService;

    @Transactional
    @Override
    public UserDto getByUsername(String username) {
        return userDao.getByUsername(username);
    }

    @Override
    public ResultDto<?> login(UserDto userDto) {
        ResultDto<String> resultDto = new ResultDto<>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            resultDto.setCode(HttpStatus.OK.value());
            resultDto.setMessage("认证成功");
            resultDto.setResult("请不要重复登录");
            return resultDto;
        }
        UsernamePasswordToken token = new UsernamePasswordToken(userDto.getUsername(), userDto.getPassword());
        subject.login(token);
        // access_token
        String access_token = UUID.randomUUID().toString();
        // 放入缓存
        accessTokenService.put(access_token, userDto.getUsername());
        // 返回信息
        resultDto.setCode(HttpStatus.OK.value());
        resultDto.setMessage("认证成功");
        resultDto.setResult(access_token);
        return resultDto;
    }

}
