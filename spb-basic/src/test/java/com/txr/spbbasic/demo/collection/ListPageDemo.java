package com.txr.spbbasic.demo.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xinrui.tian on 2018/9/27.
 */
public class ListPageDemo {


    /* stream 分页 */
    @Test
    public void testListPageByStream () {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 11; i++) {
            list.add("A" + i);
        }

        int len = list.size();
        int start = 0;
        int limit = 4;
        int count = len % limit == 0 ? len / limit : len / limit + 1;
        for (int i = 0;  i < count; start += limit, i++) {
            List<String> collect = list.stream().skip(start).limit(limit).collect(Collectors.toList());
            System.out.println("collect :" +  collect);
        }
    }

    @Test
    public void testSteamPage() {
        List<Integer> numList = new ArrayList<>();
        for (int i = 0; i < 1021; i++) {
            numList.add(i);
        }

        int pageSize = 100;
        int len = numList.size();
        int totalPage = len % 100 == 0 ? len /100 : len / 100 + 1;

        for (int i = 0; i < totalPage; i++) {
            List<Integer> collect = numList.stream().skip(i * pageSize).limit(pageSize).collect(Collectors.toList());
            System.out.println(collect);
            System.out.println("---------------第【 "+ i + 1 + "】页, 总数: 【" + len + "】, 当前数量：【" + collect.size() + "】\n");
        }

    }


    /**集合批处理*/
    @Test
    public void testListPage(){
        List<String> list = new ArrayList<>(9999);
        for (int i = 0; i < 10000; i++) {
            list.add(String.valueOf(i));
        }
        int len = list.size();
        System.out.println(len + "=============================");

        List<String> subList = new ArrayList<>(500);
        for(int index = 0, startIndex = 0; index < len; index++){

            subList.add(list.get(index));
            if (subList.size() < 500 && index < len - 1){  //批处理数量可设为可配置
                continue;
            }

            for (String s : subList) {
                System.out.println("==>" + s);
            }

            System.out.println("-------------" + startIndex + "-----------------------");
            subList.clear();
            startIndex = index + 1;
        }
    }


}
