package com.revature.expensereimbursement;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.expensereimbursement.dao.ERSReimbursementDAO;


public class DAOUtilities {
	private static DAOUtilities utils;;
	private static Properties properties;
	private static String CONNECTION_USERNAME;
	private static String CONNECTION_PASSWORD;
	private static String URL;
	//private static Connection connection;
	static{
		utils=new DAOUtilities();
		properties = new Properties();
		try {
			InputStream db = utils.getClass().getClassLoader().getResourceAsStream("database.properties");
			properties.load(db);
			 URL = properties.getProperty("url");
			 CONNECTION_USERNAME = properties.getProperty("user");
			 CONNECTION_PASSWORD = properties.getProperty("pass");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public DAOUtilities() {
		super();
		
	}
	public static synchronized Connection getConnection() throws SQLException {
		Connection connection=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not register driver!");
			e.printStackTrace();
		}

		return connection;
	}
	public static ERSReimbursementDAO getERSReimbursementDAO() {
		return new ERSReimbursementDAO();
	}
	public static void closeResources(Connection connection, PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
}
