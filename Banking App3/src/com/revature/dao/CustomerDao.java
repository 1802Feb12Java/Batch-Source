package com.revature.dao;

import java.util.List;

import com.revature.pt1.Customers;

public interface CustomerDao {
			
		// deposit money
		public void depositCus(String uername, double balance);
		
		// withdraw money
		public void withdrawCus(String username, double balance);
		
		// close account
		public void closeCus(String username);
		
		//Retrieve all flash cards
		public List<Customers>retrieveAllCus();

		// Move from application table to customers table 
		public void moveFromApp(String name);

		// transfer money between users
		public void transferCus(String cname, String tran, double tmoney);

		// check if user exists in Customers table
		boolean checkUser(String cname, String cps);
		
		// view account info and balance
		public void infoCus(String name);
}
