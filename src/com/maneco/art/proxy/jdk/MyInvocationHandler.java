package com.maneco.art.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.maneco.art.proxy.MyInterface;

public class MyInvocationHandler implements InvocationHandler {
	MyInterface mi = null;
	public MyInvocationHandler(MyInterface mi) {
		this.mi = mi;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("MyInvocationHandler.invoke method.getName() " + method.getName());
		System.out.println("MyInvocationHandler.invoke getDeclaringClass() " + method.getDeclaringClass());

		if (null != args) {
			for (Object obj : args) {
				System.out.println("MyInvocationHandler.invoke4 " + obj);
			}
		}

		if ("toString".equals(method.getName())) {
			return "eric ---- toString";
		} else if ("shout".equals(method.getName())) {
			return "in shout proxy";
		} else {
			return method.invoke(this.mi, args);
		}
		//return method.invoke(proxy, args);
	}

}
