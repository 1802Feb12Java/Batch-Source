package com.revature.factory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory cf = null;
	
	private ConnectionFactory(){
		super();
	}
	
	public static synchronized ConnectionFactory getInstance(){
		if(cf == null){
			cf = new ConnectionFactory();
		}
		return cf;
	}
	
	public Connection getConnection(){
		Connection conn = null;

		try {
			Properties props = new Properties();
			
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("database.properties");
			props.load(inputStream);
			inputStream.close();

			String driver = props.getProperty("driver");
			if (driver != null) {
			    Class.forName(driver) ;
			}

			String url = props.getProperty("url");
			String dbusername = props.getProperty("user");
			String password = props.getProperty("pass");
			conn = DriverManager.getConnection(url, dbusername, password);
		} catch (Exception e1) {
			System.out.println("Connection failed.");
		}

		return conn;
	}	
}