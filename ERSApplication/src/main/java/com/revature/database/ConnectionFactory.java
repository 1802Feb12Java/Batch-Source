package com.revature.database;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	private static ConnectionFactory cf = null;

	private ConnectionFactory() {
		super();

	}

	public static synchronized ConnectionFactory getInstance() {

		if (cf == null) {

			cf = new ConnectionFactory();

		}

		return cf;

	}

	public Connection getConnection() {

		Connection conn = null;

		Properties prop = new Properties();
		try {
			prop.load(new FileReader("database.properties"));
			try {
				Class.forName(prop.getProperty("driver"));
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			try {
				conn = DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("usr"),
						prop.getProperty("pwd"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return conn;

	}
}
