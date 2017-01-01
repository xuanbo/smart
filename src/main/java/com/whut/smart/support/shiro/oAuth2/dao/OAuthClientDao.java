package com.whut.smart.support.shiro.oAuth2.dao;

import com.whut.smart.support.shiro.oAuth2.dto.OAuthClientDto;

import java.util.List;

/**
 * OauthClient CRUD
 *
 * Created by null on 2017/1/1.
 */
public interface OAuthClientDao {

    int insert(OAuthClientDto oAuthClientDto);
    int update(OAuthClientDto oAuthClientDto);
    int delete(Long id);
    OAuthClientDto getById(Long id);
    List<OAuthClientDto> getAll();
    OAuthClientDto getByClientId(String clientId);
    OAuthClientDto getByClientSecret(String clientSecret);

}
