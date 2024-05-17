package com.zone.config;

import com.zone.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//WebConfig
@Configuration
@Slf4j
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final LoginCheckInterceptor loginCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // /** 表示所有资源
        //注册了一个拦截器
        registry.addInterceptor(loginCheckInterceptor).addPathPatterns("/**");
        log.info("注册拦截器成功");
    }
}