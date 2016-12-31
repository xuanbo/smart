package com.whut.smart.support.exception;

import com.whut.smart.dto.ResultDto;
import com.whut.smart.support.valid.GlobalValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理
 *
 * Created by null on 2016/12/31.
 */
@ControllerAdvice
public class GlobalControllerExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalValidation.class);


    /**
     * HttpMessageNotReadableException处理
     * 无法正确解析HttpMessage，可能是json格式错误
     *
     * @param e HttpMessageNotReadableException
     * @return ResultDto
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto<?> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
        log.debug("HttpMessageNotReadableException处理。原因 => {}", e.getMessage());
        ResultDto<String> errorDto = new ResultDto<>();
        errorDto.setCode(HttpStatus.BAD_REQUEST.value());
        errorDto.setMessage("无法正确解析HttpMessage");
        return errorDto;
    }


    /**
     * HttpMediaTypeNotSupportedException处理
     * 不支持的HttpMediaType，Content-Type不正确
     *
     * @param e HttpMediaTypeNotSupportedException
     * @return ResultDto
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto<?> httpMediaTypeNotSupportedExceptionHandler(HttpMediaTypeNotSupportedException e) {
        log.debug("HttpMediaTypeNotSupportedException处理。原因 => {}", e.getMessage());
        ResultDto<String> errorDto = new ResultDto<>();
        errorDto.setCode(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
        errorDto.setMessage("不支持的Content-Type");
        return errorDto;
    }


    /**
     * HttpRequestMethodNotSupportedException 处理
     * 不支持当前请求的方法
     *
     * @param e HttpRequestMethodNotSupportedException
     * @return ResultDto
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto<?> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
        log.debug("HttpRequestMethodNotSupportedException处理。原因 => {}", e.getMessage());
        ResultDto<String> errorDto = new ResultDto<>();
        errorDto.setCode(HttpStatus.BAD_REQUEST.value());
        errorDto.setMessage("不支持的请求的方法");
        return errorDto;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResultDto<?> exceptionHandler(Exception e) {
        log.debug("Exception处理。原因 => {}", e.getMessage());
        ResultDto<String> errorDto = new ResultDto<>();
        errorDto.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDto.setMessage("出现错误");
        return errorDto;
    }
}
