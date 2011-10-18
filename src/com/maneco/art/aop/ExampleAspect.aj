package com.maneco.art.aop;

public aspect ExampleAspect {

	//定义一个pointcut, 在调用GeneralClass的shout方法时候插入
	// call (String GeneralClass.shout()) 是这个pointcut里面的join point
	pointcut pointcutShout():
		call (String GeneralClass.shout());
	
	// 定义before的advice
	// 对于pointcutShout 这个pointcut在执行它的join point之前, 输出下面那句
	before(): pointcutShout() {
		System.out.println("in aspect ExampleAspect: before");
	}
	
	public static void main(String[] args) {
		GeneralClass gc = new GeneralClass();
		gc.shout();
	}
}
