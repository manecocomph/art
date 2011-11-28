package com.tianxiaohui.art.aop;

public aspect ExampleAspect {

	//定义一个pointcut, 在调用GeneralClass的shout方法时候�?�入
	// call (String GeneralClass.shout()) 是这个pointcut里�?�的join point
	pointcut pointcutShout():
		call (String GeneralClass.shout());
	
	// 定义before的advice
	// 对于pointcutShout 这个pointcut在执行它的join point之�?, 输出下�?�那�?�
	before(): pointcutShout() {
		System.out.println("in aspect ExampleAspect: before");
	}
	
	public static void main(String[] args) {
		GeneralClass gc = new GeneralClass();
		gc.shout();
	}
}
