package com.whut.smart.config;

import com.whut.smart.support.exception.GlobalControllerExceptionHandler;
import com.whut.smart.support.shiro.AuthExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Mvc异常
 *
 * Created by null on 2016/12/30.
 */
@Configuration
public class WebMvcExceptionConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册shiro认证异常处理 Bean
     * @return AuthExceptionHandler
     */
    @Bean
    public AuthExceptionHandler authExceptionHandler() {
        return new AuthExceptionHandler();
    }

    /**
     * 注册全局Controller异常处理 Bean
     * @return GlobalControllerExceptionHandler
     */
    @Bean
    public GlobalControllerExceptionHandler globalExceptionAdvice() {
        return new GlobalControllerExceptionHandler();
    }

}
