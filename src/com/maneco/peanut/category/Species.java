package com.maneco.peanut.category;

import java.util.Date;

public class Species {
	
	public Species(int id, int productId, String nameCn, String nameEn,
			int priority, String keyWords, String desc, Date insertTime) {
		this.id = id;
		this.productId = productId;
		this.nameCn = nameCn;
		this.nameEn = nameEn;
		this.priority = priority;
		this.keyWords = keyWords;
		this.desc = desc;
		this.insertTime = insertTime;
	}
	
	private int id;
	private int productId;
	private String nameCn;
	private String nameEn;
	private int priority;
	private String keyWords;
	private String desc;
	private Date insertTime;
	
	public int getId() {
		return id;
	}
	public int getProductId() {
		return productId;
	}
	public String getNameCn() {
		return nameCn;
	}
	public String getNameEn() {
		return nameEn;
	}
	public int getPriority() {
		return priority;
	}
	public String getKeyWords() {
		return keyWords;
	}
	public String getDesc() {
		return desc;
	}
	public Date getInsertTime() {
		return insertTime;
	}
}
