package com.tianxiaohui.art.question.clone;

import java.util.Date;

public class CloneObj implements Cloneable {
	public String name;
	public Date dateOfBirth;
	
	
	//you'd better to override this method, to do some check
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public static void main(String... args) {
		CloneObj co = new CloneObj();
		co.name = "tianxiaohui";
		co.dateOfBirth = new Date();
		
		try {
			System.out.println("before change => co.name:\t" + co.name);
			System.out.println("before change => co.dateOfBirth:\t" + co.dateOfBirth);
			CloneObj another = (CloneObj) co.clone();   // clone object
			System.out.println("before change => another.name:\t" + another.name);
			System.out.println("before change => another.dateOfBirth:\t" + another.dateOfBirth);
			
			another.name = "eric";				// set new name for another object
			another.dateOfBirth.setYear(85);	// set the year of another object
			System.out.println("after change  => co.name:\t" + co.name);
			System.out.println("after change  => co.dateOfBirth:\t" + co.dateOfBirth);
			System.out.println("after change  => another.name:\t" + another.name);
			System.out.println("after change  => another.dateOfBirth:\t" + another.dateOfBirth);
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		House ha = new House();
		try {
			House hb = (House) ha.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
}
