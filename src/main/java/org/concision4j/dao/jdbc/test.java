package org.concision4j.dao.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.concision4j.ConfigManager;

import cn.jadepool.sql.Jade;

public class test {
	static {
		try {
			Class.forName(ConfigManager.getJDBCDriver());
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(ConfigManager.getJDBCUrl(),ConfigManager.getJDBCUsername(),ConfigManager.getJDBCPassword());
	}
	
	
	
}
