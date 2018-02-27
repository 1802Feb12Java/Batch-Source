package com.revature.banking.jdbc.utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.revature.banking.jdbc.dao.AccountDAO;
import com.revature.banking.jdbc.dao.AdminDAO;
import com.revature.banking.jdbc.dao.ApplicationDAO;
import com.revature.banking.jdbc.dao.CustomerDAO;
import com.revature.banking.jdbc.dao.EmployeeDAO;
import com.revature.banking.jdbc.dao.UserDAO;



public class DAOUtilities {
	private static Properties properties=new Properties();
	private static String CONNECTION_USERNAME;
	private static String CONNECTION_PASSWORD;
	private static String URL;
	private static Connection connection;
	static{
		properties = new Properties();
		try {
			properties.load(new FileReader("src"+File.separator+"main"+File.separator+"resources"+File.separator+"database.properties"));
			 URL = properties.getProperty("url");
			 CONNECTION_USERNAME = properties.getProperty("user");
			 CONNECTION_PASSWORD = properties.getProperty("pass");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public DAOUtilities() {
		super();
		
	}
	public static synchronized Connection getConnection() throws SQLException {
		if(properties==null) {
			
		}
		if (connection == null) {
			try {
				Class.forName("oracle.jdbc.OracleDriver");
			} catch (ClassNotFoundException e) {
				System.out.println("Could not register driver!");
				e.printStackTrace();
			}
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		
		//If connection was closed then retrieve a new connection
		if (connection.isClosed()){
			//System.out.println("Opening new connection...");
			connection = DriverManager.getConnection(URL, CONNECTION_USERNAME, CONNECTION_PASSWORD);
		}
		return connection;
	}
	public static UserDAO getUserDAO() {
		return new UserDAO();
	}
	public static AdminDAO getAdminDAO() {
		return new AdminDAO();
	}
	public static EmployeeDAO getEmployeeDAO() {
		return new EmployeeDAO();
	}
	public static CustomerDAO getCustomerDAO() {
		return new CustomerDAO();
	}
	public static ApplicationDAO getApplicationDAO() {
		return new ApplicationDAO();
	}
	public static AccountDAO getAccountDAO() {
		return new AccountDAO();
	}
	public static ResultSet objectRead(Connection connection, String query){
		PreparedStatement stmt=null;
		ResultSet results =null;
		try {
			stmt = connection.prepareStatement(query);
			results =  stmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closeResources(connection, stmt);
		}
		return results;
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
