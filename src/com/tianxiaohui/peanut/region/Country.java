package com.tianxiaohui.peanut.region;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tianxiaohui.peanut.dao.RegionDao;

public class Country {
	private static final Map<String, Country> countries = new HashMap<String, Country>();
	private Country() {
		// immutable for this country
	}
	
	public static Country newCountry(String name) {
		if (Country.countries.containsKey(name)) {
			return Country.countries.get(name);
		} else {
			Country c = new Country();
			c.name = name;
			Country.countries.put(name, c);
			return c;
		}
	}
	
	public String name;
	private List<Province> provinces;
	
	
	
	public String getName() {
		return name;
	}

	public List<Province> getProvinces() throws Exception {
		if (null == provinces) {
			this.provinces = RegionDao.reloadProvince();
		}
		return provinces;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
