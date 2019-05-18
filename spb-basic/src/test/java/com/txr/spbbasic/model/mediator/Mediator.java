package com.txr.spbbasic.model.mediator;

/**
 * 中介者
 */
public class Mediator {
    private Man man;
    private Woman woman;

    public void setMan(Man man) {
        this.man = man;
    }

    public void setWoman(Woman woman) {
        this.woman = woman;
    }

    public void getPartner(Person person) {
        //判断实例
        if (person instanceof Man) {
            if(man == null){
                if (person.getCondition() == woman.getCondition()) {
                    System.out.println(person.getName() + "与" + woman.getName() + "是一对情侣");
                } else {
                    System.out.println(person.getName() + "与" + woman.getName() + "异性朋友");
                }
                return;
            }
            System.out.println(man.getName() + "与" + person.getName() + "是一对同性朋友");
        } else {
            if(woman == null){
                if (person.getCondition() == man.getCondition()) {
                    System.out.println(person.getName() + "与" + man.getName() + "是一对情侣");
                    return;
                } else {
                    System.out.println(person.getName() + "与" + man.getName() + "异性朋友");
                    return;
                }
            }
            System.out.println(woman.getName() + "与" + person.getName() + "是一对同性朋友");
        }
    }
}
