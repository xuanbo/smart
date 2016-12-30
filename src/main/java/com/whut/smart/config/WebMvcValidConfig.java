package com.whut.smart.config;

import com.whut.smart.support.valid.GlobalValidAdvice;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * MVC数据校验
 *
 * Created by null on 2016/12/30.
 */
@Configuration
public class WebMvcValidConfig extends WebMvcConfigurerAdapter {

    /**
     * MessageSource
     * bean 的名字一定要是 messageSource ，因为源码是写死了。。
     *
     * @return ReloadableResourceBundleMessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setDefaultEncoding(Constants.CHARACTER_ENCODING);
        // ReloadableResourceBundleMessageSource要指定 classpath: 前缀
        messageSource.setBasename("classpath:message/valid");
        return messageSource;
    }

    /**
     * 数据校验
     *
     * @return LocalValidatorFactoryBean
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validatorFactoryBean = new LocalValidatorFactoryBean();
        validatorFactoryBean.setProviderClass(HibernateValidator.class);
        validatorFactoryBean.setValidationMessageSource(messageSource());
        return validatorFactoryBean;
    }

    /**
     * 注册全局数据校验Bean
     *
     * @return GlobalValidAdvice
     */
    @Bean
    public GlobalValidAdvice globalValidAdvice() {
        return new GlobalValidAdvice();
    }

}
