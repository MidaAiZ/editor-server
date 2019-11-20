package net.tabplus.api.interceptor;


import net.tabplus.api.utils.Constant;
import net.tabplus.api.utils.JwtUtils;
import net.tabplus.api.modules.pojo.LoginRecord;
import net.tabplus.api.modules.service.LoginRecordService;
import net.tabplus.api.utils.LocaleHelper;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author lihaoyu
 * @date 2019/10/17 16:16
 */
@Component
public class LoginRecordInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private LoginRecordService loginRecordService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals(HttpMethod.OPTIONS)) {
            return true;
        }

        // 获取 登录记录 cookie
        String loginToken = "";
        String userToken = "";
        String clientToken = "";
        Integer userId = null;
        // 通过cookie获取用户token
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie == null) {
                    break;
                }
                if (cookie.getName().equals(jwtUtils.getHeader())) {
                    userToken = cookie.getValue();
                    continue;
                }
                if (cookie.getName().equals(Constant.LOGIN_RECORD_TOKEN_NAME)) {
                    loginToken = cookie.getValue();
                    continue;
                }
                if (cookie.getName().equals(Constant.TOEKN_CLIENT)) {
                    clientToken = cookie.getValue();
                    continue;
                }
            }
        }

        // 如果没有 Client Token  是第一次
        if (StringUtils.isBlank(clientToken)) {
            Cookie clientCookie = new Cookie(Constant.TOEKN_CLIENT, UUID.randomUUID().toString().replaceAll("-", ""));
            clientCookie.setHttpOnly(true);
            clientCookie.setMaxAge(Integer.MAX_VALUE);
            response.addCookie(clientCookie);
            return true;
        }

        // 如果用户登录了 记录其id
        if (StringUtils.isNotBlank(userToken)) {
            Claims claims = jwtUtils.getClaimByToken(userToken);
            userId = Integer.parseInt(claims.getSubject());
        }

        //验证token
        // loginToken为空 或者 超过一小时，记录登录
        if (StringUtils.isBlank(loginToken) || System.currentTimeMillis() - Long.parseLong(loginToken) > Constant.LOGIN_RECORD_GAP_TIME) {
            long a = System.currentTimeMillis() - Long.parseLong(loginToken);
            int b = Constant.LOGIN_RECORD_GAP_TIME;
            // 更新 cookie
            createCookie(Constant.LOGIN_RECORD_TOKEN_NAME, userId, clientToken, request, response);
        }
        // 否则什么也不做
        return true;
    }

    private Cookie createCookie(String header, Integer userId, String clientToken, HttpServletRequest request, HttpServletResponse response) {
        String token = String.valueOf(System.currentTimeMillis());
        Cookie cookie = new Cookie(header, token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) jwtUtils.getExpire());
        response.addCookie(cookie);
        // 入库
        LoginRecord record = new LoginRecord();
        if (userId != null) {
            record.setUid(userId);
        }
        record.setClientId(clientToken);
        record.setIp(request.getRemoteAddr());
        record.setUa(request.getHeader("user-agent"));
        record.setCountryCode(LocaleHelper.getContextCountryCode(request));
        loginRecordService.addRecordCache(record);
        return cookie;
    }
}
