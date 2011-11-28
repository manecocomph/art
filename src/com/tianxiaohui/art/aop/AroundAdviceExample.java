package com.tianxiaohui.art.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class AroundAdviceExample implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		System.out.println(" Advice Before method");
		long start = System.currentTimeMillis();
        Object obj = method.proceed();
        System.out.println("Advice After Method");
        long end = System.currentTimeMillis();
        
        System.out.println(obj);
        
		return obj;
	}

}
