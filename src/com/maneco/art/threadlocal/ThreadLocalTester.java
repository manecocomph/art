package com.maneco.art.threadlocal;

public class ThreadLocalTester {

	private ThreadLocal<String> threadId = new ThreadLocal<String>();
	
	public ThreadLocal<String> getThreadId() {
		return threadId;
	}


	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new MyThread()).start();
		}
	}

}
