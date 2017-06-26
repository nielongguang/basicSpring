package com.cn.study.basicSpring.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

/**
 * Created by Administrator on 2017/6/26.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.cn.study.basicSpring")
public class SpringConfiguration {

    @Bean //内部资源视图解析器 配置 这是springmvc的核心渲染机制
    public InternalResourceViewResolver viewResolver()
    {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        //配置前后缀
        viewResolver.setPrefix("/WEB-INF/classes/views");
        viewResolver.setSuffix(".jsp");
        //配置渲染方式.这里是Jstl 可以采用freemark或者其他静态模板的方式
        viewResolver.setViewClass(JstlView.class);
        return viewResolver;
    }
}
