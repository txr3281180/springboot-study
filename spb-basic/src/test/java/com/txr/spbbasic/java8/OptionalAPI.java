package com.txr.spbbasic.java8;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by xinrui.tian on 2018/9/26.
 */
public class OptionalAPI {

    @Test
    public void test() {
        String str = "ABC";
        Optional<String> optional = Optional.of(str); //创建有值的Optional
        System.out.println(optional.get()); //获取
        System.out.println(optional.isPresent());   //是否包含值
        System.out.println(optional.orElse("default"));  //如果optional有值返回optional中的值，否则返回参数
        // orElse 类似于 map getOrDefault 方法
        //Map<String, String> map  = new HashMap<>();
        //map.getOrDefault("key", "default") 有值返回key对应的值，没值返回第二个参数

        System.out.println("==>orElseGet(Supplier s) 参数是 供给型接口 T get();");
        System.out.println(optional.orElse(getXYZ()));   //会执行getXYZ()方法
        System.out.println(optional.orElseGet(() -> getXYZ()));  //不执行
        System.out.println("==================");

        Optional<Object> empty = Optional.empty();  //创建空的Optional
        //System.out.println(empty.get()); //获取,空值报错 java.util.NoSuchElementException: No value present
        System.out.println(empty.isPresent());   //是否包含值
        System.out.println(empty.orElse("default"));

        String string = null;
        Optional<String> optionalStr = Optional.ofNullable(string); //如果 string 为 不为空创建实例，否则创建 Optional.empty
        System.out.println(optionalStr);

        //optionalStr.map()
        //optionalStr.flatMap()
    }

    private String getXYZ() {
        System.out.println("XYZ");
        return "XYZ";
    }

}
