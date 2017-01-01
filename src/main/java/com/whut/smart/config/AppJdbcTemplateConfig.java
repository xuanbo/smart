package com.whut.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 * JdbcTemplate Configuration
 *
 * Created by null on 2017/1/1.
 */
@Configuration
public class AppJdbcTemplateConfig {

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource druidDataSource) {
        return new JdbcTemplate(druidDataSource);
    }
}
