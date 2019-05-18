package com.txr.spbbasic.model.state.start2;

public class MState extends State {

	public void doSomething(Person person) {
		if(person.getHour() == 7) {
			System.out.println("吃早饭");
		} else {
			person.setState(new LState());
			person.doSomething();
		}
	}

}
