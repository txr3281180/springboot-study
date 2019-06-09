package com.txr.spbbasic.json.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xinrui.tian on 2019/5/19.
 */
public class FastJsonTest {

    @Test
    public void test1() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("ABC", "abc");


        Map<String, JSONObject> map = new HashMap<>();
        map.put("a", jsonObject);

        Map<String ,Object> result = new HashMap<>();
        result.put("entity", map);
        Map<String, String> map1 = (Map<String, String>)result.get("entity");
        System.out.println(map1);

        map1.put("b", "B");

        System.out.println(map);

        //String a = map1.get("a"); //不能转换 String
        String a = JSON.toJSONString(map1.get("a"));
        System.out.println(a);

        String s = JSON.toJSONString(a);

        JSONObject jsonObject1 = JSON.parseObject(s);
        System.out.println(jsonObject1);


        Map<String, JSONObject> map2 = (Map<String, JSONObject>)result.get("entity");
        System.out.println(map2);

    }

}
