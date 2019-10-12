package com.mida.chromeext.exception;

import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * shiro管理员权限认证报错拦截处理
 */
@ControllerAdvice
public class AdminAuthException {
    // 对应处理shiro的AuthorizationException（认证异常，没有操作权限）
    @ExceptionHandler(value = AuthorizationException.class)
    public void defaultErrorHandler(HttpServletRequest request, HttpServletResponse response, Exception e) throws Exception {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(JSONObject.toJSONString(Result.error(ResultCode.FORBIDDEN.code(), "Permission denied")));
    }
}
