package com.tca.util;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	private static final String DB_USER;
	private static final String DB_PWD;
	private static final String DB_URL;
	
	static {
		Properties prop = new Properties();
		
		try(FileInputStream fis = new FileInputStream("config.properties"))
		{
			//load configurations from config.properties file
			prop.load(fis);
			
			//Register Driver
			Class.forName(prop.getProperty("DB_DRIVER"));
			
			//get configuration values
			DB_USER = prop.getProperty("DB_USER");
			DB_PWD = prop.getProperty("DB_PWD");
			DB_URL = prop.getProperty("DB_URL");
		}
		catch(ClassNotFoundException ne) {
			throw new RuntimeException("Failed to register Driver.", ne);
		}
		catch(Exception e) {
			throw new RuntimeException("Failed to load configurations.", e);
		}
		finally {
			prop.clear();
		}
	}
	
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
	}
	
}
