package com.revature.zoo.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.revature.zoo.dao.*;


public class DAOUtilities {

	private static final String CONNECTION_USERNAME = "VangJ";
	private static final String CONNECTION_PASSWORD = "honeybadger";
	private static final String URL = "jdbc:oracle:thin:@feb12usf.cdwduyxmqnpi.us-east-2.rds.amazonaws.com:1521:ORCL";

	private static AnimalDAOImpl aDaoI;
	
	private static Connection connection;
	
	static synchronized Connection getConnection() throws SQLException {
		if (connection == null) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			try {
	            connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
	        } catch (SQLException e) {
	            System.out.println("Connection Failed! Check output console");
	            e.printStackTrace();
	        }
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			System.out.println("getting new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
}