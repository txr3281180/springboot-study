package com.txr.spbbasic;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.expression.MapAccessor;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName TestApp
 * @Author szm
 * @Date 2020/6/22 11:20
 **/
public class TestApp {

    public static void main(String[] args) {
        String Path = "d:\\json.txt";
        BufferedReader reader = null;
        try {
            List result = new ArrayList();
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                System.out.println(tempString);
                JSONObject jbo = JSONObject.parseObject(tempString);
                String data = jbo.getString("resultTable");
                JSONArray jbo1 = JSONArray.parseArray(data);
                List a = JSONObject.parseArray(jbo1.toJSONString());
                result.addAll(a);
            }

            HashMap map = new HashMap();
            System.out.println("文本条数:" + result.size());
            reader.close();

            result.stream().forEach(e -> check((JSONObject) e, map));

            System.out.println("去重后条数" + map.size());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void check(JSONObject e, HashMap map) {
        String p = e.getString("GJK_Code") + e.getString("Index_Date");
        map.put(p, e);
    }
}
