package com.revature.ers.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class ConnFactory {
	private static ConnFactory cf= new ConnFactory();
	protected static Logger log = Logger.getRootLogger();
	public ConnFactory() {
		super();
	}
	
	public static synchronized ConnFactory getInstance() {
		if(cf==null) {
			cf= new ConnFactory();
		}
		return cf;
	}
	
	public Connection getConnection() {
		
		Connection conn = null;
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("database.properties");

		try {
			Properties prop = new Properties();
			prop.load(input);
			Class.forName(prop.getProperty("driver"));
			log.info("Connection to " + prop.getProperty("url") + " succesful.");

			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"), prop.getProperty("pwd"));
		} catch (SQLException e) {
			log.error("SQL exception:  There was an error communicating with the database.");
		} catch (IOException e) {
			log.error("There was a problem with the IO Stream." + e.getMessage());
		} catch (ClassNotFoundException e) {
			log.error("The class was not found." + e.getMessage() );
		}
		
		return conn;
	}
}
