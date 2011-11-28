package com.tianxiaohui.art.proxy.cglib;

import net.sf.cglib.proxy.Proxy;

import com.tianxiaohui.art.proxy.MyImpl;
import com.tianxiaohui.art.proxy.MyInterface;
import com.tianxiaohui.art.proxy.MyInterface2;

public class ProxyTester {
	public static void main(String[] args) {
		ProxyTester.test();
	}

	/**
	 * @param args
	 */
	public static void test2() {
		MyInterface mii = new MyImpl();
		// Proxy p = new Proxy(new MyInvocationHandler(mii));
		Class proxyClass = Proxy.getProxyClass(ProxyTester.class.getClassLoader(), new Class[] { MyInterface.class, MyInterface2.class });
		// MyInterface mi = (MyInterface) proxyClass.newInstance();

		MyInterface mi = (MyInterface) Proxy.newProxyInstance(ProxyTester.class.getClassLoader(), new Class[] { MyInterface.class, MyInterface2.class }, new MyInvocationHandler(mii));
		System.out.println("wowwowo" + mi.shout("KKKK"));

		
	}
	
	public static void test() {
		long start = System.currentTimeMillis();
		int count = 0;
		
		while (true) {
			if (6000 < (System.currentTimeMillis() - start)) {
				break;
			}
			
			MyInterface mii = new MyImpl();
			MyInterface mi = (MyInterface) Proxy.newProxyInstance(ProxyTester.class.getClassLoader(), new Class[] { MyInterface.class, MyInterface2.class }, new MyInvocationHandler(mii));
			System.out.println("wowwowo" + mi.shout("KKKK"));
			count++;
		}
		
		System.out.println("count : " + count);
	}

}
