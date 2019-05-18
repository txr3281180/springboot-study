package com.txr.spbbasic.java8;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by xinrui.tian on 2018/9/27.
 */
public class MapAPI {

    /**
         HashMap，LinkedHashMap   允许一个key为null,  允许多个value为null
         Hashtable 			      k-v都不允许为null
         TreeMap                  不允许key为null， 允许 value 为null
     */

    /*
        Java中的Map如果在遍历过程中要删除元素，除非通过迭代器自己的remove()方法，
        否则就会导致抛出ConcurrentModificationException异常。
    */

    @Test
    public void testMap1312(){
        Map<String, String> map = new HashMap<>();
        map.put("aa", "aa");
        map.put("bb", "bb");
        map.put("cc", "cc");
        map.put("dd", "dd");
        map.put("ee", "ee");
        map.put("ff", "ff");

        //正确移除
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            //Map.Entry<String, String> entry = iterator.next();
            //System.out.print(entry.getKey());
            //System.out.println(entry.getValue());
            iterator.remove();
        }

        //报错 java.util.ConcurrentModificationException
          /*  for (String string : map.keySet()) {
                String s = map.get(string);
                System.out.println(s);
                map.remove(string);
            }*/

        //这样也可以，效率没有entry高
        Set<String> set = new HashSet<>(map.size());
        set.addAll(map.keySet());
        for (String string : set) {
            map.remove(string);
        }
    }


    //下面方法, Map四种子类都适用（需要注意有些map不能存放null）
    @Test
    public void testMapDemo(){
        Map<String, String> map = new HashMap<>();
        /*添加*/
        map.put("keyA",  "value_a");
        map.put(null, "value_b");
        map.put("keyB", null);   //添加 k-v
        map.putAll(map);      //添加 map, 如添加的map中存在重复的key，则添加的value覆盖原有value

        /*转换，获取，判断*/
        System.out.println(map.toString());  //toString
        System.out.println(map.hashCode());  //hashCode
        System.out.println(map.equals(map));  //equals
        System.out.println(map.isEmpty());  //isEmpty

        System.out.println("get：" + map.get("keyB"));  //根据Key获取value, key不存在返回null
        System.out.println("getOrDefault：" + map.getOrDefault("keyB", "value_default"));  //根据Key获取value, key不存在返回默认指定值
        System.out.println("size：" + map.size());      //长度
        System.out.println("containsKey：" + map.containsKey("keyA"));  //是否包含某个key
        System.out.println("containsValue：" + map.containsValue("keyB"));  //是否包含某个key

        /*迭代*/
        //map.values
        Collection<String> values = map.values();
        for (String s1 : values) {
            System.out.println(s1);
        }

        //map.keySet()
        Set<String> strings = map.keySet();
        for (String string : strings) {
            System.out.println(string);
        }

        //map.entrySet()
        Set<Map.Entry<String, String>> entries = map.entrySet();
        for (Map.Entry<String, String> entry : entries) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

        //forEach
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });

        /*删除()*/
        String ee = map.remove(null);  //移除key为null的k-v, 返回对应的value
        boolean cc = map.remove("keyB", null);  //移除参数指定的key-value， 返回true表示移除成功
        map.clear();   //清空
        System.out.println(map);

        /*java8 替换*/
        //替换map中存在的Key的value,返回被替换掉value,如果没有这个key返回null
        String aa = map.replace("keyA", "replaceV_A");
        System.out.println("aa " + aa);
        System.out.println("replace1:" + map);
        //当key,与value 都匹配是，替换value值为第三个参数，返回true表示已替换
        boolean replace = map.replace(null, "bb", "cc"); //替换key为null,value为“bb”
        System.out.println("replace2" + replace);
        System.out.println("replace2" + map);

        //循环替换
        map.replaceAll((k, v) -> {
            if ("keyB".equals(k)) {
                v = "bb";
            }
            return v;
        });
        System.out.println("replaceMap: " + map);

        System.out.println("----------------------------------->");

        Map<String, String> map2 = new HashMap<>();
        map2.put("a", "111");
        map2.put("b", "222");
        map2.put("c", "333");

        /*key不存在则添加; key存在，不添加*/
        map2.putIfAbsent("b", "putIfAbsent");
        System.out.println("putIfAbsent:" + map2);

        /*计算添加*/
        //表达式（参数2）计算出结果。 如果key不存在，v==null，计算结果存入; 如果key存在，将结果覆盖原来的value;
        map2.compute("z", (k, v) -> k + "-->" + v);  //z不存在，计算结果为 z-->null
        map2.compute("a", (k, v) -> k + "-->" + v);  //a存在，计算结果为 a-->111
        System.out.println("compute: " + map2); // {a=a-->111, b=222, c=333, z=z-->null}

        /*不存在则计算添加*/
        //表达式（参数2）计算出结果。 如果key不存在，将计算结果存入；如果key存在，则不添加
        map2.computeIfAbsent("b", k -> getB() + k );  // b 存在，值不添加到map2中
        map2.computeIfAbsent("x", k -> getB() + k );  // x 不存在，添加值到map2中
        System.out.println("computeIfAbsent: " + map2);

        /*存在则计算添加*/
        //与computeIfAbsent相反；表达式（参数2）计算出结果。 如果key不存在，不添加；如果key存在，覆盖原有值
        map2.computeIfPresent("c", (k, v) -> getB() + k + v);  // b 存在，值不添加到map2中
        map2.computeIfPresent("y", (k, v) -> getB() + k + v);  // x 不存在，添加值到map2中
        System.out.println("computeIfPresent: " + map2);

         /*合并*/
        //对指定Key(参数1)的value, 与传入的value（参数2） 执行指定操作（表达式）
        // 如果key不存在，直接存入新k-v（表达式不计算）; 如果key存在，将运行表达式，将结果替换。
        map2.merge("key", "newValue", (value, newValue) -> value.concat(newValue));
        System.out.println("merge: " + map2);
    }

    private String getB() {
        System.out.println("B-----");
        return "B";
    }

    @Test
    public void testSort(){
        /* Map 排序实际排的是set */

        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("cc", "--");
        hashMap.put("aa", "--");
        hashMap.put("dd", "--");
        hashMap.put("bb", "--");
        hashMap.put("ff", "--");
        hashMap.put("ee", "--");
        System.out.println("hashMap: " + hashMap);

        Map<String, String> treeMap = new TreeMap<>((x, y) -> y.compareTo(x));
        treeMap.putAll(hashMap);
        System.out.println("treeMap: " + treeMap);
    }
}
