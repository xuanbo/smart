package com.whut.smart.service;

import java.util.Map;

/**
 * Created by null on 2017/1/2.
 */
public interface ResourceService {

    /**
     * key => url
     * value => ROLE_XX, ROLE_YY
     *
     * @return
     */
    Map<String, String> getUrlRolesMatcher();
}
