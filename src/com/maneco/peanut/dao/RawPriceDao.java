package com.maneco.peanut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.maneco.peanut.price.RawPrice;

public class RawPriceDao {
	private RawPriceDao() {
	}
	
	public static int saveFileInfo(String fileName, Date priceDate) throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		int id = -1;
		
		try {
			conn = DataSourceMgr.getConnection();
			ps = conn.prepareStatement("insert into t_rp_file(file_name_, price_date_) values(?, ?)");
			ps.setString(1, fileName);
			ps.setDate(2, new java.sql.Date(priceDate.getTime()));
			ps.execute();

			if (ps.getUpdateCount() > 0) {
				ps2 = conn.prepareStatement("SELECT LAST_INSERT_ID()");
				rs = ps2.executeQuery();
				rs.next();
				id = rs.getInt(1);
			}
		} finally {
			if (null != rs) {
				rs.close();
			}
			if (null != ps2) {
				ps2.close();
			}
			if (null != ps) {
				ps.close();
			}
			/*if (null != conn) {
				conn.close();
			}*/
		}
		return id;
	}
	
	public static int saveRawPrice(RawPrice rp) throws SQLException {
		if (null == rp || 0 >= rp.getPrice1()) {
			return -1;
		}
		System.out.println(rp.toString());
		
		Connection conn = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs = null;
		int id = -1;
		
		try {
			conn = DataSourceMgr.getConnection();
			ps = conn.prepareStatement("insert into t_rp_detail(file_id_, province_id_, city_id_, area_id_, price1_, species1_, price2_, species2_, price3_, species3_, price4_, species4_, price5_, species5_) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setInt(1, rp.getDateId());
			ps.setInt(2, rp.getProvinceId());
			ps.setInt(3, rp.getCityId());
			ps.setInt(4, rp.getAreaId());
			ps.setFloat(5, rp.getPrice1());
			ps.setInt(6, rp.getPrice1Species());
			ps.setFloat(7, rp.getPrice2());
			ps.setInt(8, rp.getPrice2Species());
			ps.setFloat(9, rp.getPrice3());
			ps.setInt(10, rp.getPrice3Species());
			ps.setFloat(11, rp.getPrice4());
			ps.setInt(12, rp.getPrice4Species());
			ps.setFloat(13, rp.getPrice5());
			ps.setInt(14, rp.getPrice5Species());
			ps.execute();

			if (ps.getUpdateCount() > 0) {
				ps2 = conn.prepareStatement("SELECT LAST_INSERT_ID()");
				rs = ps2.executeQuery();
				rs.next();
				id = rs.getInt(1);
			}
		} finally {
			if (null != rs) {
				rs.close();
			}
			if (null != ps2) {
				ps2.close();
			}
			if (null != ps) {
				ps.close();
			}
			/*if (null != conn) {
				conn.close();
			}*/
		}
		
		return id;
	}
}
