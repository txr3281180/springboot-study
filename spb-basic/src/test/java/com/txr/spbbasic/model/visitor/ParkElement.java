package com.txr.spbbasic.model.visitor;

/*
 * 公园每一部分的抽象
 */
public interface ParkElement {
	//用来接纳访问者
	void accept(Visitor visitor);
}
