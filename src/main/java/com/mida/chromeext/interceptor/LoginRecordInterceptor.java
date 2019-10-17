package com.mida.chromeext.interceptor;


import java.awt.*;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mida.chromeext.modules.pojo.LoginRecord;
import com.mida.chromeext.modules.service.LoginRecordService;
import com.mida.chromeext.utils.Constant;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.mida.chromeext.modules.service.UserService;
import com.mida.chromeext.utils.JwtUtils;

import io.jsonwebtoken.Claims;

/**
 *
 *
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

        // 获取 登录记录 cookie
        String loginToken = "";
        String userToken = "";
        Integer userId = null;
        // 通过cookie获取用户token
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if(cookie == null){
                    break;
                }
                if (cookie.getName().equals(jwtUtils.getHeader())) {
                    userToken = cookie.getValue();
                }
                if (cookie.getName().equals(Constant.LOGIN_RECORD_TOKEN_NAME)) {
                    loginToken = cookie.getValue();
                }
            }
        }
        // 如果用户登录了 记录其id
        if(StringUtils.isNotBlank(userToken)){
        Claims claims = jwtUtils.getClaimByToken(userToken);
        userId = Integer.parseInt(claims.getSubject());
        }

        //验证token
        // loginToken为空 或者 超过一小时，记录登录
        if(StringUtils.isBlank(loginToken) ||
                System.currentTimeMillis() - Long.parseLong(loginToken) > Constant.LOGIN_RECORD_GAP_TIME){
            // 更新 cookie
            createCookie(Constant.LOGIN_RECORD_TOKEN_NAME,userId,request,response);
        }
        // 否则什么也不做
        return true;
    }

    private Cookie createCookie(String header,Integer userId, HttpServletRequest request, HttpServletResponse response){
        String token = String.valueOf(System.currentTimeMillis());
        Cookie cookie = new Cookie(header, token);
        cookie.setHttpOnly(true);
        cookie.setMaxAge((int) jwtUtils.getExpire());
        response.addCookie(cookie);
        // 入库
        LoginRecord record = new LoginRecord();
        if(userId != null){
            record.setUid(userId);
        }
        record.setIp(request.getRemoteAddr());
        record.setUa(request.getHeader("user-agent"));
        loginRecordService.addRecord(record);
        return cookie;
    }
}
