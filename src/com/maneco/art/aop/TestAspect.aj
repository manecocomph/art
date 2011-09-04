package com.maneco.art.aop;

public aspect TestAspect {
	pointcut entry(int i): call(int fact(int)) && args(i);
    pointcut writing(): call(void println(String)) && ! within(Test);
    
    before(int i): writing() && cflow(entry(i)) {
        System.err.println("Current arg is " + i);
    }

	public static void main(String[] args) {
	// TODO Auto-generated method stub
	
	}

}
