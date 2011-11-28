package com.tianxiaohui.art.aop;

public class AspectTester {

	public void intArg(int i) {
		System.out.println("in AspectTester.intArg ..");
	}
	
	public void throwEx() throws AopException {
		throw new AopException("NPE");
	}
	
	String name;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
