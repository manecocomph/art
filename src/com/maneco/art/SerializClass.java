package com.maneco.art;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class SerializClass implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	String name = "default";

	@Override
	public String toString() {
		return super.toString() + "name: " + name;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public static void main(String[] args) {
		ObjectOutputStream oos = null;
		ObjectInputStream ois =  null;
		 
		try {
			// #0 new
			SerializClass obj = new SerializClass();
			
			// #1 cloneable
			SerializClass obj0 = (SerializClass) obj.clone();
			obj0.name = "Cloneable";
			
			// #2 class for name
			Class clazz = Class.forName("com.maneco.art.SerializClass");
			SerializClass obj1 = (SerializClass) clazz.newInstance();
			obj1.name = "ClassForName";
			
			// #3 reflect 
			SerializClass obj2 = null;
			Constructor[] cs = SerializClass.class.getConstructors();
			
			for (Constructor c : cs) {
				obj2 = (SerializClass) c.newInstance(null);
			}
			obj2.name = "Reflect";
			
			// #4 deserialize
			oos = new ObjectOutputStream(new FileOutputStream("Object.txt"));
			oos.writeObject(obj2);
			oos.flush();
			
			ois =  new ObjectInputStream(new FileInputStream("Object.txt"));
			SerializClass obj3 = (SerializClass) ois.readObject();
			
			// print
			System.out.println(obj);
			System.out.println(obj0);
			System.out.println(obj1);
			System.out.println(obj2);
			System.out.println(obj3);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		} finally {
			if (null != oos) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			} 
			
			if (null != ois) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
