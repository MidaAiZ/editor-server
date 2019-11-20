package net.tabplus.api.resolver;

import com.alibaba.fastjson.JSON;
import net.tabplus.api.annotation.CurrentAdmin;
import net.tabplus.api.modules.pojo.Admin;
import net.tabplus.api.modules.service.AdminService;
import org.apache.shiro.SecurityUtils;
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
