package com.txr.spbbasic.java8;

import com.txr.spbbasic.controller.model.Bond;
import com.txr.spbbasic.repository.access.BondAccess;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Spliterator;

/**
 * Created by xinrui.tian on 2018/9/27.
 */
public class ListAPI {

    /**
         List  add可以添加null（多个）, addAll 不可以（add添加的是元素，addAll添加的是对象）
         如果addAll中的List存放的是对象，则为引用赋值
         （源码使用的是System.arraycopy(a, 0, elementData, size, numNew);）

         1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。
         2.对于随机访问get和set，ArrayList优于LinkedList，因为LinkedList要移动指针。
         3.对于新增和删除操作add和remove，LinedList占优势，因为ArrayList要移动数据。

         removeAll: 如果要移除引用不同值相同的对象，对象需要重写hasCode， equls 方法
     */

    private List<Bond> bondList = BondAccess.getAllBond();

    @Test
    public void testList(){
        List<String> list1 = new LinkedList<>();
        list1.add("LinkedList");    // 底层为 LindkedList@873
        List<String> list2 = new ArrayList<>();
        list2.add("ArrayList");     // 底层为 ArrayList@851
        System.out.println(list2);  //

        //这是 List 与List 之间的赋值，并不是真正意义的linkedList = arrayList
        list1 = list2;  //list1与list2 底层都为 ArrayList@851
        System.out.println(list1);
    }


    @Test
    public void testListDemo() {
        /*添加*/
        List<String> list1 = new ArrayList<>();
        list1.add("AA");
        list1.add("DD");
        list1.add(1, "BB");  		//指针1的位置添加BB： [AA, BB, DD]
        list1.set(2, "CC");                 //指针2的位置替换为CC ：[AA, CC, DD]

        List<String> list2 = new ArrayList<>();
        list2.add("AA");
        list1.addAll(list2);        //添加集合list2集合到list中  [AA, BB, CC, AA]
        list1.addAll(2, list2);    //添加list2集合到list1集合index=2的位置  [AA, BB, AA, CC, AA]

        /*移除*/
        String str = list1.remove(1);   //移除指针1的元素 ,返回移除的元素 [AA, AA, CC, AA]
        boolean aa = list1.remove("AA");    //移除第一个AA元素，返回是否移除成功  [AA, CC, AA]
        boolean b = list1.removeAll(list2);     //移除list中的list2元素（包括重复数据） [CC]
        list1.clear();                         			 //清空元素   []
        //java8 如果list2中有元素等于AA，则删除该元素；返回是否删除 []
        boolean aa1 = list2.removeIf(x -> x.equals("AA"));

        /*获取与判断*/
        //Arrays.asList创建的list大小是固定的，增删操作回报   java.lang.UnsupportedOperationException
        List<String> list3 = Arrays.asList("aa", "bb", "cc", "aa");
        List<String> list4 = Arrays.asList("bb", "cc");

        System.out.println("get：" + list3.get(1));                      //获取指针1的元素
        System.out.println("size：" + list3.size());                     //集合长度

        System.out.println("indexOf：" + list3.indexOf("aa"));       //返回元素的index, 没有返回 -1;
        System.out.println("lastIndexOf：" + list3.lastIndexOf("aa"));  //返回AA元素最后出现的位置

        System.out.println("isEmpty：" + list3.isEmpty());               //集合是否为空
        System.out.println("equals：" + list3.equals(list4));            //是否相等
        System.out.println("contains：" + list3.contains("cc"));          //是否包含某个元素
        System.out.println("containsAll：" + list3.containsAll(list4)); //list3是否包含list4中所有元素

   	    /*替换， 排序*/
        // 参数：一元运算，么有返回值；list3中的所有元素替换为AA  [AA, AA, AA, AA]
        //list3.replaceAll(x -> "AA" );
        list3.replaceAll(x -> x + "A");                 //list3中的所有元素加A [aaA, bbA, ccA, aaA]
        list3.sort((x, y) -> y.compareTo(x));                        //排序
        System.out.println(list3);

        /*截取*/
        List<String> subList = list3.subList(0, 2);
        System.out.println(subList);   // [ccA, bbA]

   	    /*转换*/
        System.out.println("hashCode：" + list3.hashCode());         //hash值 -1269824543
        Object[] objects = list3.toArray();                          //转换为数组，Object类型
        String[] strings = list3.toArray(new String[list3.size()]);  //转换为数组，指定类型
        String listStr = list3.toString();                          //转换为String

        /*取交集*/
        List<String> list5 = new ArrayList<>();
        list5.add("AA");list5.add("BB");list5.add("CC");list5.add("DD");list5.add("EE");
        List<String> list6 = new ArrayList<>();
        //list6.add("AA");list6.add("BB");list6.add("CC");list6.add("DD");list6.add("EE");
        list6.add("XX");list6.add("BB");list6.add("ZZ");list6.add("DD");list6.add("yy");

        // 如果这个列表因调用而改变。返回true, list5存放交集元素
        // 当list5 与 list6 元素一致时或list5为空时返回false， list5返回空
        boolean b1 = list5.retainAll(list6);
        System.out.println(b1);
        System.out.println(list5);


        /*迭代*/
        //Spliterator并行遍历迭代器
        Spliterator<String> spliterator = list6.spliterator();
        /*  spliterator.characteristics();
            spliterator.estimateSize();
            spliterator.getComparator();
            spliterator.trySplit();
            spliterator.forEachRemaining();
            spliterator.getExactSizeIfKnown();
            spliterator.hasCharacteristics();
            spliterator.tryAdvance();*/

        // forEach遍历
        list5.forEach(x -> System.out.println(x));

        //iterator :迭代器（适用于所有集合） hasNext  next remove forEachRemaining
        Iterator<String> iterator = list6.iterator();
        while (iterator.hasNext()){
            if ("yy".equals(iterator.next())){
                iterator.remove();
            }

        }
        // iterator.forEachRemaining(x -> System.out.println(x));  //遍历剩余

        //listIterator : list专用迭代器
        System.out.println("---->" + list6);
        //list6.listIterator(2)  //指定参数，从第下标为2的元素开始
        ListIterator<String> listIterator = list6.listIterator();
        System.out.println(listIterator.previousIndex());  //起始  -1
        System.out.println(listIterator.nextIndex());  //起始  0
        //从前往后
        while (listIterator.hasNext()){
            String next = listIterator.next();
            //[XX, BB, ZZ, DD]
            if ("ZZ".equals(next)){
                System.out.println(listIterator.previousIndex());  //2
                System.out.println(listIterator.nextIndex());  //3
                // listIterator.add("&&&");  //添加到next前面
                // listIterator.remove();  //移除当前ZZ
                listIterator.set("WW");  //修改当前ZZ为WW
            }
        }
        System.out.println("-------------------");
        //从后往前
        System.out.println(listIterator.previousIndex());  // 上一个3
        System.out.println(listIterator.nextIndex());  //下一个坐标 4 （没有改坐标）
        while (listIterator.hasPrevious()){
            //前面使用 listIterator.hasNext()才会进入
            String previous = listIterator.previous();
            System.out.println(previous);
        }

 	    /*流*/
        list5.stream();   //流
        list5.parallelStream();  //并行流
    }



}
