package com.txr.spbbasic.model.factory;

public class FruitFactory {


    /**
     * 设计一
     * @return
     */
    public Fruit getApple(){
        return new Apple();
    }

    public Fruit getBanana(){
        return new Banana();
    }

    /**
     * 设计二
     * @param clazzName
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public Fruit getFruit(String clazzName) throws IllegalAccessException, InstantiationException {

        if("apple".equalsIgnoreCase(clazzName)){
            return Apple.class.newInstance();
        } else if("banana".equalsIgnoreCase(clazzName)){
            return Banana.class.newInstance();
        } else {
            System.out.println("没有对应的类");
            return null;
        }

    }

    /**
     * 设计三
     * @param clazzName
     * @return
     * @throws Exception
     */
    public static Fruit getFruitForName(String clazzName) throws Exception {

        //clazzName 需要时全类名，可能报错
        return (Fruit) Class.forName(clazzName).newInstance();
    }
}
