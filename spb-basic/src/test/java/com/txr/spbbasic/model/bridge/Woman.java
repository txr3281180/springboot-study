package com.txr.spbbasic.model.bridge;

public class Woman extends Person {

	public Woman(Address address) {
		super(address);
	}

	@Override
	public void show() {
		System.out.println("这是一个" + this.getAddress().address() + "女人");
	}
}
