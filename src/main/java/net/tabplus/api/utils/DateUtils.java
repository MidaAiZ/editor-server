package net.tabplus.api.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String formatDate(Date date) {
        if (date == null) {
            return null;
        }
        //指定格式化的模板
        String model = "yyyy-MM-dd";
        //默认的格式
        SimpleDateFormat dateFormat = new SimpleDateFormat(model);
        //格式化当前的日期
        return dateFormat.format(date);
    }
}
