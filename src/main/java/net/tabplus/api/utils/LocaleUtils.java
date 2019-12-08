package net.tabplus.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class LocaleUtils {
    private static MessageSource messageSource;

    public static String getMsg(String key) {
        Locale locale = LocaleContextHolder.getLocale();
        try {
            return messageSource.getMessage(key, null, locale);
        } catch (Exception e) {
            String[] keys = key.split("\\.");
            return keys[keys.length - 1];
        }
    }

    public static String getMsg(String key, String... arg) {
        Locale locale = LocaleContextHolder.getLocale();
        Object[] args = new Object[arg.length];
        for (int i = 0; i < arg.length; i++) {
            args[i] = arg[i];
        }
        return messageSource.getMessage(key, args, locale);
    }

    @Autowired(required = true)
    public void setMessageSource(MessageSource messageSource) {
        LocaleUtils.messageSource = messageSource;
    }
}
