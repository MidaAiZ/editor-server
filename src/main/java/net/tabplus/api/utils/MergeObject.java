package net.tabplus.api.utils;

import java.lang.reflect.Field;

public class MergeObject {
    public static <T> T merge(T first, T second) throws IllegalAccessException, InstantiationException {
        Class<?> clazz = first.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Object returnValue = clazz.newInstance();
        for (Field field : fields) {
            field.setAccessible(true);
            Object value1 = field.get(first);
            Object value2 = field.get(second);
            Object value = (value2 == null) ? value1 : value2;
            field.set(returnValue, value);
        }
        return (T) returnValue;
    }
}
