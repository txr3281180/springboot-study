package com.txr.spbbasic.demo.collection;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by xinrui.tian on 2018/12/30.
 */
public class CollectionDemo {


    /** 原生文件属性获取 */
    @Test
    public void testProperty() {
        Properties properties = new Properties();
        InputStream is = CollectionDemo.class.getClassLoader().getResourceAsStream("application.properties");
        try {
            properties.load(is);

            String property = properties.getProperty("server.port");
            System.out.println(property);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Map<Object, Object> 可以直接用 String 类型获取*/
    @Test
    public void testMapGet () {
        Map<Object, Object> map = new HashMap<>();
        map.put("AA", "aa");

        String aa = "AA";
        System.out.println(map.get(aa)); //Object 可以用 String直接取值

        Map<String, List<String>> map2 = new HashMap<>();
        Map<String, String> bb = map2.getOrDefault("bb", new ArrayList<>()).stream().collect(Collectors.toMap(x -> x, Function.identity(), (k1, k2) -> k1));
        System.out.println("bb" + bb);
    }


    /**集合排序*/
    @Test
    public void testCollection() {
        //有序、无序及排序性质的差别
        //集合的有序、无序是指插入元素时，保持插入的顺序性，也就是先插入的元素优先放入集合的前面部分

        //有序
        List<String> arrayList = new ArrayList<>();
        arrayList.add("d");
        arrayList.add("c");
        arrayList.add("b");
        arrayList.add("2");
        arrayList.add("a");
        arrayList.add("1");
        System.out.println(arrayList);  //[d, c, b, 2, a, 1]
        arrayList = arrayList.stream().sorted().collect(Collectors.toList());
        System.out.println(arrayList);  //[1, 2, a, b, c, d]

        //无序
        Set<String> hashSet = new HashSet<>();
        hashSet.add("d");
        hashSet.add("c");
        hashSet.add("b");
        hashSet.add("2");
        hashSet.add("a");
        hashSet.add("1");
        System.out.println(hashSet);    //[a, 1, b, 2, c, d]
        hashSet = hashSet.stream().sorted().collect(Collectors.toSet());
        System.out.println(hashSet);    //[1, a, 2, b, c, d]

        //无序
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("2", "2");
        hashMap.put("c", "C");
        hashMap.put("d", "D");
        hashMap.put("b", "B");
        hashMap.put("a", "A");
        hashMap.put("1", "1");
        System.out.println(hashMap);  //{a=A, 1=1, 2=2, b=B, c=C, e=E}

        //有序
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("2", "2");
        treeMap.put("c", "C");
        treeMap.put("d", "D");
        treeMap.put("b", "B");
        treeMap.put("a", "A");
        treeMap.put("1", "1");
        System.out.println(treeMap);  //{1=1, 2=2, a=A, b=B, c=C, d=D}

    }

    /** 测试下标 */
    @Test
    public void testListIndex() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("cc");
        System.out.println(list.indexOf("bb"));  //-1
        System.out.println(list.indexOf("aa"));  //0
        System.out.println(list.indexOf("cc"));  //1

        //下标越界
       /* List<String> list = new ArrayList<>();
        String s = list.get(0);
        System.out.println(s);*/

        System.out.println("2018-10-11".compareTo("2018-10-16"));

    }


    /** 引用赋值 与 值赋值 */
    @Test
    public void testVal () {

        List<String> temp = new ArrayList<>();
        Map<String, List<String>> collect = temp.stream().collect(Collectors.groupingBy(x -> x));
        System.out.println("Map:" + collect);  //Map:{}

        Set<String> set = new HashSet<>();
        set.add("AAA");
        set.add("BBB");
        set.add("CCC");
        System.out.println(set);  //[AAA, CCC, BBB]

        //============list 引用赋值
        List<String> strings = new ArrayList<>();
        strings.add("AA");
        strings.add("BB");
        strings.add("CC");
        strings.add("DD");
        List<String> strings1 = strings.subList(1, 3);  //引用赋值
        System.out.println(strings1);

        //List<String> strings1 = new ArrayList<>();    //值赋值
        //strings.subList(1, 3).forEach(x -> strings1.add(x));

        strings1.remove("BB");
        System.out.println(strings1);
        System.out.println(strings);
        strings.clear();
        System.out.println(strings1);  //java.util.ConcurrentModificationException


        //=======map.get()  mep.getOrDefault()==引用问题
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("aa");
        map.put("A", list);

        //String key = "A";
        String key = "B";
        List<String> orDefault = map.getOrDefault(key, new ArrayList<>());
        if (orDefault.size() == 0) {
            map.put(key, orDefault);  //list为引用
        }
        orDefault.add("bb");
        System.out.println(map);


        List<String> strings2 = map.get("A");  //list为引用
        strings2.add("cc");
        System.out.println(map);
    }

    @Test
    public void testMapGetNull() {
        Map<String, String> map = new HashMap<>();

        String s = map.get(null);
        System.out.println(s); // null

        String aa = map.getOrDefault(null, "AA");
        System.out.println(aa);//AA

        map.put(null, "BB");
        System.out.println(map.get(null));  //BB
        System.out.println(map.getOrDefault(null, "CC")); //BB


        System.out.println("==============");

        Map<String, List<String>> testMap = new HashMap<>();


        List<String> list = new ArrayList<>();
        list.add("AA");
        testMap.put("a", list);

        testMap.put("b", new ArrayList<>());
        System.out.println(testMap);

        List<String> dict = new ArrayList<>();
        dict.add("a");
        dict.add("b");
        dict.add("c");

        for (String s1 : dict) {
            /*List<String> listTemp = testMap.getOrDefault(s1, new ArrayList<>());
            if (listTemp.size() == 0){
                testMap.put(s1, listTemp);
            }
            listTemp.add(s1);*/


            List<String> listTemp = testMap.get(s1);
            if (listTemp == null){
                listTemp = new ArrayList<>();
                testMap.put(s1, listTemp);
            }
            listTemp.add(s1);
        }
        System.out.println(testMap);

    }

    @Test
    public void testSubListAndRemove() {
        List<String> list = new ArrayList<>();
        list.add("0");
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("8");
        list.add("9");

        int len = list.size();
        //截取倒数第 n 个, 就 len - n
        List<String> strings = list.subList(len - 4, len);
        System.out.println(strings);

        //java.lang.IllegalArgumentException: -1
        //List<String> collect = list.stream().skip(-1).limit(3).collect(Collectors.toList());
        //List<String> collect = list.stream().skip(list.size()).limit(3).collect(Collectors.toList());
        List<String> collect = list.stream().skip(len < 2 ? len - 1 : len - 2).collect(Collectors.toList());

       // List<String> collect = list.stream().skip(len - 2).collect(Collectors.toList());
        System.out.println(collect);

        //移除倒数第一个
        strings.remove(strings.size() -1);
        System.out.println(strings);



    }

    @Test
    public void testListToArray() {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");

        int len = list.size();
        List<String> collect = list.stream().skip(len < 2 ? len - 1 : len - 2).collect(Collectors.toList());
        System.out.println(collect);

        String[] strings = list.toArray(new String[list.size()]);

        for (int i = 0; i < strings.length; i++) {
            System.out.println(strings[i]);
        }
    }
}
