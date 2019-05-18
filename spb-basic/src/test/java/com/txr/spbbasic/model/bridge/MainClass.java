package com.txr.spbbasic.model.bridge;

public class MainClass {
	public static void main(String[] args) {
		
		Address bj = new BeiJing();
		Address sh = new ShangHai();
		
		Person bjMan = new Man(bj);
		bjMan.show();
		
		Person shMan = new Man(sh);
		shMan.show();
		
		Person bjWoman = new Woman(bj);
		bjWoman.show();
		
		Person shWoman = new Woman(sh);
		shWoman.show();
		
	}
}
