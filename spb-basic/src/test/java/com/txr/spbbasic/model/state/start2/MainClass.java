package com.txr.spbbasic.model.state.start2;

public class MainClass {
	public static void main(String[] args) {
		Person person = new Person();  //初始化状态为 MState
		
		person.setHour(7);
		person.doSomething();
		
		person.setHour(12);
		person.doSomething();
		
		person.setHour(18);
		person.doSomething();
		
		person.setHour(8);
		person.doSomething();
		
		person.setHour(7);
		person.doSomething();
		
		person.setHour(18);
		person.doSomething();
		
	}
}
