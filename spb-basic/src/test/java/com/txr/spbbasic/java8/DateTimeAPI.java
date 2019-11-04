package com.txr.spbbasic.java8;

import org.junit.Test;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.TemporalAdjusters;
import java.util.Locale;
import java.util.Set;

/**
 * Created by xinrui.tian on 2018/9/26.
 */
public class DateTimeAPI {

    /**TemporalAdjuster（时间调整器）*/

    /*java8之前的时间API存在线程安全问题，示例：DateTimeDemo*/

    /*java8时间API ：LocalDate （日期），  LocalTime （时间），LocalDateTime（日期时间）, Instant（时间戳）...*/

    @Test
    public void createDate () {
        //=======================【创建】===================================
        LocalDate date1 = LocalDate.now();
        LocalDate date2 = LocalDate.of(2018, 10, 1);
        LocalDate date3 = LocalDate.parse("2018-10-01");   //默认格式 yyyy-MM-dd,否则异常

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);

        //=======================【格式】===================================
        //指定字符串格式转化时间
        //yyyyMMdd => yyyy-MM-dd
        LocalDate date4 = LocalDate.parse("20181001", DateTimeFormatter.BASIC_ISO_DATE);
        //yyyy-MM-dd => yyyy-MM-dd
        LocalDate date5 = LocalDate.parse("2018-10-01", DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(date4);
        System.out.println(date5);

        //日期转字符
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss E");
        LocalDateTime localDateTime = LocalDateTime.now();
        String dateTimeStr = localDateTime.format(dtf);
        System.out.println(dateTimeStr);  //2018年09月26日 10:55:16 星期三
        //字符转日期
        LocalDateTime parse = localDateTime.parse(dateTimeStr, dtf);
        System.out.println(parse);  //2018-09-26T10:57

    }

    @Test
    public void testDateApi1() {
        //=======================【时间戳】===================================
        Instant instant = Instant.now();
        System.out.println(instant);
        System.out.println(instant.getNano());
        System.out.println(instant.toEpochMilli());  //1970-01-01T00:00 到现在的毫秒数

        //获取北京时间
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);

        Instant instant1 = Instant.ofEpochSecond(8);  //1970-01-01T00:00:08Z：1970-01-01T00:00 + 8秒
        System.out.println(instant1);
        Instant instant2 = Instant.ofEpochSecond(8, 1000000000);//8秒  +  100万纳秒（1秒）
        System.out.println(instant2);


        //=======================【时区】===================================
        //查看所有时区
        Set<String> zoneIdsSet = ZoneId.getAvailableZoneIds();
        zoneIdsSet.forEach(System.out::println);

