package com.talanian.util;

import oracle.jdbc.driver.OracleDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import org.apache.log4j.Logger;

import com.talanian.dao.SystemDAOImpl;

public class ConnectionUtil {
	static Logger log = Logger.getLogger(ConnectionUtil.class.getName());
	public static Connection getConnection() throws SQLException{
		String url = "jdbc:oracle:thin:@mydatabase.ckcbgjmfavpl.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "talanianj";
		String password = "password";
		OracleDriver driver = new OracleDriver();
		DriverManager.registerDriver(driver);
		log.debug("connection success");
		return DriverManager.getConnection(url, username, password);
	}
}