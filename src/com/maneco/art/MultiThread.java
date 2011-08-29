package com.maneco.art;

import java.text.ParseException;

public class MultiThread implements Runnable {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		new Thread(new MultiThread()).start();
		System.out.print("\n");
		System.out.print(System.currentTimeMillis() - start);
	}

	@Override
	public void run() {
		
		try {
			for (int i = 0; i < 10000; i++) {
				//System.out.print(Util.getDateFomrater().parse("2000-01-05"));
				System.out.print(Util.formatStr("2000-01-05"));
				//System.out.print(Util.getDateFormaterByThread().parse("2000-01-05"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
