package com.whut.smart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Mvc MessageConverter配置
 *
 * Created by null on 2016/12/30.
 */
@Configuration
public class WebMvcJsonConfig extends WebMvcConfigurerAdapter {

    /**
     * 自定义MessageConverter
     * 因为默认的StringHttpMessageConverter编码不是UTF-8
     *
     * @param converters List<HttpMessageConverter<?>>
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 注册修改后的StringHttpMessageConverter
        converters.add(stringHttpMessageConverter());
        // 添加HttpMessageConverter，因为覆盖了本方法，需要自己添加，默认会自动注册HttpMessageConverter
        converters.add(gsonHttpMessageConverter());
    }

    /**
     * StringHttpMessageConverter默认的编码是ISO-8859-1，改为UTF-8
     *
     * @return StringHttpMessageConverter
     */
    @Bean
    public StringHttpMessageConverter stringHttpMessageConverter() {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        // 修改默认的编码
        stringHttpMessageConverter.setDefaultCharset(Constants.CHARACTER_CHARSET);
        List<MediaType> mediaTypes = new ArrayList<>();
        // 修改MediaType为支持Content-Type:"text/plain;charset=UTF-8"
        mediaTypes.add(new MediaType("text", "plain", Constants.CHARACTER_CHARSET));
        stringHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        return stringHttpMessageConverter;
    }

    /**
     * GsonHttpMessageConverter Bean
     *
     * @return GsonHttpMessageConverter
     */
    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter() {
        return new GsonHttpMessageConverter();
    }
}
