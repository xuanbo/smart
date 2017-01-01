package com.whut.smart.support.shiro.oAuth2.dao;

import com.whut.smart.support.shiro.oAuth2.dto.OAuthUserDto;

import java.util.List;

/**
 * OAuthUser CRUD
 *
 * Created by null on 2017/1/1.
 */
public interface OAuthUserDao {

    int insert(OAuthUserDto oAuthUserDto);
    int update(OAuthUserDto oAuthUserDto);
    int delete(Long id);
    OAuthUserDto getById(Long id);
    List<OAuthUserDto> getAll();
    OAuthUserDto getByUsername(String username);

}
