package com.baidu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    /**
     * 日期转换字符串
     * @param date
     * @param part
     * @return
     */
    public static String date2String(Date date,String part){
        SimpleDateFormat sdf = new SimpleDateFormat(part);
        String dateStr = sdf.format(date);
        return dateStr;
    }

    /**
     * 字符串转换日期
     * @param date
     * @param part
     * @return
     * @throws ParseException
     */
    public static Date string2Date(String date,String part) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(part);
        Date dateStr = sdf.parse(date);
        return dateStr;
    }
}
