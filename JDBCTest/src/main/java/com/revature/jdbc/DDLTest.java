package com.revature.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DDLTest {
	static String createTableSQL = "CREATE TABLE DBUSER(" + "USER_ID NUMBER(5) NOT NULL, "
			+ "USERNAME VARCHAR(20) NOT NULL, " + "CREATED_BY VARCHAR(20) NOT NULL, " + "CREATED_DATE DATE NOT NULL, "
			+ "PRIMARY KEY (USER_ID) " + ")";
	static String CREATE_TRANSACTION_TABLE = "CREATE TABLE " + CreateBank.TRANSACTION_TABLE_NAME + "("
			+ "USER_ID VARCHAR(36) NOT NULL," + "ACCOUNT_ID VARCHAR(20) NOT NULL,"
			+ "PRIMARY KEY (USER_ID, ACCOUNT_ID) " + ")";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		try {

			connection = DriverManager.getConnection(
					"jdbc:oracle:thin:@bankingappinstance.crxvyqrpskhk.us-east-2.rds.amazonaws.com:1521:ORCL", "jrufo",
					"myDBPass...");

			statement = connection.createStatement();
			statement.executeQuery(CreateBank.CREATE_CREDENTIALS_TABLE);
			statement.executeQuery(CreateBank.CREATE_BANK_ACCOUNT_TABLE);
			statement.executeQuery(CreateBank.CREATE_SUPER_USER_TABLE);
			statement.executeQuery(CreateBank.CREATE_USER_BANK_ACCOUNT_TABLE);
			statement.executeQuery(CreateBank.CREATE_USER_TABLE);
			statement.executeQuery(CreateBank.CREATE_TRANSACTION_TABLE);

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
