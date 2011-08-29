package com.maneco.art.threadlocal;

public class MyThread implements Runnable {
	static ThreadLocalTester tlt = new ThreadLocalTester();
	
	@Override
	public void run() {
		System.out.println("Start ..." + Thread.currentThread().getId());
		tlt.getThreadId().set("threadlocal" + Thread.currentThread().getId());
		System.out.println(tlt.getThreadId() + " " + tlt.getThreadId().get());
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end ..." + Thread.currentThread().getId());
	}
}
