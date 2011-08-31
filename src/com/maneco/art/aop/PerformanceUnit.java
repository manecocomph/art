package com.maneco.art.aop;

public class PerformanceUnit {
	public PerformanceUnit(String signature, long l) {
		this.signature = signature;
		this.elapseTime = l;
	}
	
	String signature;
	long elapseTime;
}
