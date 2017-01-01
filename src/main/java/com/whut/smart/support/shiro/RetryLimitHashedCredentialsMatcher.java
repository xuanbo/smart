package com.whut.smart.support.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义加密密码和验证密码服务，认证失败多次锁定账户
 *
 * Created by null on 2017/1/1.
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    /*
     * EhCache中缓存的key
     */
    private static final String PASSWORD_RETRY_CACHE_KEY = "passwordRetryCache";

    /*
     * 登录尝试缓存 key: username; value: 失败尝试次数
     */
    private Cache<String, AtomicInteger> passwordRetryCache;

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache(PASSWORD_RETRY_CACHE_KEY);
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        // 获取用户名
        String username = (String) token.getPrincipal();
        // 获取已经失败的尝试次数
        AtomicInteger retryCount = passwordRetryCache.get(username);
        // 不存在则初始化为0
        if (retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        // 做一次尝试登录，如果超过5则抛出ExcessiveAttemptsException
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }
        boolean matches = super.doCredentialsMatch(token, info);
        // 认证成功则删除缓存
        if (matches) {
            passwordRetryCache.remove(username);
        }
        return matches;
    }
}
