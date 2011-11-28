package com.tianxiaohui.peanut.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;
import javax.sql.PooledConnection;

import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import com.tianxiaohui.peanut.category.Product;

public class DataSourceMgr {
	private DataSourceMgr() {
	}
	
	private static DataSource ds = null;
	private static final Map<Long, Connection> connPool = new HashMap<Long, Connection>();
	
	public static Connection getConnection() throws SQLException {
		synchronized (DataSourceMgr.class) {
			if (null == DataSourceMgr.ds) {
				MysqlDataSource ds = new MysqlDataSource();
				ds.setURL("jdbc:mysql://localhost:3306/peanut_Shell?useUnicode=true&characterEncoding=utf-8");
				ds.setUser("root");
				ds.setPassword("");
				DataSourceMgr.ds = ds;
			} 
		}
		Long threadId = Thread.currentThread().getId();
		if (connPool.containsKey(threadId)) {
			return connPool.get(threadId);
		} else {
			Connection conn = DataSourceMgr.ds.getConnection();
			connPool.put(threadId, conn);
			return conn;
		}
	}
	
	//TODO need a lot
	public static Connection getPooledConnection() throws SQLException {
		synchronized (DataSourceMgr.class) {
			if (null == DataSourceMgr.ds) {
				MysqlConnectionPoolDataSource cpDs = new MysqlConnectionPoolDataSource();
				cpDs.setURL("jdbc:mysql://localhost:3306/peanut_Shell?useUnicode=true&characterEncoding=utf-8");
				cpDs.setUser("root");
				cpDs.setPassword("");
				DataSourceMgr.ds = cpDs;
			}
		}
		PooledConnection pc = ((MysqlConnectionPoolDataSource)DataSourceMgr.ds).getPooledConnection();
		return pc.getConnection();
	}
	public static Connection getOneConnection() throws SQLException {
		if (null == DataSourceMgr.ds) {
			MysqlDataSource ds = new MysqlDataSource();
			ds.setURL("jdbc:mysql://localhost:3306/peanut_Shell?useUnicode=true&characterEncoding=utf-8");
			ds.setUser("root");
			ds.setPassword("");
			DataSourceMgr.ds = ds;
		} 
		return DataSourceMgr.ds.getConnection();
	}
	
	public static Product getProduct(int productId) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Product p = null;
		try {
			conn = DataSourceMgr.getOneConnection();
			ps = conn.prepareStatement("select * from t_d_product x where x.id_ = ?");
			ps.setInt(1, productId);
			rs = ps.executeQuery();
			String nameCn = null;
			String nameEn = null;
			String code = null;
			Date insertTime = null;

			if (rs.next()) {
				nameCn = new String(rs.getString("name_cn_").getBytes("ISO-8859-1"), "GBK");
				nameEn = rs.getString("name_en_");
				code = rs.getString("code_");
				insertTime = rs.getDate("insert_time_");
				p = new Product(productId, nameCn, nameEn, code, insertTime);
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
		
		return p;
	}
	
	public static void main(String[] args) throws Exception {
		/*long start = System.currentTimeMillis();
		for (int i = 0; i < 5000; i ++) {
			//System.out.println(DataSourceMgr.getProduct(1));
			DataSourceMgr.getProduct(1);
		}
		System.out.print("\n");
		System.out.print(System.currentTimeMillis() - start);*/
		
		new Thread(new R()).start();
		new Thread(new R()).start();
		new Thread(new R()).start();
		new Thread(new R()).start();
		new Thread(new R()).start();
	}
}
class R implements Runnable {

	@Override
	public void run() {
		try {
			for (int i = 0; i < 500; i ++) {
				//System.out.println(DataSourceMgr.getProduct(1));
				DataSourceMgr.getProduct(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
