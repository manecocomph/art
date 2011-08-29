package com.maneco.peanut;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.maneco.peanut.dao.DataSourceMgr;
import com.maneco.peanut.dao.RegionDao;
import com.maneco.peanut.region.City;

public class Main {

	public static BufferedWriter bw = null;
	
	static {
		try {
			Main.bw = new BufferedWriter(new FileWriter("C:\\work\\projects\\peanutShell\\test\\species.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void startWork() {
		File f = new File("C:\\work\\ruby\\peanut2");
		try {
			FileDataExtractor food21 = FileDataExtractor.getInstance(f, "21Food", 1);
			FileDataExtractor foodqs = FileDataExtractor.getInstance(f, "foodqs", 1);
			FileDataExtractor chinaGrain = FileDataExtractor.getInstance(f, "chinaGrain", 1);
			FileDataExtractor jinNong = FileDataExtractor.getInstance(f, "jinNong", 1);
			FileDataExtractor ocn = FileDataExtractor.getInstance(f, "ocn", 1);
			
			new Thread(food21).start();
			new Thread(foodqs).start();
			new Thread(chinaGrain).start();
			new Thread(jinNong).start();
			new Thread(ocn).start();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Main.startWork();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	public static void changeEncode() {
		try {
			System.out.println("这是中国");
			BufferedReader br = new BufferedReader(new FileReader("C:\\work\\projects\\peanutShell\\db_script\\Province_city_area.sql"));
			
			String line = br.readLine();
			while (null != line) {
				System.out.println(new String(line.getBytes("utf8"), "gbk"));
				line = br.readLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createProvinceSql() throws SQLException, UnsupportedEncodingException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = DataSourceMgr.getConnection();
			ps = conn.prepareStatement("select * from province");

			rs = ps.executeQuery();
			String name = null;
			while (rs.next()) {
				name = new String(rs.getString("province").getBytes("ISO-8859-1"), "GBK");
				System.out.println("INSERT INTO `t_d_province` VALUES (" + rs.getInt("id") + ",'" + rs.getString("provinceid") + "','" + name + "', '" + name.substring(0, 2) + "');");
			}
		} finally {
			if (null != rs) {
				rs.close();
			}
			
			if (null != ps) {
				ps.close();
			}
			
			if (null != conn) {
				conn.close();
			}
		}
	}
}
