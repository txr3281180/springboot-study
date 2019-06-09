package com.txr.spbbasic.json.fastjson;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.txr.spbbasic.controller.model.Bond;
import com.txr.spbbasic.repository.BondAccess;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
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

    /*
     //https://www.runoob.com/w3cnote/fastjson-intro.html


    SerializerFeature
        QuoteFieldNames———-输出key时是否使用双引号,默认为true
        WriteMapNullValue——–是否输出值为null的字段,默认为false
        WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
        WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
        WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
        WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
    */


    /*

    //

    SerializerFeature
        PrettyFormat:格式化输出
        WriteMapNullValue:是否输出值为null的字段,默认为false
        DisableCircularReferenceDetect:消除循环引用
        WriteNullStringAsEmpty:将为null的字段值显示为""
        WriteNullListAsEmpty：List字段如果为null,输出为[],而非null
        WriteNullNumberAsZero：数值字段如果为null,输出为0,而非null
        WriteNullBooleanAsFalse：Boolean字段如果为null,输出为false,而非null
        SkipTransientField：如果是true，类中的Get方法对应的Field是transient，序列化时将会被忽略。默认为true
        SortField：按字段名称排序后输出。默认为false
        WriteDateUseDateFormat：全局修改日期格式,默认为false。JSON.DEFFAULT_DATE_FORMAT = “yyyy-MM-dd”;JSON.toJSONString(obj, SerializerFeature.WriteDateUseDateFormat);
        BeanToArray：将对象转为array输出
        QuoteFieldNames：输出key时是否使用双引号,默认为true
        UseSingleQuotes：输出key时使用单引号而不是双引号,默认为false（经测试，这里的key是指所有的输出结果，而非key/value的key,而是key,和value都使用单引号或双引号输出）
    * */

    @Test
    public void test2() {

        /*全局配置*/
        //是否输出值为null的字段,默认为false
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteMapNullValue.getMask();
        //数值字段如果为null,输出为0,而非null
        //JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullNumberAsZero.getMask();
        //List字段如果为null,输出为[],而非null
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullListAsEmpty.getMask();
        //字符类型字段如果为null,输出为 "",而非null
        JSON.DEFAULT_GENERATE_FEATURE |= SerializerFeature.WriteNullStringAsEmpty.getMask();

        List<Bond> allBond = BondAccess.getAllBond();
        Bond bond = allBond.get(0);

        //对象转json 字符
        String string = JSON.toJSONString(bond);
        System.out.println(string);

        //json字符转JSONObject
        JSONObject jsonObject = JSON.parseObject(string);
        System.out.println(jsonObject);


        Bond bond2 = new Bond();
        bond2.setBondKey("ABC");

        //不序列化null
        String string2 = JSON.toJSONString(bond2);
        JSONObject jsonObject2 = JSON.parseObject(string2);
        System.out.println(jsonObject2);

        String s = JSON.toJSONString(bond2, SerializerFeature.WriteMapNullValue); //临时配置
        System.out.println(s);
        JSONObject jsonObject3 = JSON.parseObject(s);
        System.out.println(jsonObject3);
    }


    @Test
    public void test3() {
        List<Bond> allBond = BondAccess.getAllBond();
        Bond bond = allBond.get(0);
        String string = JSON.toJSONString(bond);
        JSONObject jsonObject = JSON.parseObject(string);


        System.out.println(jsonObject);

        Entity entity = jsonObject.toJavaObject(Entity.class);
        System.out.println(entity);


        Entity entity1 = JSONObject.toJavaObject(jsonObject, Entity.class);
        System.out.println(entity1);
    }

}
