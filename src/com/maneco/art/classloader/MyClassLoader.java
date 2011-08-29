package com.maneco.art.classloader;

import java.io.File;

public class MyClassLoader extends ClassLoader {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*File f = new File("C:/Users/xiatian/Downloads/apache-cxf-2.3.3/apache-cxf-2.3.3/lib/org/apache/cxf/Bus.class");
		System.out.println(f.exists());*/
		
		try {
			Class.forName("com.maneco.art.classloader");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
