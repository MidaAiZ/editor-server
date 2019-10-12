package com.mida.chromeext.config;

import com.mida.chromeext.interceptor.UserAuthorizationInterceptor;
import com.mida.chromeext.resolver.LoginAdminHandlerMethodArgumentResolver;
import com.mida.chromeext.resolver.LoginUserHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 类WebMvcConfig的功能描述:
 * MVC配置
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private UserAuthorizationInterceptor userAuthorizationInterceptor;
    @Autowired
    private LoginUserHandlerMethodArgumentResolver loginUserHandlerMethodArgumentResolver;
    @Autowired
    private LoginAdminHandlerMethodArgumentResolver loginAdminHandlerMethodArgumentResolver;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthorizationInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 获取当前登录的用户
        argumentResolvers.add(loginUserHandlerMethodArgumentResolver);
        // 获取当前登录的管理员
        argumentResolvers.add(loginAdminHandlerMethodArgumentResolver);
    }
}