package com.maneco.art.question.clone;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class House implements Cloneable, Serializable {
	private Door door;
	private String number;
	
	public Door getDoor() {
		return door;
	}
	public void setDoor(Door door) {
		this.door = door;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		House cloned = (House) super.clone();
		//TODO need check if null or not
		cloned.setDoor((Door) this.getDoor().clone());
		
		return cloned;
	}
	
	public static Object deepClone(Object obj) throws IOException, ClassNotFoundException {
		if (null == obj) {
			return null;
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(obj);
		ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
		ObjectInputStream ois = new ObjectInputStream(bais);
		Object deepCopy = ois.readObject();
		
		return deepCopy;
	}
}
