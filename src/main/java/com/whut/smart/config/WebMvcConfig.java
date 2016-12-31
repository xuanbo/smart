package com.whut.smart.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web Mvc Configuration
 *
 * WebMvcValidConfig要在WebMvcExceptionConfig之前
 *
 * Created by null on 2016/12/30.
 */
@Configuration
@EnableWebMvc
@Import({WebMvcValidConfig.class, WebMvcCorsConfig.class, WebMvcExceptionConfig.class, WebMvcJsonConfig.class})
@ComponentScan(basePackages = {"com.whut.smart.controller"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(WebMvcConfig.class);


    /**
     * 相当于：<mvc:default-servlet-handler />
     *
     * @param configurer DefaultServletHandlerConfigurer
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

}
