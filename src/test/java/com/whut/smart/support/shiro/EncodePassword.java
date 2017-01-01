package com.whut.smart.support.shiro;

import com.whut.smart.config.Constants;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * shiro 加密
 *
 * Created by null on 2017/1/1.
 */
public class EncodePassword {

    private static final Logger log = LoggerFactory.getLogger(EncodePassword.class);

    @Test
    public void encode() {
        String algorithmName = "md5";
        String username = "user1234";
        String password = "user1234";
        String salt = username + Constants.SALT;
        int hashIterations = 3;
        SimpleHash hash = new SimpleHash(algorithmName, password, salt, hashIterations);
        String encodedPassword = hash.toHex();
        log.debug("加密后的字符串 => {}", encodedPassword);
    }
}
