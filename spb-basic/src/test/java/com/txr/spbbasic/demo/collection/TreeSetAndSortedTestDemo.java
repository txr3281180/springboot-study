package com.txr.spbbasic.demo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by xinrui.tian on 2018/12/12
 */
public class TreeSetAndSortedTestDemo {

    /*
     * 测试引用变量排序
     */
    @Test
    public void testSort2() {
        Map<String, List<String>> map = new HashMap<>();
        List<String> list = new ArrayList<>();
        list.add("d");
        list.add("f");
        list.add("a");
        list.add("c");

        list = list.stream().sorted().collect(Collectors.toList());
        map.put("A", list);
        System.out.println(map);

        List<String> a = map.get("A");
        a.add("e");

        //直接排序引用变量不生效， 需要重新put一次
        a = a.stream().sorted().collect(Collectors.toList());
        map.put("A", a);
        System.out.println(map);
    }



    /*
        java.lang.IllegalArgumentException: Comparison method violates its general contract!
    *   （信用发行设排序用户最新下载时间报错）
    *   java1.7以上 排序报错（TimSort算法） ：Comparison method violates its general contract!
    *
    *   解决方式一：使用java1.6的排序算法
    *       jvm参数添加  -Djava.util.Arrays.useLegacyMergeSort=true
    *
    *   解决方式二：
    *     > TimSort算法必须满足：
    *       1. 自反性，compare(x, y) = - compare(y, x)
    *       2. 传递性，如果compare(x, y) > 0, compare(y, z) > 0, 则必须保证compare(x, z) > 0
    *       3. 对称性, 如果compare(x, y) == 0, 则必须保证compare(x, z) == compare(y, z)
    *     > 如果不满足以上3个条件，TimeSort并不是一定会报错，在某些序列条件下才会报错，这与TimeSort的排序逻辑有关。
    *       同时只有在排序序列长度在32以上时才有可能抛异常，因为在排序序列长度小于32时，TimeSort会直接退化为二分排序。
    *       （ps: 使用stream limit 测试过）
    *     > 排序的值中存在null
    *          sort 排序正确写法
    *         (x,y)-> {
    *                   if(x == null && y == null) return 0;
    *                   if(x == null) return 1;
    *                   if(y == null) return -1;
    *                   return x.compareTo(y);
    *                  }
    *
    * */
    @Test
    public void testSort(){
        List<String> list = new ArrayList<>();
        list.add(null);
        list.add("a");
        list.add(null);
        list.add("f");
        list.add("a");
        list.add("a");
        list.add("e");
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        //list.add("a");
        // 排序序列长度>=32时
        // 且含有null值
        // 且没有判断两个比较值都为null的情况  报错 Comparison method violates its general contract!

        list.stream().sorted((x, y) -> {
            //if (x == null && y == null) return 0;  正确写法加上都为null的情况
            if (x == null) return 1;
            if (y == null) return -1;
            return x.compareTo(y);
        }).forEach(x -> System.out.println(x));

    }


    /* Comparator排序 与 Comparable排序*/
    @Test
    public void testTreeSet () {
        TreeSet<String> descSet = new TreeSet<>(descComparator);
        descSet.add("b");
        descSet.add("a");
        descSet.add("c");
        descSet.add("2");
        descSet.add("1");
        System.out.println(descSet);


        TreeSet<String> ascSet = new TreeSet<>(ascComparator);
        ascSet.add("b");
        ascSet.add("a");
        ascSet.add("c");
        ascSet.add("2");
        ascSet.add("1");
        System.out.println(ascSet);


        List<Student> list = new ArrayList<>();
        list.add(new Student("2","d"));
        list.add(new Student("1","e"));
        list.add(new Student("2","a"));
        list.add(new Student("4","b"));
        list.add(new Student("3","c"));

        List<Student> collect = list.stream().sorted().collect(Collectors.toList());
        System.out.println(collect);

    }



    private final TreeSetComparator descComparator = new TreeSetComparator(true);
    private final TreeSetComparator ascComparator = new TreeSetComparator(false);


    class TreeSetComparator implements Comparator<String> {

        private boolean isDesc;

        public TreeSetComparator (boolean isDesc) {
            this.isDesc = isDesc;  //排序方式
        }

        @Override
        public int compare(String a, String b) {
            return isDesc?b.compareTo(a): a.compareTo(b);
        }
    }

    //=================================================================================
    class Student implements Comparable<Student> {

        private String id;

        private String name;

        public Student(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int compareTo(Student o) {
            return o.id.compareTo(this.id) == 0? this.name.compareTo(o.name) :o.id.compareTo(this.id);
        }

        @Override
        public String toString() {
            return "Student{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

}
