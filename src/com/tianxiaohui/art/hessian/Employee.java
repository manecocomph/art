package com.tianxiaohui.art.hessian;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long id;
	private Date birthDay;
	private String name;
	private int height;
	private Object other;
	
	@Override
	public String toString() {
		return super.toString() + " id: " + this.id + " name: " + this.name + " height: " + this.height + " birthday: " + this.birthDay + " other: " + this.other;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public Object getOther() {
		return other;
	}
	public void setOther(Object other) {
		this.other = other;
	}
	
	
}
