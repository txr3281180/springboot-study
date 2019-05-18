package com.txr.spbbasic.global.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinYinUtils {

     /**
         <dependency>
             <groupId>com.belerweb</groupId>
             <artifactId>pinyin4j</artifactId>
             <version>2.5.1</version>
         </dependency>
     */

    // 将汉字转换为全拼
    public static String getPingYin(String textStr) {
        String result = "";
        if (textStr == null) {
            return null;
        }
        char[] textChar = textStr.toCharArray();
        HanyuPinyinOutputFormat pyOutPutFormat = new HanyuPinyinOutputFormat();
        //设置转成大写还是小写
        pyOutPutFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        //拼音的声调格式 （WITH_TONE_NUMBER【数字表示声调】, WITHOUT_TONE【无声调】, WITH_TONE_MARK【声调符号表示】）
        pyOutPutFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        //特殊拼音ü的显示格式（WITH_U_AND_COLON【以U和一个冒号表示U:】， WITH_V【用v表示】, WITH_U_UNICODE【用ü表示】）
        pyOutPutFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

        int len = textChar.length;
        try {
            for (int i = 0; i < len; i++) {
                // ( [\u4e00-\u9fa5]+ )   至少匹配一个汉字
                // 两个unicode值正好是Unicode表中的汉字的头和尾,"[]"代表里边的值出现一个就可以，后边的“+”代表至少出现1次
                if (Character.toString(textChar[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] t2 = PinyinHelper.toHanyuPinyinStringArray(textChar[i], pyOutPutFormat);
                    result += t2[0];
                } else
                    result += Character.toString(textChar[i]);
            }
            return result;
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return result;
    }

    // 返回中文的首字母
    public static String getPinYinHeadChar(String content) {
        String result = "";
        if (content == null) {
            return result;
        }
        int len = content.length();

        for (int j = 0; j < len; j++) {
            char word = content.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                result += pinyinArray[0].charAt(0);
            } else {
                result += word;
            }
        }
        return result;
    }

    // 将字符串转移为ASCII码
    public static String getCnASCII(String content) {
        if(content == null) {
            return "";
        }
        StringBuffer strBuf = new StringBuffer();
        byte[] bGBK = content.getBytes();
        int len = bGBK.length;
        for (int i = 0; i < len; i++) {
            strBuf.append(Integer.toHexString(bGBK[i] & 0xff));
        }
        return strBuf.toString();
    }

}
