package com.whut.smart.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Web Mvc Configuration
 *
 * Created by null on 2016/12/30.
 */
@Configuration
@EnableWebMvc
@Import({WebMvcJsonConfig.class, WebMvcValidConfig.class, WebMvcCorsConfig.class, WebMvcExceptionConfig.class})
@ComponentScan(basePackages = {"com.whut.smart.controller"})
public class WebMvcConfig extends WebMvcConfigurerAdapter {

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
