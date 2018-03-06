package com.revature.dao.implementation;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class DatabaseConnection {
	public static Connection conn = null;
	static Logger log = Logger.getLogger(DatabaseConnection.class.getName());

	/**
	 * Static block that initiates connection to database before instantiation
	 */
	static {
		try {
			log.debug("INITIATING CONNECTION TO DATABASE");
			Properties prop = new Properties();
			prop.load(new FileReader("database.properties"));
			Class.forName(prop.getProperty("driver"));
			conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"),
					prop.getProperty("pwd"));
			log.debug("URL<" + prop.getProperty("url") + "> USERNAME<" + prop.getProperty("usr") + "> PASSWORD<"
					+ prop.getProperty("pwd") + ">");
			log.debug("DATABASE CONNECTION SUCCESSFUL");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e);
		} catch (FileNotFoundException e) {
			System.out.println("Database properties file not found");
			log.error(e);
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found");
			log.error(e);
		} catch (IOException e) {
			System.out.println("Unable to read from properties file");
			log.error(e);
		}
	}

}
