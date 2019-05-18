package com.txr.spbbasic.demo;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;

/**
 * Created by xinrui.tian on 2018/12/19
 */
public class NumberDemo {

    /** Integer 空指针 */
    @Test
    public void testInteger () {
        Integer a = null;
        //    boolean b = a > 1;  //空指针
        //    System.out.println(b);

        //------转化String-----------
        //     System.out.println(a.toString());  //空指针
        System.out.println(String.valueOf(a));
    }

    /** 随机数 */
    @Test
    public void testRandom() {
        Random random = new Random();
        System.out.println(random.nextInt(10));  //random.nextInt()
        System.out.println(random.nextDouble());
        System.out.println(random.nextBoolean());
    }

    /** 保留有效数字 与 保留小数位数 */
    @Test
    public void testBigDecimal() {
        BigDecimal a = BigDecimal.valueOf(20.4049); //10以上的
        BigDecimal b = BigDecimal.valueOf(2.4049);  //1-10之间的
        BigDecimal c = BigDecimal.valueOf(0.4049);  //1以下的

        System.out.println("==========保留三位小数=============");
        System.out.println("==========DecimalFormat");
        DecimalFormat df = new DecimalFormat("#.000");  //[注意 #.000  #0.00]
        System.out.println(df.format(a)); //20.405
        System.out.println(df.format(b)); //2.405
        System.out.println(df.format(c)); //.405

        System.out.println(Double.valueOf(df.format(c))); //.405 转数字后 0.405

        BigDecimal big = BigDecimal.valueOf(10.1234);  //DecimalFormat.format 参数Object
        System.out.println(df.format(big)); //10.123

        // System.out.println("DecimalFormat format null ：" + df.format(null)); //空指针

        System.out.println("=========RoundingMode");
        System.out.println(a.setScale(3, RoundingMode.HALF_UP));  //20.405
        System.out.println(b.setScale(3, RoundingMode.HALF_UP));  //2.405
        System.out.println(c.setScale(3, RoundingMode.HALF_UP));  //0.405

        System.out.println("=========BigDecimal");
        System.out.println(a.setScale(3, BigDecimal.ROUND_HALF_UP));
        System.out.println(b.setScale(3, BigDecimal.ROUND_HALF_UP));
        System.out.println(c.setScale(3, BigDecimal.ROUND_HALF_UP));

        System.out.println("=========保留三位有效数字=============");
        System.out.println("=========MathContext");
        MathContext HALF_UP_2ND = new MathContext(3, RoundingMode.HALF_UP);
        System.out.println(a.round(HALF_UP_2ND));
        System.out.println(b.round(HALF_UP_2ND));
        System.out.println(c.round(HALF_UP_2ND));
    }

    /**判断 是否位数字 org.apache.commons.lang3.math.NumberUtils */
    @Test
    public void testNumber() {

        //http://blog.csdn.net/facekbook/article/details/77338293
        /**
         * 校验String是否是一个有效的Java number
         * 校验number包含hexadecimal 的标记 0x or 0X、八进制数字、科学计数法和其他的数字标记类型，比如1234L
         * 以0开头的非十六进制的数统一作为八进制的数处理。所以String为09将返回false，因为9不是一个有效的八进制。但是是以0.开头的数字，都作为十进制处理
         * null或empty或blank字符串返回false
         */

        /**
         * 校验提供的字符串是否可以解析为number
         * 可解析的number包括下面方法可以执行字符串 Integer.parseInt(String), Long.parseLong(String), Float.parseFloat(String) or Double.parseDouble(String).
         * 这个方法可以替代java.text.ParseException异常
         * 十六进制和科学计数符号认为是不可解析的
         */

        /*commons-lang3-3.5.jar    org.apache.commons.lang3.math.NumberUtils*/
        //     System.out.println(NumberUtils.isCreatable("12312")); //true
        //      System.out.println(NumberUtils.isCreatable("123121231."));//true
        //      System.out.println(NumberUtils.isParsable("123121231.")); //false
        //       System.out.println(NumberUtils.isParsable("123121231")); //true
        //       System.out.println(NumberUtils.createBigDecimal("123121231.")); //123121231
    }
}
