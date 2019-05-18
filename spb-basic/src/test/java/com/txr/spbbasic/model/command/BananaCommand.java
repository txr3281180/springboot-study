package com.txr.spbbasic.model.command;

public class BananaCommand extends Command{
	//传入调用方
	public BananaCommand(Peddler peddler) {
		super(peddler);
	}

	public void sail() {
		this.getPeddler().sailBanana();
	}

}
