package com.tianxiaohui.art.aop;

public aspect GeneralClassAspect {
	public boolean GeneralClass.isRunning = false;
	
	//定义一个pointcut, 在调用GeneralClass的shout方法时候�?�入
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
	
	// 定义匿�??的pointcut, 并且定义around时候的advice
	int around(): call (int GeneralClass.getAge()) {
		long start = System.currentTimeMillis();
		System.out.println("I am in around aspect before");
		Object obj = proceed(); // 真正的方法调用
		System.out.println("I am in around aspect after");
		// 打�?�真正的返回值
		System.out.println("I get " + obj + " when invoke");
		long end = System.currentTimeMillis();
		// 打�?� 方法需�?时间
		System.out.println("Start: " + start + "  end: " + end);
		// 返回一个虚�?�的值
		return 3;
	}
	


	pointcut execsInProblemClass(GeneralClass mm):
		within(GeneralClass) && execution(* *(..)) && target(mm);
	
	before(GeneralClass mm): execsInProblemClass(mm) {
		System.err.println("I am before any method in GeneralClass");
		System.err.println("file name: \t" + thisJoinPointStaticPart.getSourceLocation().getFileName());
	}
	
	
	
	public static void checkStatus(GeneralClass mm) {
		System.err.println("---------" + mm);
	}
	
	Object around(GeneralClass mm): execsInProblemClass(mm) {
		System.out.println("\t\t\tI am before ");
		Object obj = proceed();
		System.out.println("\t\t\tI am after ");
		checkStatus(mm);
		return obj;
	}
	
	
	public String GeneralClass.name = "";
	
	pointcut testCall(GeneralClass gc):
		target(gc) && call (* testCallExec(..));
	
	before(GeneralClass gc): testCall(gc) {
		System.out.println("before-call testCallExec .." + gc.name);
	}
	
	pointcut testExec():
		execution(* testCallExec(..));
	
	before(): testExec() {
		System.out.println("before-exec testCallExec ..");
	}
	
	before(): cflow(testExec()) {
		System.out.println("in cflow");
	}

	public static void main(String[] args) {
		GeneralClass gc = new GeneralClass();
		gc.name = "qing";
		gc.testCallExec("eric", 3);
	}
	

}
