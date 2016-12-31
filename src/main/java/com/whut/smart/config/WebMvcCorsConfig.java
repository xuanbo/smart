package com.whut.smart.config;

import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MVC跨域配置
 *
 * Created by null on 2016/12/30.
 */
@Configuration
public class WebMvcCorsConfig extends WebMvcConfigurerAdapter implements EnvironmentAware {

    private Environment env;

    @Override
    public void setEnvironment(Environment environment) {
        this.env = environment;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(env.getProperty("mvc.cors.mapping")) // /api/**
                .allowedOrigins(env.getProperty("mvc.cors.allowedOrigins")) // http:www.xx.xx
                .allowedMethods(env.getProperty("mvc.cors.allowedMethods").split(","))
                .allowCredentials(Boolean.parseBoolean(env.getProperty("mvc.cors.allowCredentials")))
                .maxAge(Long.parseLong(env.getProperty("mvc.cors.maxAge")));
    }

}
