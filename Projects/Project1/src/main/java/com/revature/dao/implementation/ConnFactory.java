package com.revature.dao.implementation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConnFactory {
	static Logger log = Logger.getLogger(ConnFactory.class.getName());
	private static ConnFactory cf = null;

	private ConnFactory() {
		super();
	}

	public static synchronized ConnFactory getInstance() {
		if (cf == null) {
			cf = new ConnFactory();
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
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"),
					prop.getProperty("pwd"));
			log.debug("Connection to database created");
		} catch (SQLException e) {
			log.error("SQL Exception");
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			log.error("File not found");
			e.printStackTrace();
		} catch (IOException e) {
			log.error("Can't read file");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			log.error("Missing driver");
			e.printStackTrace();
		}
		return conn;
	}

}