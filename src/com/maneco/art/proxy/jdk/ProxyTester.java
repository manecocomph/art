package com.maneco.art.proxy.jdk;

import java.lang.reflect.Proxy;

import com.maneco.art.proxy.MyImpl;
import com.maneco.art.proxy.MyInterface;
import com.maneco.art.proxy.MyInterface2;

public class ProxyTester {
	public static void main(String[] args) {
		ProxyTester.test();
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
	
	public static void test2() {

		//Proxy.getInvocationHandler(proxy)
		MyInterface mi = new MyImpl();
		Object obj = Proxy.newProxyInstance(MyImpl.class.getClassLoader(), new Class[]{MyInterface.class, MyInterface2.class}, new MyInvocationHandler(mi));
		
		if (null != obj) {
			System.out.println("obj.toString: " + obj.toString());
			System.out.println("obj.getClass: " + obj.getClass());
			Class[] interfaces = obj.getClass().getInterfaces();
			
			System.out.println("obj.getClass().getSuperclass() " + obj.getClass().getSuperclass());
			
			for (Class itfc : interfaces) {
				System.out.println(itfc.toString());
			}
		}
		/*System.out.println(obj);
		System.out.println(obj.hashCode());
		System.out.println(((MyInterface) obj).shout("shlllllllll"));*/
		//new Proxy(new MyInvocationHandler());
		
		//Proxy.
		
		
		System.out.println(((MyInterface)obj).shout("kkk"));
		System.out.println(mi.shout("kkk"));
		
		Class clazz = Proxy.getProxyClass(MyImpl.class.getClassLoader(), new Class[]{MyInterface.class, MyInterface2.class});
		try {
			clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
}
