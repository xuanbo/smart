package com.whut.smart.service;

/**
 * 自定义AccessToken Service
 *
 * Created by null on 2017/1/2.
 */
public interface AccessTokenService {

    /**
     * 存在则返回true
     *
     * @param accessToken 生成的字符串
     * @return 存在则返回true
     */
    boolean checkAccessToken(String accessToken);

    /**
     * 根据accessToken获取缓存中的用户名
     *
     * @param accessToken accessToken
     * @return 用户名
     */
    String getUsername(String accessToken);

    /**
     * 放入accessToken到缓存
     *
     * @param accessToken accessToken
     * @param username 用户名
     */
    void put(String accessToken, String username);

}
