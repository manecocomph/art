package com.maneco.art.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class MyMethods {

	public String testMethod1() {
		System.out.println("\tIn method testMethod1");
		
		return "testMethod1";
	}
	
	public int testMethod2() {
		System.out.println("\tIn method testmethod2");
		
		return 9;
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext applicationContext = new FileSystemXmlApplicationContext("C:/Users/xiatian/workspace/Trail/config/spring.xml");
		MyMethods simpleInterface = (MyMethods) applicationContext.getBean("proxyBean");
		//System.out.println(simpleInterface.testMethod1());
		
		//MyMethods mm = new MyMethods();
		System.out.println(simpleInterface.testMethod1());
		System.out.println(simpleInterface.testMethod2());

	}

}
