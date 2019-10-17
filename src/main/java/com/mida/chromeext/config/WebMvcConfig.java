package com.mida.chromeext.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mida.chromeext.interceptor.LoginRecordInterceptor;
import com.mida.chromeext.interceptor.UserAuthorizationInterceptor;
import com.mida.chromeext.resolver.LoginAdminHandlerMethodArgumentResolver;
import com.mida.chromeext.resolver.LoginUserHandlerMethodArgumentResolver;

/**
 * 类WebMvcConfig的功能描述:
 * MVC配置
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UserAuthorizationInterceptor userAuthorizationInterceptor;
    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;
    @Autowired
    private LoginAdminHandlerMethodArgumentResolver loginAdminHandlerMethodArgumentResolver;
    @Autowired
    private LoginRecordInterceptor loginRecordInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthorizationInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginRecordInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 获取当前登录的用户
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
        // 获取当前登录的管理员
        argumentResolvers.add(loginAdminHandlerMethodArgumentResolver);
    }
}