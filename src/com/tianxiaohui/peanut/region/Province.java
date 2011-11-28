package com.tianxiaohui.peanut.region;

import java.util.List;

import com.tianxiaohui.peanut.dao.RegionDao;

public class Province {
	public Province(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	private int id;
	private String name;
	private List<City> cities;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<City> getCities() throws Exception {
		if (null == this.cities) {
			this.cities = RegionDao.reloadCity(id);
		}
		return cities;
	}
}
