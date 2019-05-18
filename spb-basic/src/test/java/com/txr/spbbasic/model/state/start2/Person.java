package com.txr.spbbasic.model.state.start2;

public class Person {
	private int hour;
	private State state;

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}
	
	public Person() {
		state = new MState();
	}
	
	public void doSomething(){

		state.doSomething(this);
		//复位到最先的状态，当所以方法以后再执行。
		state = new MState();
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}
