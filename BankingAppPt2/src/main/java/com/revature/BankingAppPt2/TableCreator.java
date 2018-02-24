package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TableCreator {

	public static void main(String[] args) {
		createUsersTable();
		createAccountsTable();
		
	}
	
	private static void createUsersTable() {
	
		Connection dbConnection = null;
		Statement statement = null;

		String createTableSQL = "CREATE TABLE USERS("
				+ "USER_ID NUMBER(5) NOT NULL, "
				+ "USERNAME VARCHAR(20) NOT NULL, "
				+ "CREATED_BY VARCHAR(20) NOT NULL, "
				+ "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
				+ ")";

		try {
			dbConnection = null;
			statement = dbConnection.createStatement();

			System.out.println(createTableSQL);
                        // execute the SQL stetement
			statement.execute(createTableSQL);

			System.out.println("Table \"dbuser\" is created!");

		} catch (SQLException e) {

			System.out.println(e.getMessage());

		} /*finally {

			if (statement != null) {
				statement.close();
			}

			if (dbConnection != null) {
				dbConnection.close();
			}
		}*/
	}
	
	private static void createAccountsTable() {
		
	}

}
