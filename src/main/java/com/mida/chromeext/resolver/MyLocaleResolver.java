package com.mida.chromeext.resolver;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * @Description 自定义国际化处理器LocaleResolver
 * SpringBoot为我们自动配置的LocalResolver，是根据请求的请求头中的"Accept-Language"获取判断的
 * 现在改写成先从url路径上寻找是否有国际化语言变量，如果没有再从请求头的"Accept-Language"中获取
 */
public class MyLocaleResolver implements LocaleResolver {
    /**
     * 处理逻辑
     * 接口核心方法，获取Locale，并返回
     *
     * @param request 请求
     * @return Locale区域信息
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String l = request.getParameter("lang");
        if (StringUtils.isEmpty(l)) {
            //路径上没有国际化语言参数，采用默认的（从请求头中获取）
            return request.getLocale();
        } else {
            String[] split = l.split("_");
            //语言、国家构造器
            return split.length == 2 ? new Locale(split[0], split[1]) : new Locale(split[0], "");
        }
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}