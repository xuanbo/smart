package com.whut.smart.support.shiro.oAuth2.service;

/**
 * 加密
 *
 * Created by null on 2017/1/1.
 */
public interface EncryptService {

    String encryptPassword(String password, String salt);
}
