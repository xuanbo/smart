package com.whut.smart.config;

import com.whut.smart.support.exception.GlobalJsonHandlerExceptionResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * Mvc异常
 *
 * Created by null on 2016/12/30.
 */
@Configuration
public class WebMvcExceptionConfig extends WebMvcConfigurerAdapter {

    /**
     * 注册自定义HandlerExceptionResolver Bean
     *
     * @param exceptionResolvers List<HandlerExceptionResolver>
     */
    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(globalJsonHandlerExceptionResolver());
    }

    /**
     * 自定义HandlerExceptionResolver Bean
     * 返回Json信息
     *
     * @return GlobalJsonHandlerExceptionResolver
     */
    @Bean
    public HandlerExceptionResolver globalJsonHandlerExceptionResolver() {
        return new GlobalJsonHandlerExceptionResolver();
    }
}
