package com.maneco.art.proxy;

import java.lang.reflect.Proxy;

public class ProxyTester {
	public static void main(String[] args) {
		//Proxy.getInvocationHandler(proxy)
		MyInterface mi = new MyImpl();
		Object obj = Proxy.newProxyInstance(MyImpl.class.getClassLoader(), new Class[]{MyInterface.class}, new MyInvocationHandler(mi));
		System.out.println(obj);
		System.out.println(obj.hashCode());
		System.out.println(((MyInterface) obj).shout("shlllllllll"));
		//new Proxy(new MyInvocationHandler());
		
		//Proxy.
		
		System.out.println(mi.shout("kkk"));
		
	}
}
