package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DatabaseConnection {
	public static Connection getDatabaseConnection() {
		Connection dbConnection = null;
		try {

			dbConnection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@feb12usf.cxboj8vmeqoq.us-east-2.rds.amazonaws.com:1521:ORCL",
                    Credentials.userName, Credentials.password);			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		return dbConnection;
	}
}
