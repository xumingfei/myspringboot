package com.myproject.springmybatis.config;

import com.myproject.springmybatis.interceptor.LoginHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
        .excludePathPatterns("/images/**")
        .excludePathPatterns("/css/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/").setViewName("forward:/index");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/");
        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/");
    }

    //使用CommonsMultipart接收附件时需要增加该Bean,否则会失败
//    @Bean
//    public CommonsMultipartResolver multipartResolver() {
//        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
//        multipartResolver.setMaxUploadSize(20971520);
//        multipartResolver.setMaxInMemorySize(1048576);
//        return multipartResolver;
//    }
}
