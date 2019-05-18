package com.txr.spbbasic.java8;

import com.txr.spbbasic.controller.model.Bond;
import com.txr.spbbasic.repository.access.BondAccess;
import org.junit.Test;

import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by xinrui.tian on 2018/9/25.
 */
public class StreamAndLambdaAPI {

    private List<Bond> bondList = BondAccess.getAllBond();
    private List<String> list1 = Arrays.asList("c","b","a");
    private List<String> list2 = Arrays.asList("x","y","z");



    /**
     ①.无参数，无返回值
     Runnable run2 = () ->  System.out.println("Lambda");
     ②.有一个参数，无返回值（参数括号可以省略）
     Comparator<Integer> com = (x) -> System.out.println("Lambda");
     ③.有多个参数，Lambda体中只有一句返回语句(可以省略return)
     Comparator<Integer> com = (x, y) -> Integer.compare(x, y);
     ④.有多个参数，Lambda体中有多条语句，使用 “{}”
     Comparator<Integer> com = (x, y) -> {
     System.out.println("Lambda");
     return Integer.compare(x, y);
     }

     >Lambda 表达式需要“函数式接口”的支持
     函数式接口：接口中只有一个抽象方法的接口，称为函数式接口。
     >可以使用注解 @FunctionalInterface 修饰, 可以检查是否是函数式接口。
     接口添加该注解后，只能有一个抽象方法，否则编译报错。

     Consumer<T> :  消费型接口   void accept(T t);
     Supplier<T> : 供给型接口    T get();
     Function<T, R> : 函数型接口  R apply(T t);
     Predicate<T> : 断言型接口   boolean test(T t);
     */
    @Test
    public void testLambda () {
        //匿名内部类
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                System.out.println("Runnable");
            }
        };

        //lambda
        Runnable run2 = () -> System.out.println("Runnable");
    }



    @Test
    public void testStream () {
        //=============【创建流】=====================================
        //通过 Collection 系列集合提供的 stream() 或者 parallelStream()
        Stream<String> stream = list1.stream();
        Stream<String> stringStream = list2.parallelStream();   //并行流


        //通过Arrays 中的静态方法 stream() 获取数组流
        String[] str = new String[3];
        Stream<String> stream1 = Arrays.stream(str);  //数组

        //通过 Stream 类中的静态方法 of()
        Stream<List<String>> stream2 = Stream.of(list1);
        Stream<String> stream3 = Stream.of(str);

        //4.创建无限流
        //迭代方式
        Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
        stream4.limit(10).forEach(System.out::println);
        //生成方式
        Stream<Double> stream5 = Stream.generate(() -> Math.random());
        stream5.limit(5).forEach(System.out::println);
    }

    @Test
    public void testStream1() {
        //=============【常用】=====================================
        List<String> result = bondList.stream()
                .filter(bond -> bond.getIssuePrice() > 0)  //过滤
                .map(Bond::getIssuerName)                  //映射
                .distinct()                                //去重
                .skip(2)                            //从第三个开始      【分页：当前页】
                .limit(4)                           //截取四个         【分页：每页数量】
                .collect(Collectors.toList());      //集合
        //collect(Collectors.toSet()) 去重更快
        System.out.println(result);

        //=============【sorted】=====================================
        //【compareTo】:
        //      数据类型比大小，返回 -1（小于） 0（等于） 1（大于）；
        //      字符型比大小，返回 底层编码差值  负数（小于） 0（等于） 正数（大于）
        //【sorted()】 默认升序;
        //            (x,y) -> {x.compareTo(y)} 升序asc
        //            (x,y) -> {y.compareTo(x)} 倒序desc
        //  实质是 冒泡排序；【y EleIndex 0,  x EleIndex 1】;
        list1.stream().sorted((b1, b2) -> b1.compareTo(b2)).forEach(System.out::println); //【排序】abc
        //1【位置不变】，0【不变】，-1【调换位置】
        // 将集合中的null排到最后 ：
        //      (x,y)-> {
        //                  if(x == null && y == null) return 0;
        //                  if(x == null) return 1;
        //                  if(y == null) return -1;
        //                  return x.compareTo(y);
        //              }
        list2.stream().sorted((b1, b2) -> -1).forEach(System.out::println);  //【反转】zyx

        //=============【flatMap 将 集合扁平化】=====================================
        //原理类似于集合中addAll
        //应用场景：集合中有多个对象，每个对象都有一个集合属性，将所有对象的集合集中映射到一个集合中可以使用flatMap
        List<List<String>> list3 = Arrays.asList(list1, list2);
        System.out.println(list3);  //[[c, b, a], [x, y, z]]
        List<String> list4 = list3.stream().flatMap(str -> str.stream()).collect(Collectors.toList());
        System.out.println(list4);  //[c, b, a, x, y, z]
    }

    @Test
    public void testStream2 () {
        //=============【匹配】=====================================
        boolean a = list1.stream().allMatch(x -> x.equals("a"));   //都满足为true
        boolean b = list1.stream().anyMatch(x -> x.equals("a"));   //有一个满足为为true
        boolean c = list1.stream().noneMatch(x -> x.equals("a"));  //都不满足为true
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        //=============【查询】=====================================
        Optional<String> any = list1.stream().findAny();    //寻找任意元素
        Optional<String> first = list1.stream().findFirst();//寻找第一个元素
        System.out.println("any: " + any.get() + "  first: " + first.get());

        long count = list1.stream().count();
        System.out.println("总数: " + count);                        //查询总数

        //寻找某个字段最大或者最小的元素
        Optional<Bond> max = bondList.stream().max((x, y) -> x.getIssuePrice().compareTo(y.getIssuePrice()));
        Optional<Bond> min = bondList.stream().min((x, y) -> x.getIssuePrice().compareTo(y.getIssuePrice()));
        System.out.println(max.get());
        System.out.println(min.get());
    }

    @Test
    public void testStrem3 () {
        //=============【Collectors】=====================================
        //【List<Bond>】
        bondList.stream().map(Bond::getIssuerName).collect(Collectors.toList());
        //【Set<Bond>】
        bondList.stream().map(Bond::getIssuerName).collect(Collectors.toSet());
        //【特殊集合】
        bondList.stream().map(Bond::getIssuerName).collect(Collectors.toCollection(HashSet::new));
        //【Map<String, List<Bond>>】
        bondList.stream().collect(Collectors.groupingBy(Bond::getIssuerName));
        //【Map<String, Set<Bond>>】
        bondList.stream().collect(Collectors.groupingBy(Bond::getIssuerName, Collectors.toSet()));
        //【Map<String, Map<String, List<Bond>>>】 多重分组
        bondList.stream().collect(Collectors.groupingBy(Bond::getIssuerName, Collectors.groupingBy(Bond::getBondName)));
        // key组合分组
        bondList.stream().collect(Collectors.groupingBy(bond -> bond.getIssuerName() + "-" + bond.getBondName()));


        //【groupingBy 与 toMap 是用区别的； toMap始终都是 一个key对一个value】
        //【Map<String, String>】 *如果集合中映射的key有重复会报错
        bondList.stream().collect(Collectors.toMap(Bond::getBondKey, Bond::getBondName));
        //【Map<String, String>】 *解决重复报错
        bondList.stream().collect(Collectors.toMap(Bond::getBondKey, Bond::getBondName, (k1, k2) -> k1));
        //【Map<String, Bond>】
        bondList.stream().collect(Collectors.toMap(Bond::getBondKey, Function.identity()));
        bondList.stream().collect(Collectors.toMap(Bond::getBondKey, Function.identity(), (K1, K2) -> K2));
        bondList.stream().collect(Collectors.toMap(Bond::getBondKey, x -> x, (k1, k2) -> k2));  //Function.identity() == x -> x

        //拼接
        list1.stream().collect(Collectors.joining("','", "'", "'")); //'c','b','a'
        list1.stream().collect(Collectors.joining(","));                    //c,b,a
        list1.stream().collect(Collectors.joining());                                //cba
        //list参数只有一个的时候不进行拼接

        //Collectors可用于统计见示例方法testStream4()
    }

    @Test
    public void testStream4 () {
        //=============【统计】XXXSummaryStatistics=====================================
        DoubleSummaryStatistics result = bondList.stream()
                .mapToDouble(Bond::getCouponRate)  //存在精度问题
                .summaryStatistics();
        //【mapToInt】  【mapToLong】【mapToDouble】
        System.out.println("最大" + result.getMax());
        System.out.println("最小" + result.getMin());
        System.out.println("求和" + result.getSum());
        System.out.println("平均" + result.getAverage());
        System.out.println("数量" + result.getCount());

        //====================【averagingLong】【summingLong】===============================
        Double avg = bondList.stream().collect(Collectors.averagingLong(Bond::getIssuePrice)); //平均值
        Long sum = bondList.stream().collect(Collectors.summingLong(Bond::getIssuePrice));  //求和
        System.out.println("avg: " + avg + " sum: " + sum);

        //【summarizingLong】
        //LongSummaryStatistics sum1 = bondList.stream().collect(Collectors.summarizingLong(Bond::getIssuePrice));
        //sum1.getAverage(); sum1.getSum(); sum1.getMax(); sum1.getMin(); sum1.getCount();

        //====================【归约reduce】====================================================
        //两种写法
        Long reduce = bondList.stream().map(Bond::getIssuePrice).reduce(0L, (x, y) -> x + y); //从0开始求和
        System.out.println(reduce);
        Optional<Long> reduceOp = bondList.stream().map(Bond::getIssuePrice).reduce((x, y) -> x + y);
        System.out.println(reduceOp.get());
    }
}
