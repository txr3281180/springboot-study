package com.txr.spbbasic.model.prototype;

import java.util.ArrayList;
import java.util.List;

public class Student implements Cloneable{

    private String name;

    private List<String> friend;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getFriend() {
        return friend;
    }

    public void setFriend(List<String> friend) {
        this.friend = friend;
    }

    public Student clone(){

        try {
            Student s = (Student) super.clone();
            //引用变量深度赋值
            List<String> friend = new ArrayList<>();
            for (String str : this.getFriend()) {
                friend.add(str);
            }
            s.setFriend(friend);

            return s;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", friend=" + friend +
                '}';
    }
}
