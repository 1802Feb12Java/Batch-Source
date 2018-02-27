package com.revature.bankapp.controller;

import java.sql.SQLException;
import java.util.List;

import com.revature.bankapp.dao.BankAdminDAO;
import com.revature.bankapp.dao.BankDAO;
import com.revature.bankapp.dao.CustomerDAO;
import com.revature.bankapp.model.BankAccount;
import com.revature.bankapp.model.BankAdmin;
import com.revature.bankapp.model.Customer;

public class BankController {
	
	public static void registerCustomer(Customer customer) throws SQLException {
		CustomerDAO.register(customer);
	}
	
	public static void deleteCustomerAccount(BankAccount account) throws SQLException{
		CustomerDAO.deleteAccount(account);
	}
	
	public static List<BankAccount> customerViewAccounts() throws SQLException {
		return CustomerDAO.viewAccounts();
	}
	
	public static BankAccount createCustomerAccount() throws SQLException {
		return CustomerDAO.createAccount();
	}
	
	public static void deposit(BankAccount account) throws SQLException {
		BankDAO.deposit(account);
	}
	
	public static void withdraw(BankAccount account) throws SQLException {
		BankDAO.withdraw(account);
	}
	
	public static List<Customer> adminViewAllCustomers() throws SQLException {
		return BankAdminDAO.viewAllCustomers();
	}
	
	public static List<BankAdmin> adminViewAllAdmins() throws SQLException {
		return BankAdminDAO.viewAllAdmins();
	}
	
	public static BankAdmin adminCreateAdmin(BankAdmin admin) throws SQLException {
		return BankAdminDAO.createBankAdmin(admin);
	}
	
	public static Customer adminCreatCustomer(Customer customer) throws SQLException {
		return BankAdminDAO.createCustomer(customer);
	}
	
	public static void adminUpdateCustUsername(String username, int id) throws SQLException {
		BankAdminDAO.updateCustUsername(username, id);
	}
	
	public static void adminUpdateCustPassword(String pw, int id) throws SQLException {
		BankAdminDAO.updateCustPassword(pw, id);
	}
	
	public static void adminDeleteCustomer(int id) throws SQLException {
		BankAdminDAO.deleteCustomer(id);
	}
}
