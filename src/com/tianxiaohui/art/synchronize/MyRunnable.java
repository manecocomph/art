package com.tianxiaohui.art.synchronize;

public class MyRunnable implements Runnable {
	private int num = -1;
	private static SyncClass sc1 = new SyncClass();
	private static SyncClass sc2 = new SyncClass();
	
	public MyRunnable (int i) {
		this.num = i;
	}
	@Override
	public void run() {
		try {
			if (1 == this.num) {
				sc1.method1();
			} else if (2 == this.num) {
				sc1.method2();
			} else if (3 == this.num) {
				sc2.method3();
			} else if (4 == this.num) {
				sc1.method4();
			} else if (5 == this.num) {
				sc2.method5();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
