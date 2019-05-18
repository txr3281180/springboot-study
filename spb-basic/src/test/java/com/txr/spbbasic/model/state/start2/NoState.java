package com.txr.spbbasic.model.state.start2;

public class NoState extends State {

	public void doSomething(Person person) {
		System.out.println(person.getHour() +  "未定义");
	}

}
