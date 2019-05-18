package com.txr.spbbasic.model.visitor;

/*
 * 访问者
 */
public interface Visitor {
	void visit(Park park);
	void visit(ParkA parkA);
	void visit(ParkB parkB);
}
