package com.txr.spbbasic.demo.annotation.indexAnno;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinrui.tian on 2018/12/7
 */
public class MainClass {

    public static void main(String[] args) {

        String str = "F0001332018NCD037,111897682,20181206,CIB,0.4600,98.3820,2.5211,95.8608,3.5732,0.4528,0.4101,0.0045,,,,,,,,,,,推荐,,,98.3948,2.5339,95.3300,";

        CdhCdcValuation mapping = FieldMappingTools.columnMapping(new CdhCdcValuation(), str);
        System.out.println(mapping);

        CdhCdcValuation cdhCdcValuation = FieldMappingTools.classMapping(CdhCdcValuation.class, str);
        System.out.println(cdhCdcValuation);

        System.out.println("-----------------------------------------");
        String str1 = "F0001332018NCD031,111897682,20181206,CIB,0.4600,98.3820,2.5211,95.8608,3.5732,0.4528,0.4101,0.0045,,,,,,,,,,,推荐,,,98.3948,2.5339,95.3300,";
        String str2 = "F0001332018NCD032,111897682,20181206,CIB,0.4600,98.3820,2.5211,95.8608,3.5732,0.4528,0.4101,0.0045,,,,,,,,,,,推荐,,,98.3948,2.5339,95.3300,";
        String str3 = "F0001332018NCD033,111897682,20181206,CIB,0.4600,98.3820,2.5211,95.8608,3.5732,0.4528,0.4101,0.0045,,,,,,,,,,,推荐,,,98.3948,2.5339,95.3300,";
        String str4 = "F0001332018NCD034,111897682,20181206,CIB,0.4600,98.3820,2.5211,95.8608,3.5732,0.4528,0.4101,0.0045,,,,,,,,,,,推荐,,,98.3948,2.5339,95.3300,";
        String str5 = "F0001332018NCD035,111897682,20181206,CIB,0.4600,98.3820,2.5211,95.8608,3.5732,0.4528,0.4101,0.0045,,,,,,,,,,,推荐,,,98.3948,2.5339,95.3300,";
        String str6 = "F0001332018NCD036,111897682,20181206,CIB,0.4600,98.3820,2.5211,95.8608,3.5732,0.4528,0.4101,0.0045,,,,,,,,,,,推荐,,,98.3948,2.5339,95.3300,";
        String str7 = "F0001332018NCD037,111897682,20181206,CIB,0.4600,98.3820,2.5211,95.8608,3.5732,0.4528,0.4101,0.0045,,,,,,,,,,,推荐,,,98.3948,2.5339,95.3300,";
        List<String> strList = new ArrayList<>();
        strList.add(str1);
        strList.add(str2);
        strList.add(str3);
        strList.add(str4);
        strList.add(str5);
        strList.add(str6);
        strList.add(str7);


        List<CdhCdcValuation> cdhCdcValuations = FieldMappingTools.classListMapping(CdhCdcValuation.class, strList);
        System.out.println(cdhCdcValuations);

    }

    /** 直接赋值更快*/
    @Test
    public void testMapping (){

        List<String> list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add("F" + i + ",111897682,20181206,CIB,0.4600,98.3820,2.5211,95.8608,3.5732,0.4528,0.4101,0.0045,,,,,,,,,,,推荐,,,98.3948,2.5339,95.3300,");
        }

        long start2 = System.currentTimeMillis();
        List<CdhCdcValuation> result = new ArrayList<>();
        for (String s : list) {
            result.add(new CdhCdcValuation(s));
        }
        long end2 = System.currentTimeMillis();
        System.out.println(end2-start2);

        System.out.println(result);


        long start = System.currentTimeMillis();
        List<CdhCdcValuation> cdhCdcValuations = FieldMappingTools.classListMapping(CdhCdcValuation.class, list);
        long end = System.currentTimeMillis();
        System.out.println(end-start);
        System.out.println(cdhCdcValuations);

    }

}
