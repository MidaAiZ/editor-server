package net.tabplus.api.config;

import net.tabplus.api.resolver.LoginUserHandlerMethodArgumentResolver;
import net.tabplus.api.interceptor.LoginRecordInterceptor;
import net.tabplus.api.interceptor.UserAuthorizationInterceptor;
import net.tabplus.api.resolver.LoginAdminHandlerMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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
        registry.addInterceptor(loginRecordInterceptor).addPathPatterns("/users/profile", "/login");
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