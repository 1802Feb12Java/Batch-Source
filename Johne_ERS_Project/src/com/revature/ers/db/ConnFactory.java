package com.revature.ers.db;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnFactory {

	private static ConnFactory cf = null;
	public static final String url = "jdbc:oracle:thin:@ers.cdwduyxmqnpi.us-east-2.rds.amazonaws.com:1521:ORCL";
	public static final String user = "ERS_admin";
	public static final String pw = "ersadmin";
	
	
	private ConnFactory(){
		super();
	}
	
	/**
	 * if there is no connection factory, create a new connection
	 * otherwise, use the connection factory that already exist
	 * @return
	 */
	public static synchronized ConnFactory getInstance(){
		if(cf == null){
			cf = new ConnFactory();	
		}
		return cf;
	}
	
	public Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}	
		return conn;
	}
	
}

