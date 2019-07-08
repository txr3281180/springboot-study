package com.txr.spbbasic.juc;

/*
 * 一、volatile 关键字：当多个线程进行操作共享数据时，可以保证内存中的数据可见。
 * 					  相较于 synchronized 是一种较为轻量级的同步策略。
 * 
 * 注意：
 * 1. volatile 不具备“互斥性” （互斥性： 一个线程访问共享数据后，另一个线程不能访问。）
 * 2. volatile 不能保证变量的“原子性”（原子性：见Atomic。）
 */
public class Test1Volatile {

	//private boolean flag = false;
	//private static boolean flag = false;   //static 实例级别。类的静态成员，实例间共享

	private volatile boolean flag = false;   //volatile 线程级别。保证可见性，有序性，不能保证原子性

	public boolean getFlag() {return this.flag;}
	public void setFlag(boolean flag) {this.flag = flag; }

	//使用 volatile 直接在主存中读写， 会降低性能， 但是比锁的效率高

	public static void main(String[] args) {

		Test1Volatile tv = new Test1Volatile();

		new Thread(() -> {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
			}

			tv.setFlag(true);

			System.out.println("flag=" + tv.getFlag());

		}, "thread-1").start();  // thread-1 修改 flag 的值

		//while(true) 系统底层代码，执行效率非常高
		while(true) {
			if(tv.getFlag()){		//主线程 读取 flag。  如果不使用volatile，主线程会不停循环
				System.out.println("------------------");
				break;
			}
		}
	}



}
