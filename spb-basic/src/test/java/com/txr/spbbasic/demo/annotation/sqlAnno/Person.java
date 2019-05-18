package com.txr.spbbasic.demo.annotation.sqlAnno;

@Table("user")
public class Person {
    @Column("name")
    private String name;

    @Column("sex")
    private String sex;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Column("age")
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
