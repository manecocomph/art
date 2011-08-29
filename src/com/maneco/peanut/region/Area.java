package com.maneco.peanut.region;

public class Area {
	public Area(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
}
