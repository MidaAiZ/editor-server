package com.mida.chromeext.config;

import com.mida.chromeext.resolver.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class LocaleConfig extends WebMvcConfigurerAdapter {
    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:i18n/messages");
        messageSource.setDefaultEncoding("UTF-8");
        //refresh cache once per hour
        messageSource.setCacheSeconds(3600);
        return messageSource;
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor changeInterceptor = new LocaleChangeInterceptor();
//        changeInterceptor.setParamName("lang");
//        return changeInterceptor;
//    }

    @Bean
    public LocaleResolver localeResolver() {
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver() {
//            @Override
//            public Locale resolveLocale(HttpServletRequest request) {
//                if (!StringUtils.isEmpty(request.getParameter("lang"))) {
//                    String[] split = request.getParameter("lang").split("_");
//                    return new Locale(split[0], split[1]);
//                } else {
//                    return request.getLocale();
//                }
//            }
//        };
//        localeResolver.setDefaultLocale(Locale.ENGLISH);
//        return localeResolver;
        return new MyLocaleResolver();
    }
}