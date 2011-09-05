package com.maneco.art.aop;

public aspect TesterAspect {

	final pointcut myPoint():
		call (* AspectTester.*(..));
	
	/**
	 * call and execution 2 times
	 * if remove !this(TesterAspect), then recursive ...
	 * @param i
	 */
	pointcut intArg(int i):
		args(i) && !this(TesterAspect) && within(AspectTester);
	
	before(int i): intArg(i) {
		System.out.println(thisJoinPoint.getSignature());
		System.out.println(thisJoinPoint.getKind());
		System.out.println(thisJoinPoint.getTarget());
		System.out.println(thisJoinPoint.getThis());
		System.out.println(thisJoinPoint.toLongString());
		System.out.println(thisJoinPoint.getSourceLocation());
		System.out.println("before in argInt\n");
	}
	
	pointcut getName():
		get(String AspectTester.name);
	
	before(): getName() {
		System.out.println("in before getName\n");
	}
		
	public int AspectTester.age;
	
	pointcut setAge():
		set(int AspectTester.age);
	
	void around(): setAge() {
		System.out.println("in around set Age\n");
		//proceed();
	}
	
	pointcut handleException():
		handler(AopException);
	
	before(): handleException() {
		System.out.println("in handle exception");
		
	}
	
	pointcut inAdviceExec():
		adviceexecution() && within(TesterAspect);
	// must !this(TesterAspect);
	before(): inAdviceExec() && !this(TesterAspect) {
		System.out.println("in advice exec");
	}
	
	pointcut testCflowbelow():
		cflowbelow(handleException());
	
	before(): testCflowbelow() {
		System.out.println("in testCflowBelow");
	}
	/*13229010632  张俊 广州市朝阳区延安路广德小区 1栋1单元102室
	498*/
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AspectTester at = new AspectTester();
		/*at.intArg(9);
		
		System.out.println(at.name);*/
		//System.err.println(at.age);
		//at.age = 9;
		
		//System.err.println(at.age);
		try {
		at.throwEx();
		} catch(AopException a) {
			a.printStackTrace();
		}
	}

}
