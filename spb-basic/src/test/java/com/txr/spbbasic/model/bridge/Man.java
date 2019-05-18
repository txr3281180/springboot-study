package com.txr.spbbasic.model.bridge;

public class Man extends Person {

	public Man(Address address) {
		super(address);
	}

	@Override
	public void show() {
		System.out.println("这是一个" + this.getAddress().address() + "男人");
	}

}
