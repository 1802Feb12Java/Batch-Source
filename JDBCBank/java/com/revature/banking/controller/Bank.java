package com.revature.banking.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.revature.banking.dao.AccountDAO;
import com.revature.banking.dao.AdminDAO;
import com.revature.banking.dao.ApplicationDAO;
import com.revature.banking.dao.BankDAO;
import com.revature.banking.dao.CustomerDAO;
import com.revature.banking.dao.EmployeeDAO;
import com.revature.banking.dao.UserDAO;
import com.revature.banking.model.Account;
import com.revature.banking.model.Admin;
import com.revature.banking.model.Application;
import com.revature.banking.model.Customer;
import com.revature.banking.model.Employee;
import com.revature.banking.model.User;
import com.revature.banking.utilities.DAOUtilities;

public class Bank implements Serializable {
	private static final long serialVersionUID = -6177103669279664375L;
	private int adminNextId =10000000;
	private int customerNextId =30000000;
	private int employeeNextId=20000000;
	private int accountsNextId =100000000;
	private int applicationNextId=50000000;
	public Bank() {
		Admin admin = new Admin(adminNextId++, "admin", "password", "admin@bank.com");
		DAOUtilities.getAdminDAO().addAdmin(admin);
		System.out.println("New admin username is \"admin\" and new password is \"password\". "
				+ "Please change them ASAP");
	}
	public static Bank loadBank() {
		DAOUtilities.createFolders();
		Bank bank = BankDAO.getBank();
		if(bank == null) {
			bank = new Bank();
			DAOUtilities.getBankDAO().addBank(bank);
		}
		return bank;
	}
	public static Bank loadNewBank() {
		Bank bank = new Bank();
		BankDAO.saveBank(bank);
		return bank;
	}
	public static void saveBank(Bank bank) {
		DAOUtilities.getBankDAO().updateBank(bank);
	}
	public int getNumApplications() {
		return DAOUtilities.getApplicationDAO().getAllApplications().size();
	}
	public int getNumAccounts() {
		return DAOUtilities.getAccountDAO().getAllAccounts().size();
	}
	public int getNumAdmins() {
		return  DAOUtilities.getAdminDAO().getAllAdmins().size();
	}
	public int getNumCustomers() {
		return DAOUtilities.getCustomerDAO().getAllCustomers().size();
	}
	public int getNumEmployees() {
		return DAOUtilities.getEmployeeDAO().getAllEmployees().size();
	}
	public int getNumUsers() {
		return DAOUtilities.getUserDAO().getNumUsers();
	}
	public List<Application> getApplications(){
		return DAOUtilities.getApplicationDAO().getAllApplications();
	}
	public List<Application> getApplicationsByCustomerId(int customerId){
		return DAOUtilities.getApplicationDAO().getApplicationsByCustomerId(customerId);
	}
	public List<Account> getAccounts(){
		return DAOUtilities.getAccountDAO().getAllAccounts();
	}
	public List<Account> getAccountsByCustomerId(int customerId){
		return DAOUtilities.getAccountDAO().getAccountsByCustomerId(customerId);
	}
	public List<Admin> getAdmins(){
		return DAOUtilities.getAdminDAO().getAllAdmins();
	}
	public List<Customer> getCustomers(){
		return DAOUtilities.getCustomerDAO().getAllCustomers();
	}
	public List<Employee> getEmployees(){
		return DAOUtilities.getEmployeeDAO().getAllEmployees();
	}
	public boolean updateUsername(User user) {
		return DAOUtilities.getUserDAO().updateUser(user);		
	}
	public boolean updatePassword(User user) {
		return DAOUtilities.getUserDAO().updateUser(user);	
	}
	public boolean usernameExists(String username) {
		return DAOUtilities.getUserDAO().usernamesExists(username);
	}
	public boolean customerIdExists(int customerId) {
		return DAOUtilities.getCustomerDAO().customerExists(customerId);
	}
	public boolean accountIdExists(int accountId) {
		return DAOUtilities.getAccountDAO().accountExists(accountId);
	}
	public Customer getCustomerById(int customerId) {
		return DAOUtilities.getCustomerDAO().getCustomerByCustomerId(customerId);
	}
	public Account getAccountByAccountId(int accountId) {
		return DAOUtilities.getAccountDAO().getAccountByAccountId(accountId);
	}
	public User getUserByUsername(String username) {
		return DAOUtilities.getUserDAO().getUserByUsername(username);
	}
	@Override
	public String toString() {
		return "Bank [Users=" + this.getNumUsers() + ", Customers=" + this.getNumCustomers() 
				+ ", Employees=" + this.getNumEmployees()	+ ", Administrators=" + this.getNumAdmins() 
				+ ", Accounts=" + this.getNumAccounts() + ", Applications=" + this.getNumApplications() +"]";
	}
	public Customer getCustomer(int customerId) {
		return DAOUtilities.getCustomerDAO().getCustomerByCustomerId(customerId);
	}
	public Employee getEmployee(int employeeId) {
		return DAOUtilities.getEmployeeDAO().getEmployeeByEmployeeId(employeeId);
	}
	public Admin getAdmin(int adminId) {
		return DAOUtilities.getAdminDAO().getAdminByAdminId(adminId);
	}
	public Customer registerCustomer(String username, String password, String name, String address, String phone){
		Customer customer = new Customer(customerNextId++ + getNumCustomers(), username, password, name, address, phone);
		Bank.saveBank(this);
		DAOUtilities.getCustomerDAO().addCustomer(customer);
		return customer;
	}
	public Employee registerEmployee(String username, String password, String email) {
		Employee employee = new Employee(employeeNextId++ + getNumEmployees(), username, password, email);
		Bank.saveBank(this);
		DAOUtilities.getEmployeeDAO().addEmployee(employee);
		return  employee;
	}
	public Admin registerAdmin(String username, String password, String email) {
		Admin admin = new Admin(adminNextId++ + getNumAdmins(), username, password, email);
		Bank.saveBank(this);
		DAOUtilities.getAdminDAO().addAdmin(admin);
		return admin;
	}
	public void approveApplication(Application application, Employee employee) {
		if(application.isOpen()) {
			Account account = new Account(accountsNextId++, application.getPrimaryCustomer(), application.getSecondaryCustomer(), "Open");
			Bank.saveBank(this);
			DAOUtilities.getAccountDAO().addAccount(account);
			application.approve(employee.getUserId(), account.getAccountId());
			DAOUtilities.getApplicationDAO().updateApplication(application);
		}else
			System.out.println("The application has already been finalized.\n\n");
	}
	public void denyApplication(Application application, Employee employee) {
		if(application.isOpen()) {
			application.deny(employee.getUserId());
		}else
			System.out.println("The application has already been finalized.\n\n");
	}	
	public void customerApply(Customer primaryCustomer, Customer secondaryCustomer) {
		Application application = null;
		if (secondaryCustomer !=null) {
			application = new Application(this.applicationNextId++, primaryCustomer.getUserId(), secondaryCustomer.getUserId());
			Bank.saveBank(this);
			DAOUtilities.getApplicationDAO().addApplication(application);
		}
		else {
			application = new Application(this.applicationNextId++, primaryCustomer.getUserId(), 0);
			Bank.saveBank(this);
			DAOUtilities.getApplicationDAO().addApplication(application);
		}
	}
	public void cancelAccount(Admin admin, Account account){
		account.cancel(admin);
		DAOUtilities.getAccountDAO().updateAccount(account);
	}
	public void withdraw(Account account, double amount) {
		if(account.getBalance()>=amount) {
			account.withdraw(amount);
			DAOUtilities.getAccountDAO().updateAccount(account);
		}else
			System.out.println("Account balance is less than $" + amount);
	}
	public boolean transfer(Account fromAccount, Account toAccount, double amount) {
		// TODO Auto-generated method stub
		if(fromAccount.getBalance()>=amount) {
			fromAccount.withdraw(amount);
			toAccount.deposit(amount);
			return true;
		}else
			return false;
	}
	public void deposit(Account toAccount, double amount) {
		toAccount.deposit(amount);
	}
}
