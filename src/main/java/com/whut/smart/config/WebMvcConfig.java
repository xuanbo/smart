package com.whut.smart.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Web Mvc Configuration
 *
 * WebMvcValidConfig要在WebMvcExceptionConfig之前
 *
 * Created by null on 2016/12/30.
 */
@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@Import({WebMvcValidConfig.class, WebMvcCorsConfig.class, WebMvcExceptionConfig.class, WebMvcJsonConfig.class})
@ComponentScan(basePackages = {"com.whut.smart.controller"})
//@ComponentScan(basePackages = {"com.whut.smart.controller", "com.whut.smart.support.shiro.oAuth2.controller"})
public class WebMvcConfig extends WebMvcConfigurerAdapter implements EnvironmentAware {

    private static final Logger log = LoggerFactory.getLogger(WebMvcConfig.class);

    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    /**
     * 相当于：<mvc:default-servlet-handler />
     *
     * @param configurer DefaultServletHandlerConfigurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     * jsp 视图解析
     *
     * @return InternalResourceViewResolver
     */
    @Bean
    public ViewResolver InternalResourceViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix(env.getProperty("mvc.view.prefix"));
        viewResolver.setSuffix(env.getProperty("mvc.view.suffix"));
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }

}
