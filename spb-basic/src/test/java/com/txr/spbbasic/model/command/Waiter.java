package com.txr.spbbasic.model.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务生
 */
public class Waiter {
	private List<Command> commands = new ArrayList<Command>();

	//下订单
	public void setOrder(Command command) {
		commands.add(command);
	}
	//移除订单
	public void removeOrder(Command command) {
		commands.remove(command);
	}
	
	public void sail() {
		for(Command command : commands) {
			command.sail();
		}
	}
}
