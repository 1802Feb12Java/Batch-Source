package com.revature.system;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.utility.ConnectionFactory;

public class Test {

    public static void main(String[] argv) {

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

            connection = ConnectionFactory.getConnection();

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
//            try {
//				Statement statement = connection.createStatement();
//				ResultSet results = statement.executeQuery("SELECT FIRSTNAME FROM EMPLOYEE");
//				
//				while(results.next()) {
//					System.out.println(results.getString(1));
//				}
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
        } else {
            System.out.println("Failed to make connection!");
        }
    }

}