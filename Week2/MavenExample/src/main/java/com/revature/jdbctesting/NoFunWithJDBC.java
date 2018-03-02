package com.revature.jdbctesting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class NoFunWithJDBC {

	static String jdbcUrl = "jdbc:oracle:thin:@feb12usf.c2doxd3zyy2m.us-east-2.rds.amazonaws.com:1521:ORCL";
	static String username = "josephjustn";
	static String password = "c|j5&8'5UnC[";
	static String sql = "SELECT * FROM EMPLOYEE";

	public static void main(String[] args) {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Oracle JDBC Driver not found");
			e.printStackTrace();
			return;
		}
		System.out.println("Oracle JDBC Driver Registered!\n");

		try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);) {

			int row = 0;
			while (rs.next()) {
				System.out.println("----------Row " + (++row) + " ------------");
				System.out.println("ID:\t" + rs.getString(1));
				System.out.println("NAME:\t" + rs.getString(3) + " " + rs.getString(2));
				System.out.println("TITLE:\t" + rs.getString(4));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}