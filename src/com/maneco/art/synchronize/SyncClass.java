package com.maneco.art.synchronize;

public class SyncClass {

	public synchronized void method1() throws InterruptedException {
		while (true) {
			System.err.println("in method 111");
			Thread.sleep(3000);
		}
	}
	
	public synchronized void method2() throws InterruptedException {
		while (true) {
			System.err.println("in method 222");
			Thread.sleep(3000);
		}
	}
	
	public synchronized void method3() throws InterruptedException {
		while (true) {
			System.err.println("in method 333");
			Thread.sleep(3000);
		}
	}
	
	public void method4() throws InterruptedException {
		synchronized(this.getClass()) {
			while (true) {
				System.err.println("in method 444");
				Thread.sleep(3000);
			}
		}
	}
	
	public void method5() throws InterruptedException {
		synchronized(this.getClass()) {
			while (true) {
				System.err.println("in method 555");
				Thread.sleep(3000);
			}
		}
	}
	
	public static void main(String... args) {
		new Thread(new MyRunnable(1)).start();
		new Thread(new MyRunnable(2)).start();
		new Thread(new MyRunnable(3)).start();
		new Thread(new MyRunnable(4)).start();
		new Thread(new MyRunnable(5)).start();
	}
}
