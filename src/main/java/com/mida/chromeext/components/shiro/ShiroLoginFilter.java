package com.mida.chromeext.components.shiro;

import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.utils.Result;
import com.mida.chromeext.utils.ResultCode;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.http.HttpMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShiroLoginFilter extends FormAuthenticationFilter {
    /**
     * 在访问controller前判断是否登录，返回json，不进行重定向。
     *
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        httpServletResponse.getWriter().write(JSONObject.toJSONString(Result.error(ResultCode.NOT_LOGIN.code(), "Admin not logged in")));
        return false;
    }

    /**
     * 拦截跨域options请求
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        if (request instanceof HttpServletRequest) {
            if (((HttpServletRequest) request).getMethod().toUpperCase().equals(HttpMethod.OPTIONS)) {
                return true;
            }
        }
        return super.isAccessAllowed(request, response, mappedValue);
    }
}