package com.txr.spbbasic.model.mediator;

public class MainClass {
	public static void main(String[] args) {
		Mediator m = new Mediator();
		Person mingRen = new Man("鸣人",5, m);
		Person zuoZu = new Man("佐助",6, m);
		Person xiaoYing = new Woman("小樱", 6, m);

		xiaoYing.getPartner(mingRen);
		xiaoYing.getPartner(zuoZu);
		mingRen.getPartner(zuoZu);
		
	}
}	
