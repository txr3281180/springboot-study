package com.txr.spbbasic.model.cor;

public class CarHeadHandler extends CarHandler{

	public void HandlerCar() {
		System.out.println("组装车头");
		if(this.carHandler != null) {
			this.carHandler.HandlerCar();
		}
	}

}
