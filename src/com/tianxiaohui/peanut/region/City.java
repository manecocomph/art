package com.tianxiaohui.peanut.region;

import java.util.List;

import com.tianxiaohui.peanut.dao.RegionDao;

public class City {
	public City(int id, String name) {
		this.id = id;
		this.name = name;
	}

	private int id;
	private String name;
	private List<Area> areas;

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<Area> getAreas() throws Exception {
		if (null == this.areas || 0 == this.areas.size()) {
			this.areas = RegionDao.reloadArea(this.id);
		}

		return areas;
	}
}
