package com.revature.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	private static Connection conn;
	
	public ConnectionFactory() {
		super();
	}
	
	public static Connection getConnection() {
		
		if(conn == null) {
			try {
				conn = DriverManager.getConnection(
				        "jdbc:oracle:thin:@jdbcbank.cpcjj0oba8us.us-east-2.rds.amazonaws.com:1521:orcl", "sgm4789", "Howareyou!");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Connection Failed");
			}
		}
		
		return conn;
	}

	
}
