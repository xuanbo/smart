package com.whut.smart.service.impl;

import com.whut.smart.dao.mybatis.ResourceDao;
import com.whut.smart.dto.RoleDto;
import com.whut.smart.service.ResourceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by null on 2017/1/2.
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Resource
    private ResourceDao resourceDao;

    @Transactional(readOnly = true)
    @Override
    public Map<String, String> getUrlRolesMatcher() {
        final Map<String, String> urlRolesMatcher = new HashMap<>();
        resourceDao.getAll().forEach(resourceDto -> {
            List<RoleDto> roleDtos = resourceDto.getRoleDtos();
            if (CollectionUtils.isEmpty(roleDtos)) {
                urlRolesMatcher.put(resourceDto.getUrl(), null);
            } else {
                String[] roleNames = new String[roleDtos.size()];
                for (int i = 0; i < roleDtos.size(); i++) {
                    roleNames[i] = roleDtos.get(i).getName();
                }
                urlRolesMatcher.put(resourceDto.getUrl(), String.join(",", roleNames));
            }
        });
        return urlRolesMatcher;
    }
}
