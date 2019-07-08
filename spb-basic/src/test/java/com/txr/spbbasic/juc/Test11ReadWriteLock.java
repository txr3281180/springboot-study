package com.txr.spbbasic.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * 1. ReadWriteLock : 读写锁
 * 
 * 写写/读写 需要“互斥”
 * 读读 不需要互斥
 * 
 */
public class Test11ReadWriteLock {

	public static void main(String[] args) {

		ReadWriteLockDemo rw = new ReadWriteLockDemo();
		//ReadWriteLockDemo2 rw = new ReadWriteLockDemo2();  //读写混乱

		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {

					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					rw.set((int) (Math.random() * 101));
				}
			}, "Write" + i).start();
		}
		
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					rw.get();
				}
			}).start();
		}
	}

}

class ReadWriteLockDemo2{

	private int number = 0;

	//读
	public void get(){

		System.out.println(Thread.currentThread().getName() + " : " + number);
	}

	//写
	public void set(int number){
		System.out.println(Thread.currentThread().getName() + ": set num " + number);
		this.number = number;
	}
}


class ReadWriteLockDemo{
	
	private int number = 0;
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	//读
	public void get(){
		lock.readLock().lock(); //上锁
		
		try{
			System.out.println(Thread.currentThread().getName() + " : " + number);
		}finally{
			lock.readLock().unlock(); //释放锁
		}
	}
	
	//写
	public void set(int number){
		lock.writeLock().lock();
		try{
			System.out.println(Thread.currentThread().getName() + ": set num " + number);
			this.number = number;
		}finally{
			lock.writeLock().unlock();
		}
	}
}