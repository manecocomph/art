package com.maneco.art.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	MyInterface mi = null;
	public MyInvocationHandler(MyInterface mi) {
		this.mi = mi;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		/*
		 * System.out.println("MyInvocationHandler.invoke1 " +
		 * proxy.getClass());
		 */
		// System.out.println("MyInvocationHandler.invoke2 " + proxy);

		System.out.println("MyInvocationHandler.invoke3 " + method.getName());

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
