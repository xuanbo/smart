package com.whut.smart.support.shiro.oAuth2.service.impl;

import com.whut.smart.support.shiro.oAuth2.service.EncryptService;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Service;

/**
 * md5加密 hash3次
 *
 * Created by null on 2017/1/1.
 */
@Service
public class EncryptServiceImpl implements EncryptService {

    private static final String algorithmName = "md5";

    private static final int hashIterations = 3;

    @Override
    public String encryptPassword(String password, String salt) {
        return new SimpleHash(algorithmName, password, salt, hashIterations).toHex();
    }
}
