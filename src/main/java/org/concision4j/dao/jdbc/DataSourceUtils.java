package org.concision4j.dao.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.concision4j.core.ConfigManager;

import com.mchange.v2.c3p0.DataSources;

public class DataSourceUtils {
	
	public static DataSource dataSources;
	
	static {
		
		try
		{
		  Class.forName(ConfigManager.getJDBCDriver());
		  DataSource ds_unpooled = DataSources.unpooledDataSource(ConfigManager.getJDBCUrl(),ConfigManager.getJDBCUsername(),ConfigManager.getJDBCPassword()); 
		  System.out.println(ConfigManager.getJDBCDriver());
		  System.out.println(ConfigManager.getJDBCUrl());
		  System.out.println(ConfigManager.getJDBCUsername());
		  System.out.println(ConfigManager.getJDBCPassword());
		  Map<String, Object > overrides = new HashMap<String, Object>();
		  overrides.put("maxStatements", "200");         //Stringified property values work
		  overrides.put("maxPoolSize", new Integer(50)); //"boxed primitives" also work
		  dataSources = DataSources.pooledDataSource( ds_unpooled ,overrides );
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				DataSources.destroy( dataSources );
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
			return dataSources.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Error get connection,message: "+e.getMessage());
		}
		return null;
	}
	
	
	public static void releaseConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Error close connection,message: "+e.getMessage());
			}
		}
	}
}
