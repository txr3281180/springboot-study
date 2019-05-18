package com.txr.spbbasic.model.command;

public class MainClass {
	public static void main(String[] args) {
		Peddler peddler = new Peddler();
//		peddler.sailApple();
//		peddler.sailBanana();

		Command appleCommand = new AppleCommand(peddler);
		Command bananaCommand = new BananaCommand(peddler);
//		appleCommand.sail();
//		bananaCommand.sail();
		Waiter waiter = new Waiter();

		//下订单
		waiter.setOrder(appleCommand);
		waiter.setOrder(bananaCommand);

		//移除订单某项
		waiter.removeOrder(appleCommand);

		waiter.sail();
	}
}

