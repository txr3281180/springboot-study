package txr.common.date;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 计算两个日期之间的的日期
     */
    public static List<LocalDate> getBetweenDate(String beforDate, String afterDate) {
        LocalDate beforLocalDate = null;
        LocalDate afterLocalDate = null;
        try {
            beforLocalDate = LocalDate.parse(beforDate);
            afterLocalDate = LocalDate.parse(afterDate);
        } catch (DateTimeException de) {
            //log.warn(String.format("Time conversion error:[beforDate: %s, afterDate: %s]", beforDate, afterDate));
        }
        List<LocalDate> localDates = new ArrayList<>();
        if (beforLocalDate == null || afterLocalDate == null){
            return localDates;
        }
        long day = afterLocalDate.toEpochDay() - beforLocalDate.toEpochDay();
        //包含beforDate，不包含afterDate
        for (long i = 0; i <= day; i++) {
            localDates.add(beforLocalDate.plusDays(i));
        }
        return localDates;
    }
}
