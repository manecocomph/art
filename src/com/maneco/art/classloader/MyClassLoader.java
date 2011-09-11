package com.maneco.art.classloader;

import java.io.File;

public class MyClassLoader extends ClassLoader {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {		
		try {
			Class clc = Class.forName("com.maneco.art.classloader.MyClassLoader");
			System.out.println(clc.newInstance());
			ClassLoader cl = clc.getClassLoader();
			System.out.println(cl.toString());
			
			MyClassLoader.printAllClassLoader(clc);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public static void printAllClassLoader(Object obj) {
		if (null == obj) {
			throw new NullPointerException("parameter can't be null");
		}
		Class clazz = obj.getClass();
		ClassLoader cl = clazz.getClassLoader();
		while (null != cl) {
			System.out.println(cl.toString());
			cl.getParent();
		}
	}
}
