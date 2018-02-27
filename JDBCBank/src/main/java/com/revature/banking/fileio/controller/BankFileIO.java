package com.revature.banking.fileio.controller;

import java.io.Serializable;
import java.util.List;

import com.revature.banking.fileio.dao.BankDAOFileIO;
import com.revature.banking.fileio.model.AccountFileIO;
import com.revature.banking.fileio.model.AdminFileIO;
import com.revature.banking.fileio.model.ApplicationFileIO;
import com.revature.banking.fileio.model.CustomerFileIO;
import com.revature.banking.fileio.model.EmployeeFileIO;
import com.revature.banking.fileio.model.UserFileIO;
import com.revature.banking.fileio.utilities.DAOUtilitiesFileIO;

public class BankFileIO implements Serializable {
	private static final long serialVersionUID = -6177103669279664375L;
	private int adminNextId =10000000;
	private int customerNextId =30000000;
	private int employeeNextId=20000000;
	private int accountsNextId =100000000;
	private int applicationNextId=50000000;
	public BankFileIO() {
		AdminFileIO admin = new AdminFileIO(adminNextId++, "admin", "password", "admin@bank.com");
		DAOUtilitiesFileIO.getAdminDAO().addAdmin(admin);
		System.out.println("New admin username is \"admin\" and new password is \"password\". "
				+ "Please change them ASAP");
	}
	public static BankFileIO loadBank() {
		DAOUtilitiesFileIO.createFolders();
		BankFileIO bank = BankDAOFileIO.getBank();
		if(bank == null) {
			bank = new BankFileIO();
			DAOUtilitiesFileIO.getBankDAO().addBank(bank);
		}
		return bank;
	}
	public static BankFileIO loadNewBank() {
		BankFileIO bank = new BankFileIO();
		BankDAOFileIO.saveBank(bank);
		return bank;
	}
	public static void saveBank(BankFileIO bank) {
		DAOUtilitiesFileIO.getBankDAO().updateBank(bank);
	}
	public int getNumApplications() {
		return DAOUtilitiesFileIO.getApplicationDAO().getAllApplications().size();
	}
	public int getNumAccounts() {
		return DAOUtilitiesFileIO.getAccountDAO().getAllAccounts().size();
	}
	public int getNumAdmins() {
		return  DAOUtilitiesFileIO.getAdminDAO().getAllAdmins().size();
	}
	public int getNumCustomers() {
		return DAOUtilitiesFileIO.getCustomerDAO().getAllCustomers().size();
	}
	public int getNumEmployees() {
		return DAOUtilitiesFileIO.getEmployeeDAO().getAllEmployees().size();
	}
	public int getNumUsers() {
		return DAOUtilitiesFileIO.getUserDAO().getNumUsers();
	}
	public List<ApplicationFileIO> getApplications(){
		return DAOUtilitiesFileIO.getApplicationDAO().getAllApplications();
	}
	public List<ApplicationFileIO> getApplicationsByCustomerId(int customerId){
		return DAOUtilitiesFileIO.getApplicationDAO().getApplicationsByCustomerId(customerId);
	}
	public List<AccountFileIO> getAccounts(){
		return DAOUtilitiesFileIO.getAccountDAO().getAllAccounts();
	}
	public List<AccountFileIO> getAccountsByCustomerId(int customerId){
		return DAOUtilitiesFileIO.getAccountDAO().getAccountsByCustomerId(customerId);
	}
	public List<AdminFileIO> getAdmins(){
		return DAOUtilitiesFileIO.getAdminDAO().getAllAdmins();
	}
	public List<CustomerFileIO> getCustomers(){
		return DAOUtilitiesFileIO.getCustomerDAO().getAllCustomers();
	}
	public List<EmployeeFileIO> getEmployees(){
		return DAOUtilitiesFileIO.getEmployeeDAO().getAllEmployees();
	}
	public boolean updateUsername(UserFileIO user) {
		return DAOUtilitiesFileIO.getUserDAO().updateUser(user);		
	}
	public boolean updatePassword(UserFileIO user) {
		return DAOUtilitiesFileIO.getUserDAO().updateUser(user);	
	}
	public boolean usernameExists(String username) {
		return DAOUtilitiesFileIO.getUserDAO().usernamesExists(username);
	}
	public boolean customerIdExists(int customerId) {
		return DAOUtilitiesFileIO.getCustomerDAO().customerExists(customerId);
	}
	public boolean accountIdExists(int accountId) {
		return DAOUtilitiesFileIO.getAccountDAO().accountExists(accountId);
	}
	public CustomerFileIO getCustomerById(int customerId) {
		return DAOUtilitiesFileIO.getCustomerDAO().getCustomerByCustomerId(customerId);
	}
	public AccountFileIO getAccountByAccountId(int accountId) {
		return DAOUtilitiesFileIO.getAccountDAO().getAccountByAccountId(accountId);
	}
	public UserFileIO getUserByUsername(String username) {
		return DAOUtilitiesFileIO.getUserDAO().getUserByUsername(username);
	}
	@Override
	public String toString() {
		return "Bank [Users=" + this.getNumUsers() + ", Customers=" + this.getNumCustomers() 
				+ ", Employees=" + this.getNumEmployees()	+ ", Administrators=" + this.getNumAdmins() 
				+ ", Accounts=" + this.getNumAccounts() + ", Applications=" + this.getNumApplications() +"]";
	}
	public CustomerFileIO getCustomer(int customerId) {
		return DAOUtilitiesFileIO.getCustomerDAO().getCustomerByCustomerId(customerId);
	}
	public EmployeeFileIO getEmployee(int employeeId) {
		return DAOUtilitiesFileIO.getEmployeeDAO().getEmployeeByEmployeeId(employeeId);
	}
	public AdminFileIO getAdmin(int adminId) {
		return DAOUtilitiesFileIO.getAdminDAO().getAdminByAdminId(adminId);
	}
	public CustomerFileIO registerCustomer(String username, String password, String name, String address, String phone){
		CustomerFileIO customer = new CustomerFileIO(customerNextId++ + getNumCustomers(), username, password, name, address, phone);
		BankFileIO.saveBank(this);
		DAOUtilitiesFileIO.getCustomerDAO().addCustomer(customer);
		return customer;
	}
	public EmployeeFileIO registerEmployee(String username, String password, String email) {
		EmployeeFileIO employee = new EmployeeFileIO(employeeNextId++ + getNumEmployees(), username, password, email);
		BankFileIO.saveBank(this);
		DAOUtilitiesFileIO.getEmployeeDAO().addEmployee(employee);
		return  employee;
	}
	public AdminFileIO registerAdmin(String username, String password, String email) {
		AdminFileIO admin = new AdminFileIO(adminNextId++ + getNumAdmins(), username, password, email);
		BankFileIO.saveBank(this);
		DAOUtilitiesFileIO.getAdminDAO().addAdmin(admin);
		return admin;
	}
	public void approveApplication(ApplicationFileIO application, EmployeeFileIO employee) {
		if(application.isOpen()) {
			AccountFileIO account = new AccountFileIO(accountsNextId++, application.getPrimaryCustomer(), application.getSecondaryCustomer(), "Open");
			BankFileIO.saveBank(this);
			DAOUtilitiesFileIO.getAccountDAO().addAccount(account);
			application.approve(employee.getUserId(), account.getAccountId());
			DAOUtilitiesFileIO.getApplicationDAO().updateApplication(application);
		}else
			System.out.println("The application has already been finalized.\n\n");
	}
	public void denyApplication(ApplicationFileIO application, EmployeeFileIO employee) {
		if(application.isOpen()) {
			application.deny(employee.getUserId());
		}else
			System.out.println("The application has already been finalized.\n\n");
	}	
	public void customerApply(CustomerFileIO primaryCustomer, CustomerFileIO secondaryCustomer) {
		ApplicationFileIO application = null;
		if (secondaryCustomer !=null) {
			application = new ApplicationFileIO(this.applicationNextId++, primaryCustomer.getUserId(), secondaryCustomer.getUserId());
			BankFileIO.saveBank(this);
			DAOUtilitiesFileIO.getApplicationDAO().addApplication(application);
		}
		else {
			application = new ApplicationFileIO(this.applicationNextId++, primaryCustomer.getUserId(), 0);
			BankFileIO.saveBank(this);
			DAOUtilitiesFileIO.getApplicationDAO().addApplication(application);
		}
	}
	public void cancelAccount(AdminFileIO admin, AccountFileIO account){
		account.cancel(admin);
		DAOUtilitiesFileIO.getAccountDAO().updateAccount(account);
	}
	public void withdraw(AccountFileIO account, double amount) {
		if(account.getBalance()>=amount) {
			account.withdraw(amount);
			DAOUtilitiesFileIO.getAccountDAO().updateAccount(account);
		}else
			System.out.println("Account balance is less than $" + amount);
	}
	public boolean transfer(AccountFileIO fromAccount, AccountFileIO toAccount, double amount) {
		// TODO Auto-generated method stub
		if(fromAccount.getBalance()>=amount) {
			fromAccount.withdraw(amount);
			toAccount.deposit(amount);
			return true;
		}else
			return false;
	}
	public void deposit(AccountFileIO toAccount, double amount) {
		toAccount.deposit(amount);
	}
}
