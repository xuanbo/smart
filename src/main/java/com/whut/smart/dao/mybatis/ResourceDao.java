package com.whut.smart.dao.mybatis;

import com.whut.smart.dto.ResourceDto;

import java.util.List;

/**
 * Created by null on 2017/1/2.
 */
public interface ResourceDao {

    List<ResourceDto> getAll();
}
