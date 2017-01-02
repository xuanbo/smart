package com.whut.smart.service.impl;

import com.whut.smart.service.AccessTokenService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 将access_token作为key，username作为value放入cache
 * 这里的CacheManager是shiro提供的，不是spring的，因此不能使用@Cacheable等注解
 *
 * Created by null on 2017/1/2.
 */
@Service("accessTokenService")
public class AccessTokenServiceImpl implements AccessTokenService {

    private Cache<String, String> cache;

    @Autowired
    public AccessTokenServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("accessTokenCache");
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return getUsername(accessToken) != null;
    }

    @Override
    public String getUsername(String accessToken) {
        return cache.get(accessToken);
    }

    @Override
    public void put(String accessToken, String username) {
        cache.put(accessToken, username);
    }
}
