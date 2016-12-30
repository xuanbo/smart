package com.whut.smart.dao.mybatis;

import com.whut.smart.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by null on 2016/12/30.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class})
public class UserDaoTest {

    private static final Logger log = LoggerFactory.getLogger(UserDaoTest.class);

    @Resource
    private UserDao userDao;

    @Test
    public void getByUsername() {
        String username = "admin";
        log.debug("admin info is {}", userDao.getByUsername(username));
    }
}
