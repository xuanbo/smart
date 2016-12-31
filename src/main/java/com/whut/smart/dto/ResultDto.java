package com.whut.smart.dto;

/**
 * 返回给前台数据
 *
 * Created by null on 2016/12/31.
 */
public class ResultDto<T> {

    private Integer code;
    private String message;
    private T result;

    public Integer getCode() {
        return code;
    }

    public ResultDto setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultDto setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getResult() {
        return result;
    }

    public ResultDto setResult(T result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "ResultDto{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", result=" + result +
                '}';
    }
}
