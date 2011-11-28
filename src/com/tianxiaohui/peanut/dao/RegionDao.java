package com.tianxiaohui.peanut.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tianxiaohui.peanut.region.Area;
import com.tianxiaohui.peanut.region.City;
import com.tianxiaohui.peanut.region.Province;

public class RegionDao {
	private RegionDao() {
		// This is utility class
	}
	
	public static List<Area> reloadArea(int cityId) throws SQLException, UnsupportedEncodingException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Area> areaList = null;

		try {
			conn = DataSourceMgr.getConnection();
			ps = conn
					.prepareStatement("select id_, area_ from t_d_area x where x.father_ = (select city_id_ from t_d_city c where c.id_ = ?)");
			ps.setInt(1, cityId);

			rs = ps.executeQuery();

			areaList = new ArrayList<Area>();

			String name = null;
			while (rs.next()) {
				name = new String(rs.getString("area_").getBytes("ISO-8859-1"),
						"GBK");

				areaList.add(new Area(rs.getInt("id_"), RegionDao
						.removeAreaExt(name)));
			}
		} finally {
			if (null != rs) {
				rs.close();
			}
			
			if (null != ps) {
				ps.close();
			}
			
			/*if (null != conn) {
				conn.close();
			}*/
		}

		return areaList;
	}
	
	public static List<Province> reloadProvince() throws SQLException, UnsupportedEncodingException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Province> provinceList = null;

		try {
			conn = DataSourceMgr.getConnection();
			ps = conn.prepareStatement("select id_, short_ from t_d_province");

			rs = ps.executeQuery();
			provinceList = new ArrayList<Province>();

			String name = null;
			while (rs.next()) {
				name = new String(rs.getString("short_").getBytes("ISO-8859-1"), "GBK");

				provinceList.add(new Province(rs.getInt("id_"), RegionDao.removeProvinceExt(name)));
			}
		} finally {
			if (null != rs) {
				rs.close();
			}
			
			if (null != ps) {
				ps.close();
			}
			
			/*if (null != conn) {
				conn.close();
			}*/
		}

		return provinceList;
	}
	
	public static List<City> reloadCity(int provinceId) throws SQLException, UnsupportedEncodingException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<City> cityList = null;

		try {
			conn = DataSourceMgr.getConnection();
			ps = conn.prepareStatement("select id_, city_ from t_d_city x where x.father_ = (select province_id_ from t_d_province p where p.id_ = ?)");
			ps.setInt(1, provinceId);

			rs = ps.executeQuery();

			cityList = new ArrayList<City>();

			String name = null;
			while (rs.next()) {
				name = new String(rs.getString("city_").getBytes("ISO-8859-1"), "GBK");

				cityList.add(new City(rs.getInt("id_"), RegionDao.removeCityExt(name)));
			}
		} finally {
			if (null != rs) {
				rs.close();
			}
			
			if (null != ps) {
				ps.close();
			}
			
			/*if (null != conn) {
				conn.close();
			}*/
		}

		return cityList;
	}
	
	private static String removeAreaExt(String name) {
		if (null == name) {
			return null;
		}
		name = name.replace("　", "");
		int size = name.length();
		if (2 >= size) {
			return name;
		}
		
		if (1 == (size - name.lastIndexOf("县"))) {
			return name.substring(0, size - 1);
		} else if (1 == (size - name.lastIndexOf("区"))) {
			return name.substring(0, size - 1);
		} else if (1 == (size - name.lastIndexOf("市"))) {
			return name.substring(0, size - 1);
		}/* else if (3 == (size - name.lastIndexOf("自治区"))) {
			return name.substring(0, size - 3);
		}*/ 
		
		return name;
	}
	
	private static String removeCityExt(String name) {
		if (null == name) {
			return null;
		}
		name = name.replace("　", "");
		int size = name.length();
		if (2 >= size) {
			return name;
		}
		
		if (1 == (size - name.lastIndexOf("市"))) {
			return name.substring(0, size - 1);
		}
		
		return name;
	}
	
	private static String removeProvinceExt(String name) {
		if (null == name) {
			return null;
		}
		name = name.replace("　", "");
		int size = name.length();
		if (2 >= size) {
			return name;
		}
		
		if (1 == (size - name.lastIndexOf("�?"))) {
			return name.substring(0, size - 1);
		} else if (1 == (size - name.lastIndexOf("市"))) {
			return name.substring(0, size - 1);
		}
		
		return name;
	}
	
	private static void testRemoveAreaExt() {
		System.out.println(RegionDao.removeAreaExt("�?��?县"));
		System.out.println(RegionDao.removeAreaExt("天�?�区"));
		System.out.println(RegionDao.removeAreaExt("市辖区"));
		System.out.println(RegionDao.removeAreaExt("辛集市"));
		System.out.println(RegionDao.removeAreaExt("�?�龙满�?自治区"));
	}
	
	private static void testReloadArea() {
		try {
			List<Area> areaList = RegionDao.reloadArea(163);
			for (Area area : areaList) {
				System.out.println(area.getId() + " : " + area.getName());
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private static void testReloadCity() {
		try {
			List<City> cityList = RegionDao.reloadCity(16);
			for (City city : cityList) {
				System.out.println(city.getId() + " : " + city.getName());
			}
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		/*RegionDao.testRemoveAreaExt();
		System.out.println("范　县".length());
		System.out.println("范　县".replace("　", ""));
		RegionDao.testReloadArea();
		RegionDao.testReloadCity();*/
		
		for (int i = 0; i < 10000; i++) {
			try {
				RegionDao.reloadArea(16);
				RegionDao.reloadCity(15);
				RegionDao.reloadProvince();
				System.out.println(i);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
