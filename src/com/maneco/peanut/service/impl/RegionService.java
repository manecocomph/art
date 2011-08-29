package com.maneco.peanut.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;

import com.maneco.peanut.dao.RegionDao;
import com.maneco.peanut.region.Area;
import com.maneco.peanut.region.City;
import com.maneco.peanut.region.Province;
import com.maneco.peanut.service.IRegionService;

public class RegionService implements IRegionService {
private static final Logger log = Logger.getLogger(RegionService.class);
	
	private static JCS cacheProvince = null;
	private static JCS cacheCity = null;
	private static JCS cacheArea = null;

	static {
		try {
			cacheProvince = JCS.getInstance("region_province");
			cacheCity = JCS.getInstance("region_city");
			cacheArea = JCS.getInstance("region_area");
		} catch (CacheException e) {
			RegionService.log.error("Cache JCS region init fail", e);
		}
	}
	
	public String getCityDropdownOption(int provinceId) {
		Object obj = RegionService.cacheCity.get(Integer.valueOf(provinceId));
		
		if (null != obj) {
			return obj.toString();
		} else {
			StringBuffer sb = null;
			
			try {
				List<City> cityList = RegionDao.reloadCity(provinceId);
				sb = new StringBuffer();

				for (City c : cityList) {
					sb.append("<option value='" + c.getId() + "'>" + c.getName() + "</option>");
				}
			} catch (SQLException sqle) {
				RegionService.log.error("SQL exception when load city", sqle);
				sqle.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				RegionService.log.error("Encoding exception when tranfer encoding", e);
				e.printStackTrace();
			}
			
			String str = sb.toString();
			try {
				RegionService.cacheCity.put(Integer.valueOf(provinceId), str);
			} catch (CacheException e) {
				RegionService.log.error("Cache put exception", e);
				e.printStackTrace();
			}
			return str;
		}
	}
	
	public String getAreaDropdownOption(int cityId) {
		Object obj = RegionService.cacheArea.get(Integer.valueOf(cityId));
		
		if (null != obj) {
			return obj.toString();
		} else {
			StringBuffer sb = null;
			
			try {
				List<Area> areaList = RegionDao.reloadArea(cityId);
				sb = new StringBuffer();

				for (Area a : areaList) {
					sb.append("<option value='" + a.getId() + "'>" + a.getName() + "</option>");
				}
			} catch (SQLException sqle) {
				RegionService.log.error("SQL exception when load Area", sqle);
				sqle.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				RegionService.log.error("Encoding exception when tranfer encoding", e);
				e.printStackTrace();
			}
			
			String str = sb.toString();
			try {
				RegionService.cacheArea.put(Integer.valueOf(cityId), str);
			} catch (CacheException e) {
				RegionService.log.error("Cache put exception", e);
				e.printStackTrace();
			}
			return str;
		}
	}
	
	public String getProvinceDropdownOption(int countryId) {
		Object obj = RegionService.cacheProvince.get(Integer.valueOf(countryId));
		
		if (null != obj) {
			return obj.toString();
		} else {
			StringBuffer sb = null;
			
			try {
				List<Province> provinceList = RegionDao.reloadProvince();
				sb = new StringBuffer();

				for (Province p : provinceList) {
					sb.append("<option value='" + p.getId() + "'>" + p.getName() + "</option>");
				}
			} catch (SQLException sqle) {
				RegionService.log.error("SQL exception when load province", sqle);
				sqle.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				RegionService.log.error("Encoding exception when tranfer encoding", e);
				e.printStackTrace();
			}
			
			String str = sb.toString();
			try {
				RegionService.cacheProvince.put(Integer.valueOf(countryId), str);
			} catch (CacheException e) {
				RegionService.log.error("Cache put exception", e);
				e.printStackTrace();
			}
			return str;
		}
	}

	@Override
	public String who() {
		return "Eric";
	}

	@Override
	public String why(String name) {
		return "your Name:" + name;
	}

	@Override
	public String whyNot() {
		return "Jiakou";
	}

	@Override
	public void giveMe(String name) {
		System.out.println("I am doing something");
	}

	@Override
	public int what(int age) {
		return age * 100;
	}

	@Override
	public int when(int time) {
		return 1000 * time;
	}

	@Override
	public String how(int height) {
		return "you invoke how(int) with height: " + height;
	}
}
