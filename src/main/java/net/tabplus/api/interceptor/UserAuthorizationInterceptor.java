package net.tabplus.api.interceptor;


import net.tabplus.api.annotation.LoginRequired;
import net.tabplus.api.exception.MyException;
import net.tabplus.api.modules.service.UserService;
import net.tabplus.api.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 类AuthorizationInterceptor的功能描述:
 * 权限(Token)验证
 */
@Component
public class UserAuthorizationInterceptor extends HandlerInterceptorAdapter {
    public static final String CURRENT_USER = "userId";
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals(HttpMethod.OPTIONS)) {
            return true;
        }

        LoginRequired annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(LoginRequired.class);
        } else {
            // 如果不是映射到方法直接通过
            return true;
        }
        // 如果不需要登陆验证，直接通过
        if (annotation == null) {
            return true;
        }

        // 需要验证，获取用户凭证
        String token = "";
        // 通过cookie获取用户token
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(jwtUtils.getHeader())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
//        // 通过请求报头获取token
//        if (StringUtils.isBlank(token)) {
//            token = request.getHeader(jwtUtils.getHeader());
//        }
//        // 通过参数获取token
//        if (StringUtils.isBlank(token)) {
//            token = request.getParameter(jwtUtils.getHeader());
//        }

        /**
         * 要求强制登录选项
         * 未登录时就抛出异常
         */
        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims != null && jwtUtils.isTokenExpired(claims.getExpiration())) {
            claims = null;
        }
        //凭证为空 && 要求强制登录
        if (annotation.force() && StringUtils.isBlank(token)) { throw new MyException("无token，请重新登录"); }

        //验证用户信息
//        User user = userService.getUserById(Integer.valueOf(claims.getSubject()));
//        if (user == null) {
//            throw new RuntimeException("用户不存在，请重新登录");
//        }

        //设置userId到request里，后续根据userId，获取用户信息
        if (claims != null) {
            request.setAttribute(CURRENT_USER, claims.getSubject());
        }

        return true;
    }
}
