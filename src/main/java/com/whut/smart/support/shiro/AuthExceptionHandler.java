package com.whut.smart.support.shiro;

import com.whut.smart.dto.ResultDto;
import com.whut.smart.support.valid.GlobalValidation;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * shiro 认证异常处理
 *
 * Created by xinQing on 2017/1/1.
 */
@ControllerAdvice
public class AuthExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalValidation.class);


    /**
     * 处理用户名不存在异常
     *
     * @param e UnknownAccountException
     * @return ResultDto
     */
    @ExceptionHandler(UnknownAccountException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto<?> unknownAccountExceptionHandler(UnknownAccountException e) {
        log.debug("UnknownAccountException处理。原因 => {}", e.getMessage());
        ResultDto<String> errorDto = new ResultDto<>();
        errorDto.setCode(HttpStatus.UNAUTHORIZED.value());
        errorDto.setMessage("用户名不存在");
        return errorDto;
    }

    /**
     * 处理密码不正确异常
     *
     * @param e IncorrectCredentialsException
     * @return ResultDto
     */
    @ExceptionHandler(IncorrectCredentialsException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto<?> incorrectCredentialsExceptionHandler(IncorrectCredentialsException e) {
        log.debug("IncorrectCredentialsException处理。原因 => {}", e.getMessage());
        ResultDto<String> errorDto = new ResultDto<>();
        errorDto.setCode(HttpStatus.UNAUTHORIZED.value());
        errorDto.setMessage("密码不正确");
        return errorDto;
    }
}
