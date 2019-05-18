package com.txr.spbbasic.model.command;

public class AppleCommand extends Command {
	//传入调用方
	public AppleCommand(Peddler peddler) {
		super(peddler);
	}

	public void sail() {
		this.getPeddler().sailApple();
	}
	
}
