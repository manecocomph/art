package com.maneco.art.aop;

public aspect TesterAspect {

	final pointcut myPoint():
		call (* AspectTester.*(..));
	
	/**
	 * call and execution 2 times
	 * if remove !this(TesterAspect), then recursive ...
	 * @param i
	 */
	pointcut intArg(int i): args(i) && !this(TesterAspect);
	
	before(int i): intArg(i) {
		System.out.println(thisJoinPoint.getSignature());
		System.out.println(thisJoinPoint.getKind());
		System.out.println(thisJoinPoint.getTarget());
		System.out.println(thisJoinPoint.getThis());
		System.out.println(thisJoinPoint.toLongString());
		System.out.println(thisJoinPoint.getSourceLocation());
		System.out.println("before in argInt");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AspectTester at = new AspectTester();
		at.intArg(9);
	}

}
