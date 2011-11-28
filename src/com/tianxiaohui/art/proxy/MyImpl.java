package com.tianxiaohui.art.proxy;

public class MyImpl implements MyInterface {

	@Override
	public String shout(String str) {
		System.out.println("in impl shout");
		return "eric";
	}

	@Override
	public int getAge() {
		System.out.println("in impl shout");
		return 56;
	}

}
