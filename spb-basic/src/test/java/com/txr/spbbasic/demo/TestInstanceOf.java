package com.txr.spbbasic.demo;

/**
 * 理解 instanceof
 */
public class TestInstanceOf {
    //instanceof 运算符是用来在运行时指出对象是否是特定类的一个实例。
    //用法  object instanceof Class
    //  object 是否为  Class 的实例
    public static void main(String[] args) {
        Person p = new Person();
        Man m = new Man();
        Woman w = new Woman();
        Person p1 = new Man();
        Person p2 = new Woman();

        System.out.println((p instanceof Person));  //true
        System.out.println((m instanceof Person)); //true
        System.out.println((w instanceof Person)); //true

        System.out.println((p1 instanceof Man));
        System.out.println((p2 instanceof Man));
        System.out.println((m instanceof Man));

    }

}

class Person{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Man extends Person{
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

class Woman extends Person{
    private String sex;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

