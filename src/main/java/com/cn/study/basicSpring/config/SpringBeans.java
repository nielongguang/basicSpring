package com.cn.study.basicSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

/**
 * Created by Administrator on 2017/6/26.
 */

@Configuration
public class SpringBeans {

    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxInMemorySize(1000000);
        return multipartResolver;
    }





}
