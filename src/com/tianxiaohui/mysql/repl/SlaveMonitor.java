package com.tianxiaohui.mysql.repl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SlaveMonitor {
	public static Connection getOneConnection() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_eric_test", "eric", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
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
				return true;
			}
			
		} finally {
			if (null != ps) {
				ps.close();
			}
			if (null != conn) {
				conn.close();
			}
		}
		
		return false;
	}
	
	public static void main(String[] args) {

	}

}
