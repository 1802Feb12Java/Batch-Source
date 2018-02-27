package com.revature.banking.jdbc.controller;

import java.io.Serializable;
import java.util.List;

import org.apache.log4j.Logger;

import com.revature.banking.jdbc.model.Account;
import com.revature.banking.jdbc.model.Admin;
import com.revature.banking.jdbc.model.Application;
import com.revature.banking.jdbc.model.Customer;
import com.revature.banking.jdbc.model.Employee;
import com.revature.banking.jdbc.model.User;
import com.revature.banking.jdbc.utilities.DAOUtilities;

public class Bank implements Serializable {
	private static final long serialVersionUID = -6177103669279664375L;
	private static Logger log = Logger.getLogger(Bank.class.getName());
//	private int adminNextId =10000000;
//	private int customerNextId =30000000;
//	private int employeeNextId=20000000;
//	private int accountsNextId =100000000;
//	private int applicationNextId=50000000;
	public Bank() {
		List<Admin> admins = DAOUtilities.getAdminDAO().getAllAdmins();
		if(admins.size()==0) {
			Admin admin = new Admin(0, "admin", "password", "admin@bank.com");
			DAOUtilities.getAdminDAO().addAdmin(admin);
			System.out.println("New admin username is \"admin\" and new password is \"password\". "
					+ "Please change them ASAP");
		}
	}
	public static Bank loadBank() {
		Bank bank = new Bank();
		log.info("Starting new bank");
		return bank;
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
	public List<Application> getAllApplications(){
		return DAOUtilities.getApplicationDAO().getAllApplications();
	}
	public List<Application> getApplicationsByCustomerId(int customerId){
		return DAOUtilities.getApplicationDAO().getApplicationsByCustomerId(customerId);
	}
	public List<Account> getAllAccounts(){
		return DAOUtilities.getAccountDAO().getAllAccounts();
	}
	public List<Account> getAllOpenAccounts(){
		return DAOUtilities.getAccountDAO().getAllOpenAccounts();
	}
	public List<Account> getAccountsByCustomerId(int customerId){
		return DAOUtilities.getAccountDAO().getAccountsByCustomerId(customerId);
	}
	public List<Admin> getAllAdmins(){
		return DAOUtilities.getAdminDAO().getAllAdmins();
	}
	public List<Customer> getAllCustomers(){
		return DAOUtilities.getCustomerDAO().getAllCustomers();
	}
	public List<Employee> getAllEmployees(){
		return DAOUtilities.getEmployeeDAO().getAllEmployees();
	}
	public boolean updateUsername(User user) {
		log.info("Updated username " + user.toString());
		return DAOUtilities.getUserDAO().updateUser(user);		
	}
	public boolean updatePassword(User user) {
		log.info("Updated password " + user.toString());
		return DAOUtilities.getUserDAO().updateUser(user);	
	}
	public boolean usernameExists(String username) {
		return DAOUtilities.getUserDAO().usernamesExists(username);
	}
	public boolean customerIdExists(int customerId) {
		return DAOUtilities.getCustomerDAO().getCustomerByCustomerId(customerId)!= null;
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
	public Customer registerCustomer(String username, String password, String email, String firstName, String lastName){
		Customer customer = new Customer(0 + getNumCustomers(), username, password, email, firstName, lastName);
		DAOUtilities.getCustomerDAO().addCustomer(customer);
		customer=DAOUtilities.getCustomerDAO().getCustomerByUsername(customer.getUsername());
		log.info("Registered " + customer.toString());
		return customer;
	}
	public Employee registerEmployee(String username, String password, String email) {
		Employee employee = new Employee(0 + getNumEmployees(), username, password, email);
		DAOUtilities.getEmployeeDAO().addEmployee(employee);
		employee = DAOUtilities.getEmployeeDAO().getEmployeeByUsername(employee.getUsername());
		log.info("Registered " + employee.toString());
		return  employee;
	}
	public Admin registerAdmin(String username, String password, String email) {
		Admin admin = new Admin(0 + getNumAdmins(), username, password, email);
		DAOUtilities.getAdminDAO().addAdmin(admin);
		admin=DAOUtilities.getAdminDAO().getAdminByUsername(admin.getUsername());
		log.info("Registered " + admin.toString());
		return admin;
	}
	public void approveApplication(Application application, Employee employee) {
		System.out.println(application.getStatus() + " Status");
		if(application.isOpen()) {
			Account account = new Account(0, application.getPrimaryCustomerId(), application.getSecondaryCustomerId(), application.getApplicationId(), "Open");
			DAOUtilities.getAccountDAO().addAccount(account, application);
			account = DAOUtilities.getAccountDAO().getAccountByApplicationId(application.getApplicationId());
			log.info("Account created: "+ account.toString());
			application.approve(employee.getUserId(), account.getAccountId());
			DAOUtilities.getApplicationDAO().updateApplication(application);
			application=DAOUtilities.getApplicationDAO().getApplicationByApplicationId(application.getApplicationId());
			log.info("Approved " + application.toString());
		}else
			System.out.println("The application has already been finalized.\n\n");
	}
	public void denyApplication(Application application, Employee employee) {
		if(application.isOpen()) {
			application.deny(employee.getUserId());
			DAOUtilities.getApplicationDAO().updateApplication(application);
			application=DAOUtilities.getApplicationDAO().getApplicationByApplicationId(application.getApplicationId());
			log.info("Denied " + application.toString());			
		}else
			System.out.println("The application has already been finalized.\n\n");
	}	
	public void customerApply(Customer primaryCustomer, Customer secondaryCustomer) {
		Application application = null;
		if (secondaryCustomer !=null) {
			application = new Application(0, primaryCustomer.getUserId(), secondaryCustomer.getUserId());
			DAOUtilities.getApplicationDAO().addApplication(application);
			log.info("New application for customer: "+ primaryCustomer + " and " + secondaryCustomer);
		}
		else {
			application = new Application(0, primaryCustomer.getUserId(), 0);
			DAOUtilities.getApplicationDAO().addApplication(application);
			log.info("New application for customer: "+ primaryCustomer );
		}
	}
	public void cancelAccount(Admin admin, Account account){
		account.cancel(admin);
		DAOUtilities.getAccountDAO().updateAccount(account);
		log.info("Canceled account:   Admin: " + admin.getUsername() + "  Account:" + account.getAccountId() );
	}
	public void withdraw(Account account, double amount) {
		if(account.getBalance()>=amount) {
			account.withdraw(amount);
			DAOUtilities.getAccountDAO().updateAccount(account);
			log.info("Withdrew $" + amount + " from "+account.toString());
		}else
			System.out.println("Account balance is less than $" + amount);
	}
	public boolean transfer(Account fromAccount, Account toAccount, double amount) {
		if(fromAccount.getBalance()>=amount) {
			fromAccount.withdraw(amount);
//			DAOUtilities.getAccountDAO().updateAccount(fromAccount);
			toAccount.deposit(amount);
//			DAOUtilities.getAccountDAO().updateAccount(toAccount);
			DAOUtilities.getAccountDAO().accountTransfer(toAccount, fromAccount);
			return true;
		}else
			return false;
	}
	public void deposit(Account account, double amount) {
		account.deposit(amount);
		DAOUtilities.getAccountDAO().updateAccount(account);
		log.info("Deposited $" + amount + " to "+account.toString());
	}
	
}
