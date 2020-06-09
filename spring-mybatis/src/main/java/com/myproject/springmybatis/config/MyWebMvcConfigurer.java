package com.myproject.springmybatis.config;

import com.myproject.springmybatis.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: Xiaofei
 * @DATE: 2020/6/8 16:26
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**").addPathPatterns("/")
                .excludePathPatterns("/login", "/index.html","/register","/success")
        .excludePathPatterns("/assets/**")
        .excludePathPatterns("/js/**")
        .excludePathPatterns("/font/**")
        .excludePathPatterns("/css/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("login");
    }
}
