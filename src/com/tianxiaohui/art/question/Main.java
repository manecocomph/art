package com.tianxiaohui.art.question;

import java.io.IOException;

import com.tianxiaohui.art.question.clone.Door;
import com.tianxiaohui.art.question.clone.House;
import com.tianxiaohui.art.question.clone.Lock;

public class Main {

	public static void main(String[] args) {
		House ha = new House();
		ha.setNumber("007");
		ha.setDoor(new Door());
		ha.getDoor().setLock(new Lock());
		System.out.println(ha.toString());
		System.out.println(ha.getNumber().toString());
		System.out.println(ha.getDoor().toString());
		System.out.println(ha.getDoor().getLock().toString());
		try {
			House hb = (House) House.deepClone(ha);
			System.out.println(hb.toString());
			System.out.println(hb.getNumber().toString());
			System.out.println(hb.getDoor().toString());
			System.out.println(hb.getDoor().getLock().toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
