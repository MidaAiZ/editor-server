package com.mida.chromeext.config;

import com.mida.chromeext.shiro.AdminRealm;
import com.mida.chromeext.shiro.ShiroLoginFilter;
import com.mida.chromeext.shiro.ShiroRedisCacheManage;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.Filter;

/**
 * 类ShiroConfig的功能描述:
 * Shiro配置
 * @auther hxy
 * @date 2017-11-15 21:50:12
 */
@Configuration
public class ShiroConfig {

//    @Bean("sessionManager")
//    public SessionManager sessionManager(CachingShiroSessionDao sessionDAO){
//        sessionDAO.setPrefix("shiro-session:");
//        //注意中央缓存有效时间要比本地缓存有效时间长
//        sessionDAO.setSeconds(1800);
//        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
//        //设置session过期时间为1小时(单位：毫秒)，默认为30分钟
//        sessionManager.setGlobalSessionTimeout(60 * 60 * 1000);
//        sessionManager.setSessionIdUrlRewritingEnabled(false);
//        sessionManager.setSessionValidationSchedulerEnabled(true);
//        sessionManager.setSessionDAO(sessionDAO);
//        return sessionManager;
//    }

    @Bean("securityManager")
    public SecurityManager securityManager(AdminRealm adminRealm, ShiroRedisCacheManage shiroRedisCacheManage) { // , SessionManager sessionManager
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(adminRealm);
//        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(shiroRedisCacheManage);
        return securityManager;
    }

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        //Shiro的核心安全接口,这个属性是必须的
        shiroFilter.setSecurityManager(securityManager);
        shiroFilter.setLoginUrl("manage/login");
        Map<String, Filter> filter = new LinkedHashMap<>();
        filter.put("authc",new ShiroLoginFilter());
        shiroFilter.setFilters(filter);
        // shiroFilter.setSuccessUrl("/index.html");
        // shiro不拦截资源
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/manage/login", "anon");
        filterMap.put("/**/*.css", "anon");
        filterMap.put("/**/*.js", "anon");
        filterMap.put("/*.html", "anon");
        filterMap.put("/**/*.html", "anon");
        filterMap.put("/fonts/**", "anon");
        filterMap.put("/images/**", "anon");
        filterMap.put("/statics/**", "anon");
        filterMap.put("/swagger/**", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/", "anon");
        // 设置拦截路径
        filterMap.put("/manage/**", "authc");
        shiroFilter.setFilterChainDefinitionMap(filterMap);

        return shiroFilter;
    }

    /**
     * 保证实现了Shiro内部lifecycle函数的bean执行
     * @return
     */
    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * AOP式方法级权限检查
     * @return
     */
//    注释，避免多次查询权限
//    @Bean
//    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
//        DefaultAdvisorAutoProxyCreator proxyCreator = new DefaultAdvisorAutoProxyCreator();
//        proxyCreator.setProxyTargetClass(true);
//        return proxyCreator;
//    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
