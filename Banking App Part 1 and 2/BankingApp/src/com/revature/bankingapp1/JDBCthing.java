package com.revature.bankingapp1;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCthing {

	public static void main(String[] args) {

	        System.out.println("-------- Oracle JDBC Connection Testing ------");

	        try {

	            Class.forName("oracle.jdbc.driver.OracleDriver");

	        } catch (ClassNotFoundException e) {

	            System.out.println("Where is your Oracle JDBC Driver?");
	            e.printStackTrace();
	            return;

	        }

	        System.out.println("Oracle JDBC Driver Registered!");

	        Connection connection = null;

	        try {

	            connection = DriverManager.getConnection(
	                    "jdbc:oracle:thin:@feb12usf.c2gn4hs11dfz.us-east-2.rds.amazonaws.com:1521:ORCL", "jcolson419", "myMasterPass1");

	        } catch (SQLException e) {

	            System.out.println("Connection Failed! Check output console");
	            e.printStackTrace();
	            return;

	        }

	        if (connection != null) {
	            System.out.println("You made it, take control your database now!");
	        } else {
	            System.out.println("Failed to make connection!");
	        }

	}

}
