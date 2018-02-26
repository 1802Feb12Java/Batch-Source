package com.revature.BankingAppPt2;

import java.sql.Connection;


import org.apache.log4j.Logger;

public class CustomerDAO extends UserDAO {
	final static Logger logger = Logger.getLogger(Customer.class);
	
	public CustomerDAO(Connection connection) {
		super(connection);
	}

}
