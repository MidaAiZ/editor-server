package com.mida.chromeext.resolver;

import com.alibaba.fastjson.JSON;
import com.mida.chromeext.annotation.CurrentAdmin;
import com.mida.chromeext.pojo.Admin;
import com.mida.chromeext.pojo.User;
import com.mida.chromeext.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 类LoginUserHandlerMethodArgumentResolver的功能描述:
 * 要想 @loginUser 起作用，需要编写一个配套解析器，做法是实现 spring 提供的 HandlerMethodArgumentResolver 接口。
 */
@Component
public class LoginAdminHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private AdminService adminService;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(Admin.class) && parameter.hasParameterAnnotation(CurrentAdmin.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest webRequest, WebDataBinderFactory factory) throws Exception {
        Admin admin;
        Object subject = SecurityUtils.getSubject().getPrincipal();
        if (subject instanceof Admin) {
            admin = (Admin) subject;
        } else {
            admin = JSON.parseObject(JSON.toJSON(subject).toString(), Admin.class);
        }
        return admin;
    }
}
