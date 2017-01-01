package com.whut.smart.support.shiro.oAuth2.service.impl;

import com.whut.smart.support.shiro.oAuth2.dao.OAuthClientDao;
import com.whut.smart.support.shiro.oAuth2.dto.OAuthClientDto;
import com.whut.smart.support.shiro.oAuth2.service.OAuthClientService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by null on 2017/1/1.
 */
@Service
@Transactional
public class OAuthClientServiceImpl implements OAuthClientService {

    @Resource
    private OAuthClientDao oAuthClientDao;

    @Override
    public int insert(OAuthClientDto oAuthClientDto) {
        return oAuthClientDao.insert(oAuthClientDto);
    }

    @Override
    public int update(OAuthClientDto oAuthClientDto) {
        return oAuthClientDao.update(oAuthClientDto);
    }

    @Override
    public int delete(Long id) {
        return oAuthClientDao.delete(id);
    }

    @Override
    public OAuthClientDto getById(Long id) {
        return oAuthClientDao.getById(id);
    }

    @Override
    public List<OAuthClientDto> getAll() {
        return oAuthClientDao.getAll();
    }

    @Override
    public OAuthClientDto getByClientId(String clientId) {
        return oAuthClientDao.getByClientId(clientId);
    }

    @Override
    public OAuthClientDto getByClientSecret(String clientSecret) {
        return oAuthClientDao.getByClientSecret(clientSecret);
    }
}
