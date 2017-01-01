package com.whut.smart.support.shiro.oAuth2.service;

/**
 * Created by null on 2017/1/1.
 */
public interface OAuthService {

    // 添加 auth code
    void addAuthCode(String authCode, String username);
    // 添加 access token
    void addAccessToken(String accessToken, String username);

    // 验证auth code是否有效
    boolean checkAuthCode(String authCode);
    // 验证access token是否有效
    boolean checkAccessToken(String accessToken);

    String getUsernameByAuthCode(String authCode);
    String getUsernameByAccessToken(String accessToken);

    //auth code / access token 过期时间
    long getExpireIn();

    public boolean checkClientId(String clientId);
    public boolean checkClientSecret(String clientSecret);
}