        LocalDateTime localDateTime = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));  //亚洲/上海
        System.out.println(localDateTime);

        //时区时间
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("US/Pacific"));  //太平洋时间
        System.out.println(zonedDateTime);
    }

    @Test
    public void testDateApi2() {
        //================【计算差值】=======================================
        //【Duration】
        LocalTime t1 = LocalTime.parse("10:00:00");
        LocalTime t2 = LocalTime.now();

        Duration timeBetween = Duration.between(t1, t2);
        System.out.println("时间差：" + timeBetween.getNano());  // 1000000 纳秒
        // 注意 toMillis 微秒, getSeconds 秒, toMinutes 分, 时， 天， 月，年 获取的都是整数部分

        //【Period】
        LocalDate history = LocalDate.parse("2018-08-25");
        LocalDate now = LocalDate.now();
        Period dateBetween = Period.between(history, now);
        System.out.println("日期差：" + dateBetween.getYears() + "年" + dateBetween.getMonths() + "月" + dateBetween.getDays() + "天");   //1
        //注意getDays  getMonths  getDays 获取的都是整数部分

        //【toEpochDay】
        long abs = Math.abs(history.toEpochDay() - now.toEpochDay());
        System.out.println("天数差共：" + abs + "天");  //两个时间的天数差

        //时间戳
        Instant start = Instant.now();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant end = Instant.now();
        Duration instantBetween = Duration.between(start, end);
        System.out.println("时间戳相差:" + instantBetween.getNano());  // 1000000 纳秒
    }

    @Test
    public void testDateApi3() {
        //=======================【年月日信息】===================================
        LocalDate now = LocalDate.now();
        System.out.println("=========================【天】================================");
        System.out.println("本月第" + now.getDayOfMonth() + "天"); //返回这个月第几天
        System.out.println("本年第" + now.getDayOfYear() + "天");  //返回今年第几天
        System.out.println("本月共" + now.lengthOfMonth() + "天"); //本月一共多少天
        System.out.println("本年共" + now.lengthOfYear() + "天");   //本年一共多少天
        System.out.println("1970 到现在" + now.toEpochDay() + "天"); //返回1970 到现在的数据

        System.out.println("=========================【月】================================");
        Month month = now.getMonth();  //month.length(true);
        System.out.println("月份:" + month);  //枚举
        System.out.println("月份值:" + now.getMonthValue());
        System.out.println(month.getDisplayName(TextStyle.FULL, Locale.getDefault())); //第二个参数为地区
        System.out.println(month.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        System.out.println(month.getDisplayName(TextStyle.NARROW, Locale.getDefault()));
        System.out.println(month.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        System.out.println(month.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        System.out.println(month.getDisplayName(TextStyle.NARROW, Locale.ENGLISH));
        int length = Month.FEBRUARY.length(false); //【参数：是否是闰年】平年二月 28
        System.out.println("指定月份共有" + length + "天");

        System.out.println("=========================【星期】================================");
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        System.out.println(dayOfWeek);  //返回星期几
        System.out.println("第" + dayOfWeek.getValue() + "个星期");
        System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault())); //第二个参数为地区
        System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.getDefault()));
        System.out.println(dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.getDefault()));
        System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.ENGLISH));
        System.out.println(dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.ENGLISH));

        System.out.println("=========================【年】================================");
        System.out.println(now.getYear());
        System.out.println("是否为闰年:" + now.isLeapYear());
        System.out.println("公元纪年: " + now.getEra());  //获得时代。 CE 公元纪年，西方基督降生时刻
    }

    @Test
    public void testDateApi4() {
        //=======================【修改日期】===================================
        LocalDate now = LocalDate.now();
        //修改年月日 withYear  withMonth  withDayOfMonth
        System.out.println(now.withYear(2017));  //修改年
        System.out.println(now.withMonth(8));       //修改月
        System.out.println(now.withDayOfMonth(23));  //修改日

        //plusYears  plusMonths  plusWeeks  plusDays      //添加年、月、日、星期
        //minusYears  minusMonths  minusWeeks  minusDays  //减少年、月、日、星期
        System.out.println(now.plusDays(10));
        System.out.println(now.minusWeeks(1));

        //compareTo  isBefore  isAfter  isEqual
        LocalDate past = LocalDate.parse("2018-09-21");
        System.out.println(past.compareTo(now));  //返回相差值 负值为小于，0等于，正值为大于
        System.out.println(past.isBefore(now)); //true  是否在now之前 不含等于
        System.out.println(past.isAfter(now)); //false  是否在now之后 不含等于
        System.out.println(past.isEqual(now)); //false  是否同一天
    }


    @Test
    public void testDateTimeFormatter () {
        //yyyy-MM-dd hh:mm:ss   12小时
        //yyyy-MM-dd HH:mm:ss   24小时

        LocalDate now = LocalDate.now();
        System.out.println("年表：" + now.getChronology());

        //使用格式化
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  //24小时
        LocalDateTime dateTime1 = LocalDateTime.parse("2018-10-10 16:00:00", formatter1);
        System.out.println(dateTime1);

        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a");  //12小时
        LocalDateTime dateTime2 = LocalDateTime.parse("2018-10-10 04:00:00 下午", formatter2);
        System.out.println(dateTime2);

        LocalDateTime dateTime = LocalDateTime.parse("2018-11-16T16:00:00");
        System.out.println(dateTime);

        //F 不是本月第几个星期几。 准确说是  本月第N周(本月天数/7)的第几天
        String formatString = "【yyyy年MM月dd日 HH:mm:SS】" + "【 a 】" + "【 E 】"
                + "【今天第D天】" + " 【 F 】" + " 【今年第w个星期】" + " 【本月第W个星期】";
        DateTimeFormatter format = DateTimeFormatter.ofPattern(formatString);
        String formatTime = dateTime.format(format);
        System.out.println(formatTime);
    }

    @Test
    public void testLoacalTime() {
        LocalTime now = LocalTime.now();
        System.out.println(now);

        String[] time = now.toString().split(":");

        int a = Integer.valueOf(time[0]);
        System.out.println(a);


        int b = Integer.valueOf(time[1]);
        System.out.println(b);
    }

    // 本星期 和 本月 起止时间
    @Test
    public void testDate() {
        //LocalDate now = LocalDate.now();

        LocalDate now = LocalDate.parse("2019-04-30");
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        LocalDate mon = now.minusDays(dayOfWeek.getValue() - 1);
        System.out.println("星期一日期 ：" + mon);
        LocalDate sun = mon.plusDays(6);
        System.out.println("星期日日期 ：" + sun);


        int dayOfMonth = now.getDayOfMonth();
        LocalDate firstDay = now.minusDays(dayOfMonth - 1 );
        LocalDate lastDay = firstDay.plusDays(now.lengthOfMonth() - 1);
        System.out.println(firstDay  +  ":" + lastDay);
    }


    // 判断是否为改月最后一天
    @Test
    public void testData1() {

        //方式一
        LocalDate now = LocalDate.now();
        LocalDate with = now.with(TemporalAdjusters.lastDayOfMonth());
        //System.out.println(now);
        //System.out.println(with);

        if (now.equals(with)) {
            System.out.println(now + "本月最后一天");
        }


        //方式二
        LocalDate parse = LocalDate.parse("2019-11-30");
        int dayOfMonth = parse.getDayOfMonth();
        int monthLen = parse.lengthOfMonth();
        System.out.println(dayOfMonth);
        System.out.println(monthLen);

        if (monthLen == dayOfMonth) {
            System.out.println(parse + "本月最后一天");
        }


    }
}
