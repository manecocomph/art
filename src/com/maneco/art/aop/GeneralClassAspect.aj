package com.maneco.art.aop;

public aspect GeneralClassAspect {

	//定义一个pointcut, 在调用GeneralClass的shout方法时候插入
	pointcut pointcutShout():
		call (String GeneralClass.shout());
	
	// 定义before的动作
	before(): pointcutShout() {
		System.out.println("in aspect GeneralClassAspect.beforeShout: before");
	}
	
	// 定义after的动作
	after() returning(String msg): pointcutShout() {
		System.out.println("in aspect GeneralClassAspect.beforeShout: after");
	}
	
	// 定义匿名的pointcut, 并且定义around时候的advice
	int around(): call (int GeneralClass.getAge()) {
		long start = System.currentTimeMillis();
		System.out.println("I am in around aspect before");
		Object obj = proceed(); // 真正的方法调用
		System.out.println("I am in around aspect after");
		// 打印真正的返回值
		System.out.println("I get " + obj + " when invoke");
		long end = System.currentTimeMillis();
		// 打印 方法需要时间
		System.out.println("Start: " + start + "  end: " + end);
		// 返回一个虚假的值
		return 3;
	}
	
	pointcut execsInProblemClass():
		within(GeneralClass) && execution(* *(..));
	
	before(): execsInProblemClass() {
		System.err.println("I am before any method in GeneralClass");
		System.err.println("file name: \t" + thisJoinPointStaticPart.getSourceLocation().getFileName());
	}
	
	Object around(): execsInProblemClass() {
		System.out.println("\t\t\tI am before ");
		Object obj = proceed();
		System.out.println("\t\t\tI am after ");
		
		return obj;
	}

}
