package com.txr.spbbasic.model.memento;

public class MainClass {
	public static void main(String[] args) {
		Person per = new Person("鸣人","男",17);
		
		Memento memento = per.createMemento();  //备份
		Caretaker caretaker = new Caretaker();
		caretaker.setMemento(memento);
		
		per.display();
		
		per.setName("小樱");
		per.setSex("女");
		per.setAge(16);
		
		per.display();

		//回滚操作
		per.rollback(caretaker.getMemento());
		per.display();
		
	}
}
