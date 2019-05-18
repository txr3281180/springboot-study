package com.txr.spbbasic.demo;

import org.junit.Test;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class StringDemo {


    //设置输出字码。 resp.setContentType("text/html; charset=UTF-8“)；
    //设置获取value值的字码	new String(name.getBytes("iso-8859-1"), "utf-8");

    @Test
    public void testString() {
        /*==========正则匹配*/
        String num = "1";
        //Pattern. compile("<").matcher("st<r").replaceAll("&lt;")
        boolean matches = Pattern.compile("^[0-9]*$").matcher(num).matches();
        System.out.println(matches);

        /*===============字符串与数字转换*/
        int i = 0;
        System.out.println(String.valueOf(i));
        System.out.println(i + "");

        String y = "0";
        System.out.println(Integer.parseInt(y));
        //Integer.valueOf(int); 自动转换

        /*===========String.compareTo 与 Long.compareTo 的返回值*/
        String a = "1";
        String b = "4";
        System.out.print(a.compareTo(b) + " ");
        System.out.print(b.compareTo(b) + " ");
        System.out.println(b.compareTo(a));
        //-3  0  3

        Long c = 1L;
        Long d = 4L;
        System.out.print(c.compareTo(d) + " ");
        System.out.print(c.compareTo(c) + " ");
        System.out.println(d.compareTo(c));
        //-1  0  1
    }

    /** StringBuffer 和 StringBuilder 的坑*/
    @Test
    public void testStringBuffer () {

        //带参构造器注意使用点
        //new StringBuilder(null);   //空指针
        System.out.println(new StringBuilder(1).append("-").append(2).toString());  // -2  坑
        System.out.println(new StringBuilder(1).append("-").append("2").toString());  // -2  坑
        System.out.println(new StringBuilder("1").append("-").append("2").toString());  // 1-2
        System.out.println(new StringBuilder().append(1).append("-").append("2").toString());  //1-2

        //区分 map get的类型
        Map<String, String> map = new HashMap<>();
        map.put("AA", "aa");
        StringBuilder builderStr = new StringBuilder("A").append("A");
        String s1 = map.get(builderStr);
        String s2 = map.get(builderStr.toString());  //必须转为String, 因为泛型定义为String
        System.out.println(s1);  // null
        System.out.println(s2);     // aa

        //参数为null的情况
        StringBuilder str = new StringBuilder("aa");
        String a = null;
        str.append(a);
        System.out.println(str);   //aanull
        //str.append(null);  //直接传null, 编译不通过
    }

    /** String 填充占位符 */
    @Test
    public void testMessageFormat () {
        String str = MessageFormat.format("你好{0}, 你{1}!", "张三", "吃饭了吗?");
        System.out.println(str);

        String condition = "Date > = '%s' and Date < '%s' and id = %d";
        String format = String.format(condition, "2017", "2018", 12);
        System.out.println(format);
    }

    /** split的第二个参数*/
    @Test
    public void testSplit () {
        //split 第二个参数
        String a = "a,b,c,d,,,e,,";  //split 后面的为空的数据将被丢弃
        Arrays.stream(a.split(",")).forEach(s ->  System.out.print((s.isEmpty() || s == null?"-":s) + "|"));  //abcd##e
        System.out.println();
        Arrays.stream(a.split(",", 0)).forEach(s ->  System.out.print((s.isEmpty() || s == null?"-":s) + "|"));
        //上面两个效果一样
        System.out.println();
        Arrays.stream(a.split(",", 2)).forEach(s ->  System.out.print((s.isEmpty() || s == null?"-":s) + "|"));
        System.out.println();
        Arrays.stream(a.split(",", 5)).forEach(s ->  System.out.print((s.isEmpty() || s == null?"-":s) + "|"));
        System.out.println();
        Arrays.stream(a.split(",", -1)).forEach(s ->  System.out.print((s.isEmpty() || s == null?"-":s) + "|"));
        System.out.println();
        /*
            a|b|c|d|-|-|e|
            a|b|c|d|-|-|e|
            a|b,c,d,,,e,,|
            a|b|c|d|,,e,,|
            a|b|c|d|-|-|e|-|-|
        */
        System.out.println("--------------------------------------------------------------------");

        String str = "";
        System.out.println(str.length());  //0

        //test split

        String string = "";
        String[] split = string.split("/"); //{""}
        System.out.println(split.length);
        System.out.println(split[0] == null);

        string = "A/A";
        String[] split1 = string.split("/"); //{"A", "A"}
        System.out.println(split1.length);

        string = "A/";
        String[] split2 = string.split("/"); // {"A"}
        System.out.println(split2.length);

        string = "/A";
        String[] split3 = string.split("/"); //{"", "A"}
        System.out.println(split3.length);
        System.out.println(split3[0] == null);

        string = "/";
        String[] split4 = string.split("/"); //{}
        System.out.println(split4.length); // 0
        //System.out.println(split4[0] == null);
    }


    /**
     * 判断一个字符串是不是纯数字
     */
    public boolean isNumericString(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
