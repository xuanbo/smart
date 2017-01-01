package com.whut.smart.support.shiro.oAuth2.service.impl;

import com.whut.smart.support.shiro.oAuth2.service.OAuthClientService;
import com.whut.smart.support.shiro.oAuth2.service.OAuthService;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * OAuth服务
 *
 * Created by null on 2017/1/1.
 */
@Service
@Transactional
public class OAuthServiceImpl implements OAuthService {

    private Cache<String, String> cache;

    @Resource
    private OAuthClientService oAuthClientService;

    @Autowired
    public OAuthServiceImpl(CacheManager cacheManager) {
        this.cache = cacheManager.getCache("code-cache");
    }

    @Override
    public void addAuthCode(String authCode, String username) {
        cache.put(authCode, username);
    }

    @Override
    public void addAccessToken(String accessToken, String username) {
        cache.put(accessToken, username);
    }

    @Override
    public boolean checkAuthCode(String authCode) {
        return cache.get(authCode) != null;
    }

    @Override
    public boolean checkAccessToken(String accessToken) {
        return cache.get(accessToken) != null;
    }

    @Override
    public String getUsernameByAuthCode(String authCode) {
        return cache.get(authCode);
    }

    @Override
    public String getUsernameByAccessToken(String accessToken) {
        return cache.get(accessToken);
    }

    @Override
    public long getExpireIn() {
        return 3600L;
    }

    @Override
    public boolean checkClientId(String clientId) {
        return oAuthClientService.getByClientId(clientId) != null;
    }

    @Override
    public boolean checkClientSecret(String clientSecret) {
        return oAuthClientService.getByClientSecret(clientSecret) != null;
    }
}
