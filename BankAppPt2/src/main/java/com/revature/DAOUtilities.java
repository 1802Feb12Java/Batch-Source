package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOUtilities {
	
	private static final String CONNECTION_USERNAME = "mmiocic";
	private static final String CONNECTION_PASSWORD = "waterpolo4";
	private static final String URL = "jdbc:oracle:thin:@feb12usf.cehr5rryxkuu.us-east-2.rds.amazonaws.com:1521:ORCL";

	private static UserDAOImpl udaoi;
	private static SuperuserDAOImpl sdaoi;
	
	private static Connection connection;
	
	public static synchronized UserDAO getUserDAO() {
		if(udaoi==null) {
			udaoi = new UserDAOImpl();
		}
		return udaoi;
	}
	
	public static synchronized SuperuserDAO getSuperDAO() {
		if(sdaoi==null) {
			sdaoi = new SuperuserDAOImpl();
		}
		return sdaoi;
	}
	
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
