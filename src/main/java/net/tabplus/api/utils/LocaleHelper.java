package net.tabplus.api.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class LocaleHelper {
    public static Locale getContextLocale(HttpServletRequest request) {
        return RequestContextUtils.getLocaleResolver(request).resolveLocale(request);
    }

    public static String getContextCountryCode(HttpServletRequest request) {
        String countryCode = RequestContextUtils.getLocaleResolver(request).resolveLocale(request).getCountry();
        if (StringUtils.isEmpty(countryCode)) {
            countryCode = Constant.THE_WORLD;
        }
        return countryCode;
    }
}
