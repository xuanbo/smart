package com.whut.smart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MVC跨域配置
 *
 * Created by null on 2016/12/30.
 */
@Configuration
public class WebMvcCorsConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // /api/**
                .allowedOrigins("http://localhost:8080") // http:www.xx.xx
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(false).maxAge(3600);
    }
}
