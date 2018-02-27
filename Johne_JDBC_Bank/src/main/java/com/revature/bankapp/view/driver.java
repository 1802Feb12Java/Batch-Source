package com.revature.bankapp.view;

import java.sql.SQLException;

import com.revature.bankapp.controller.BankController;
import com.revature.bankapp.model.BankAccount;
import com.revature.bankapp.model.BankAdmin;
import com.revature.bankapp.model.Customer;

public class driver {
	public static void main(String[] args) throws SQLException {
		Customer user = new Customer();
		user.setCustomerID(1002);
		user.setCustUsername("user");
		user.setCustPW("user");
		
		BankAccount account = new BankAccount();
		BankAdmin admin = new BankAdmin();
		
		//BankController.registerCustomer(user);
		//BankController.adminDeleteCustomer(0);
		System.out.println(BankController.adminViewAllCustomers().toString());
	}
}
