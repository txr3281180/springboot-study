package com.txr.spbbasic.global.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by xinrui.tian on 2018/11/9.
 */
public class DateTimeUtils {

    /** 判断字符串是否为合法时间  yyyy-MM-dd */
    public static boolean isDate(String str) {
        if (str != null && !str.trim().isEmpty()) {
            try {
                LocalDate.parse(str, DateTimeFormatter.ISO_LOCAL_DATE);
                return true;
            }catch (Exception e){
                return false;
            }
        }
        return false;
    }

    /**格式化日期时间, 返回yyyy-MM-dd HH:mm:ss */
    public LocalDateTime formatterDateTime (String dateTime) {
        LocalDateTime localDateTime;
        try {
            dateTime = dateTime.replace("T", " ");
            DateTimeFormatter formatter;
            switch (dateTime.length()){
                case 13:
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
                    break;
                case 16:
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                    break;
                case 21:
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
                    break;
                case 22:
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SS");
                    break;
                case 23:
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
                    break;
                default:
                    formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                    break;
            }
            localDateTime = LocalDateTime.parse(dateTime, formatter);
        } catch (Exception e) {
            return null;
        }
        return localDateTime;
    }
}
