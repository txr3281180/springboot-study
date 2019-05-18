package com.txr.spbbasic;

import com.txr.spbbasic.global.utils.Json.JsonUtils;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class BaseDemo {

    /* System.arraycopy(array1, EleIndex, array2, EleIndex, length) */
    @Test
    public void test1() {
        String[] array1 = new String[4];
        array1[0] = "aa";
        array1[1] = "bb";
        array1[2] = "cc";
        array1[3] = "dd";

        String[] array2 = new String[array1.length];
        System.arraycopy(array1, 0, array2, 0, array1.length); //要比通过循环来复制数组快的多
        //System.arraycopy(array1, 1, array2, 1, 2);
        for (String s : array2) {
            System.out.println(s);
        }

        String[] clone = array1.clone();
        System.out.println(clone);
    }

    @Test
    public void test2() {
        //判断偶数
        System.out.println(101 % 2 == 0);  //取模运算
        System.out.println((101 & 1) == 0); //位移运算

        //获取本地IP
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            System.out.println(localHost);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        //&& 比 || 优先
        String string = "AY";
        //"AB" 或者 "DY"
        if (string.contains("A") && string.contains("B") || string.contains("D") && string.contains("Y")) {
            System.out.println("==>" + string);
        }
    }

    /**
     * switch null 空指针
     */
    @Test
    public void switchTest() {
        //  String a = "A";
        String a = null;

        switch (a) {    //switch null 空指针
            case "A":
                System.out.println("A");
                break;
            case "B":
                System.out.println("B");
                break;
        }
    }

    @Test
    public void testNull() {

        /**相除遇到无限循环小数*/
        double a = 46379.0;
        //无限循环小数报错
        //BigDecimal.valueOf(a).divide(BigDecimal.valueOf(30)).setScale(2, RoundingMode.HALF_UP).doubleValue();
        BigDecimal.valueOf(a).divide(BigDecimal.valueOf(30), 2, RoundingMode.HALF_UP).doubleValue();

        //
        Object o = null;
        String s = (String) o;
        System.out.println("s ==>" + s);

        /** 封装的基本数据类型使用三元运算符可能报空指针 */
        Double val = null;
        //空指针
        Double aDouble = val == null ? null : BigDecimal.valueOf(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
        //需要分开写
        //if (val == null) { return; }
        //BigDecimal.valueOf(val).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

     /*排序工具 ComparatorChain  BeanComparator   BeanUtils*/


     /**  Calendar 日历 */
    @Test
    public void testDate() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.set(Calendar.MONTH, 0);  //月份从0开始
        c.set(Calendar.DAY_OF_MONTH, 1); //设置为该月第一天

        Date firstDayOfThisYear = c.getTime();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println(sd.format(new Date()));
        System.out.println(sd.format(firstDayOfThisYear));
    }


    /** 测试线程锁：释放锁之后，其它线程是否继续执行 */
    @Test
    public void testSyn() {
        //todo
    }

    @Test
    public void testSystem () {
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }


    @Test
    public void testJson() {
        Map<Object, Object> map = new HashMap<>();
        map.put("Type", "55617");
        map.put("Subject", "172.16.73.144:50110_7488");
        map.put("MessageID", "172.16.73.144:50110_7488");
        map.put("ReplyTo", "QB.GATEWAY.AckQueue.dfzqr.18888_1");

        Map<Object, Object> meg = new HashMap<>();
        meg.put("UnderwriterID", "402880f034219aed0134219e1f500795");
        map.put("xUPPCnewBondMarginGuideInfoReq", meg);


        String s = JsonUtils.toJson(map);
        System.out.println(s);
    }

    @Test
    public void testHost() throws UnknownHostException {
        InetAddress localHost = InetAddress.getLocalHost();

        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getCanonicalHostName());
        System.out.println(localHost.getHostName());
    }

    @Test
    public void testAtomicInteger(){
        AtomicInteger ai = new AtomicInteger(1);
        System.out.println(ai);
        System.out.println(ai.intValue());


        int i = ai.incrementAndGet();   //先自增在获取
        System.out.println(i);
        System.out.println(ai);

        System.out.println("===================");
        int andIncrement = ai.getAndIncrement();  //先获取在自增
        System.out.println(andIncrement);
        System.out.println(ai);


        ai.set(0);

        System.out.println(ai);
    }

    @Test
    public void testResutn1(){
        System.out.println(testReturn());
    }

    public int testReturn(){

        int i = 1;
        try {
            System.out.println("try");
            return i++;
        } finally {
            System.out.println("finally");
            return i;
        }
    }
}
