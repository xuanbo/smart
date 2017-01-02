package com.whut.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Root ApplicationContext
 *
 * Created by null on 2016/12/30.
 */
@Configuration
@EnableAspectJAutoProxy
@EnableTransactionManagement
@Import({AppMybatisConfig.class, AppJdbcTemplateConfig.class, AppDruidConfig.class})
@ImportResource({"classpath:config/spring-shiro.xml"})
//@ImportResource({"classpath:config/oAuth2/spring-shiro.xml"})
@ComponentScan(basePackages = {"com.whut.smart.dao", "com.whut.smart.service"})
@PropertySource("classpath:application.properties")
public class AppConfig {

    /**
     * PropertySourcesPlaceHolderConfigurer Bean only required for @Value("{}") annotations.
     * Remove this bean if you are not using @Value annotations for injecting properties.
     *
     * 在@Configuration POJO类中可能有的获取到Null，这是可以使用Environment获取
     *
     * @return PropertySourcesPlaceholderConfigurer
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
