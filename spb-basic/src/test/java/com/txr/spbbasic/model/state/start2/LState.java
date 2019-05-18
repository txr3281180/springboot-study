package com.txr.spbbasic.model.state.start2;

public class LState extends State{

	public void doSomething(Person person) {
		if(person.getHour() == 12) {
			System.out.println("吃午饭");
		} else {
			person.setState(new SState());
			person.doSomething();
		}
	}

}
