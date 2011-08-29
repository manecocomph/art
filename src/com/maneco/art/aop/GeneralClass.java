package com.maneco.art.aop;

public class GeneralClass {

	public String shout() {
		System.out.println("in GeneralClass.shout method");
		return "shout retun";
	}
	
	public int getAge() {
		System.out.println("in GeneralClass.getAge method");
		return 99;
	}
	
	public static void main(String[] args) {
		GeneralClass gc = new GeneralClass();
		System.err.println("in main, invoke shout " + gc.shout());
		System.err.println("in main, invoke getAge " + gc.getAge());
	}
}
