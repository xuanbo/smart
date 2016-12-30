package com.whut.smart.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Druid DataSource Configuration
 *
 * 通常来说，只需要修改initialSize、minIdle、maxActive。
 * 如果用Oracle，则把poolPreparedStatements配置为true，mysql可以配置为false。
 * 分库分表较多的数据库，建议配置为false。
 *
 * Created by null on 2016/12/30.
 */
@Configuration
public class AppDruidConfig {

    private static final Logger log = LoggerFactory.getLogger(AppDruidConfig.class);

    @Value("${mysql.druid.driverClass}")
    private String driverClass;

    @Value("${mysql.druid.jdbcUrl}")
    private String jdbcUrl;

    @Value("${mysql.druid.username}")
    private String username;

    @Value("${mysql.druid.password}")
    private String password;

    // 配置初始化大小
    @Value("${mysql.druid.initialSize}")
    private int initialSize;

    // 配置初始化最小
    @Value("${mysql.druid.minIdle}")
    private int minIdle;

    // 配置初始化最大
    @Value("${mysql.druid.maxActive}")
    private int maxActive;

    // 获取连接等待超时的时间
    @Value("${mysql.druid.maxWait}")
    private long maxWait;

    // 间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
    @Value("${mysql.druid.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    // 一个连接在池中最小生存的时间，单位是毫秒
    @Value("${mysql.druid.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;

    @Value("${mysql.druid.validationQuery}")
    private String validationQuery;

    @Value("${mysql.druid.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value("${mysql.druid.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${mysql.druid.testOnReturn}")
    private Boolean testOnReturn;

    // 是否打开PSCache
    @Value("${mysql.druid.poolPreparedStatements}")
    private Boolean poolPreparedStatements;

    // 指定每个连接上PSCache的大小
    @Value("${mysql.druid.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    // 监控统计拦截的filters
    @Value("${mysql.druid.filters}")
    private String filters;

    @Bean
    public DataSource druidDataSource() throws SQLException {
        log.debug(driverClass);
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(driverClass);
        dataSource.setUrl(jdbcUrl);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setInitialSize(initialSize);
        dataSource.setMinIdle(minIdle);
        dataSource.setMaxActive(maxActive);
        dataSource.setMaxWait(maxWait);
        dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        dataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        dataSource.setValidationQuery(validationQuery);
        dataSource.setTestWhileIdle(testWhileIdle);
        dataSource.setTestOnBorrow(testOnBorrow);
        dataSource.setTestOnReturn(testOnReturn);
        dataSource.setPoolPreparedStatements(poolPreparedStatements);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        dataSource.setFilters(filters);
        return dataSource;
    }

}
