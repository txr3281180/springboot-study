package com.txr.spbbasic.java8;

import org.junit.Test;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by xinrui.tian on 2018/9/27.
 */
public class SetAPI {

    /**有序，无重复，只能存入一个null值*/

    @Test
    public void testSetDemo(){
        //Set 的方法与 List 类似，Set中没有指针,所以List中参数可以输入指针的方法Set中都没有
        //方法介绍查看List
        //Set<String> set = new HashSet<>();
        Set<String> set = new LinkedHashSet<>();
        //添加
        set.add("aa");
        set.addAll(set);
        //其他
        set.size();
        set.isEmpty();
        set.hashCode();
        set.toArray();
        set.toArray(new String[1]);
        set.equals(set);
        set.toString();
        //是否包含，取并集
        set.contains("aa");
        set.containsAll(set);
        set.retainAll(set);
        //迭代
        set.iterator();
        set.forEach(x -> System.out.println(x));
        set.spliterator();
        //流
        set.stream();
        set.parallelStream();
        //移除
        set.removeIf(x -> x.equals("aa"));
        set.remove("aa");
        set.removeAll(set);
        set.clear();
    }
}
