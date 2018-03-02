package com.revature.bankingappnew;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHandling {

	private static Connection conn;

	public ConnectionHandling() {
		super();
	}
	
	public static Connection getConnection() {
		if(conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:oracle:thin:@jdbcbank.c2gn4hs11dfz.us-east-2.rds.amazonaws.com:1521:ORCL",
						"jcolson419", "myMasterPass1");
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
	            e.printStackTrace();
			}
		}
		return conn;
	}//end getConnection
	
	
}//end class
