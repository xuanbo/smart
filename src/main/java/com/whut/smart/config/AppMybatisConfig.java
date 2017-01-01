package com.whut.smart.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;

/**
 * Spring-Mybatis Configuration
 * 在@Value注入值前实例化了，采用构造方法注入
 * 而MapperScannerConfigurer实例化太快，通过实现EnvironmentAware接口从Environment中获取外部属性值
 *
 * Created by null on 2016/12/30.
 */
@Configuration
public class AppMybatisConfig implements EnvironmentAware {

    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    /**
     * SqlSessionFactory
     *
     * @param druidDataSource druidDataSourceBean，采用构造方法注入
     * @param mapperLocations XXMapper.xml文件的位置
     * @param typeAliasesPackage 设置别名的包路径
     * @return SqlSessionFactoryBean
     * @throws IOException I/O exception
     */
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(
            DataSource druidDataSource,
            @Value("${mybatis.mapperLocations}") String mapperLocations,
            @Value("${mybatis.typeAliasesPackage}") String typeAliasesPackage) throws IOException {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(druidDataSource);
        // 扫描*mapper.xml
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(resolver.getResources(mapperLocations));
        // 设置别名
        sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
        return sessionFactoryBean;
    }

    /**
     * 扫描dao接口
     *
     * @return MapperScannerConfigurer
     */
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        // 扫描所有接口，然后创建各自接口的动态代理类。
        mapperScannerConfigurer.setBasePackage(env.getProperty("mybatis.basePackage"));
        return mapperScannerConfigurer;
    }

    /**
     * 事务
     *
     * @return DataSourceTransactionManager
     */
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager(DataSource druidDataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(druidDataSource);
        return transactionManager;
    }
}
