package com.maneco.peanut.category;

import java.util.Date;
import java.util.List;

import com.maneco.peanut.dao.CategoryDao;

public class Product {
	public Product(int id) {
		this.id = id;
	}
	public Product(int id, String nameCn, String code) {
		this.id = id;
		this.nameCn = nameCn;
		this.code = code;
	}
	
	public Product(int id, String nameCn, String nameEn, String code,
			Date insertTime) {
		this.id = id;
		this.nameCn = nameCn;
		this.nameEn = nameEn;
		this.code = code;
		this.insertTime = insertTime;
	}

	private int id;
	private String nameCn;
	private String nameEn;
	private String code;
	private Date insertTime;
	private List<Species> species;
	
	public List<Species> getSpecies() throws Exception {
		if (null == this.species) {
			this.species = CategoryDao.loadSpecies(this.id);
		}
		return species;
	}
	public int getId() {
		return id;
	}
	public String getNameCn() {
		return nameCn;
	}
	public String getNameEn() {
		return nameEn;
	}
	public String getCode() {
		return code;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	
	
}
