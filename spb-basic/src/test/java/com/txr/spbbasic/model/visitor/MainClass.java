package com.txr.spbbasic.model.visitor;

public class MainClass {
	public static void main(String[] args) {
		Park park = new Park();
		park.setName("越秀公园");
		VisitorA visitorA = new VisitorA();

		park.accept(visitorA);

		VisitorB visitorB = new VisitorB();
		park.accept(visitorB);

		VisitorManager visitorManager = new VisitorManager();
		park.accept(visitorManager);
	}
}
