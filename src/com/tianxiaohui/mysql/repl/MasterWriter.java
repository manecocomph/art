package com.tianxiaohui.mysql.repl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MasterWriter {

	public static Connection getOneConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://sjc-vts-270:3307/db_eric_test", "eric", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static void writeToMaster() throws SQLException {
		Connection conn = null;
		PreparedStatement ps = null;
		
		try {
			conn = MasterWriter.getOneConnection();
			ps = conn.prepareStatement("insert into t_test(id_, name_) values(1, 'eric')");
			System.out.println("Start to write .." + System.currentTimeMillis());
			ps.execute();
			System.out.println("after write .." + System.currentTimeMillis());
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
	}
	
	public static boolean get() throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			conn = MasterWriter.getOneConnection();
			ps = conn.prepareStatement("select * from t_test where id_ = 1");
			rs = ps.executeQuery();
			
			if (rs.next()) {
				System.out.println("************* I get ..." + System.currentTimeMillis());
				return false;
			}
			
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		boolean a = false;
		boolean b = true;
		System.out.println("----");
		MasterWriter.writeToMaster();
		
		while (!a && b) {
			a = MasterWriter.get();
			
			if (!a) {
				a = MasterWriter.get();
			}
			
			/*if (!a) {
				a = SlaveMonitor.get();
			}*/
		}
	}

}
