package com.revature.banking.part1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bank implements Serializable {
	
	private static final long serialVersionUID = -6177103669279664375L;
	
	private int numUsers, numCustomers, numEmployees, numAdmin, numAccounts,numApplications;
	private Set<String> usernames = new HashSet<String>();
	private Map<String, User> userIds = new HashMap<String, User>();
	private List<Application> applications = new ArrayList<Application>();
	private List<Account> accounts = new ArrayList<Account>();
	private List<Customer> customers = new ArrayList<Customer>();
	private List<Employee> employees  = new ArrayList<Employee>();
	private List<Admin> admins = new ArrayList<Admin>();
	
	public Bank() {
		this.numUsers = 0;
		this.numCustomers = 0;
		this.numEmployees = 0;
		this.numAdmin = 0;
		this.numAccounts = 0;
		this.numApplications=0;
		Admin admin = new Admin(1000000 + numAdmin, "admin", "password");
		this.usernamesAdd(admin.getUsername());
		this.userIdsPut(admin.getUsername(), admin);
		//this.adminIDsAdd(admin.getID());
		System.out.println("New admin username is \"admin\" and new password is \"password\". "
				+ "Please change them ASAP");
		//DataManager.saveAdmin(admin);
	}
	public int getNumUsers() {
		return customers.size() + employees.size() + admins.size();
	}
	public int getNumCustomers() {
		return customers.size();
	}
	public int getNumEmployees() {
		return employees.size();
	}
	public int getNumAdmins() {
		return  admins.size();
	}
	public int getNumAccounts() {
		return accounts.size();
	}
	public int getNumApplications() {
		return applications.size();
	}
	public List<Application> getApplications(){
		return applications;
	}
	public List<Account> getAccounts(){
		return accounts;
	}
	public List<Customer> getCustomers(){
		return customers;
	}
	public List<Employee> getEmployees(){
		return employees;
	}
	public List<Admin> getAdmins(){
		return admins;
	}
	public boolean usernamesContains(String username) {
		return usernames.contains(username);
	}
	public boolean usernamesAdd(String username) {
		boolean status = usernames.add(username);
		//DataManager.saveBankData(this);
		return status;
	}
	public boolean usernamesRemove(String username) {
		boolean status = usernames.remove(username);
		//DataManager.saveBankData(this);
		return status;
	}
	public User userIdsGet(String key) {
		return userIds.get(key);
	}
	public boolean userIdsContainsKey(String key) {
		return userIds.containsKey(key);
	}
	public boolean userIdsContainsValue(User value) {
		return userIds.containsValue(value);
	}
	public void userIdsPut(String key, User value) {
		userIds.put(key, value);
	}
	public void userIdsRemove(String key) {
		userIds.remove(key);
	}

	public boolean customerIDsContains(int customerID) {
		if (customerID <=this.getNumCustomers()+29999999 && customerID >= 30000000)
			return true;
		else
			return false;
		//
	}
	public boolean accountNumbersContains(int accountNumber) {
		if (accountNumber <=this.getNumAccounts()+999999 && accountNumber >= 10000000)
			return true;
		else
			return false;
		//
	}
	public Customer getCustomerById(int id) {
		Customer customer = null;
		System.out.println(customers.toString());
		for (Customer customer2: customers) {
			if(customer2.getID() == id) {
				customer = customer2;
				break;
			}
		}
		return customer;
	}
	public Account getAccountByAccountNumber(int accountNumber) {
		Account account = null;
		for (Account account2: accounts) {
			if(account2.getAccountNumber() == accountNumber) {
				account = account2;
				break;
			}
		}
		return account;
	}
	@Override
	public String toString() {
		return "Bank [Number of Users=" + numUsers + ", Number of Customers=" + numCustomers 
				+ ", Number of Employees=" + numEmployees	+ ", Number of Administrators=" + numAdmin 
				+ ", Number of Accounts=" + numAccounts + ", Number of Applications=" + numApplications +"]";
	}
	public Customer getCustomer(int customerID) {
		Customer customer = null;
		
		return customer;
	}
	public Employee getEmployee(int employeeID) {
		Employee employee = null;
		
		return employee;
	}
	public Admin getAdmin(int adminID) {
		Admin admin = null;
		
		return admin;
	}
	
	
	
	public Customer registerCustomer(String username, String password, String name, String address, String phone){
		Customer customer = new Customer(30000000 + getNumCustomers(), username, password, name, address, phone);
		customers.add(customer);
		this.usernamesAdd(username);
		this.userIdsPut(username, customer);
		System.out.println(customer.toString());
		return customer;
	}
	public Employee registerEmployee(String username, String password) {
		Employee employee = new Employee(20000000 + getNumEmployees(), username, password);
		employees.add(employee);
		this.usernamesAdd(username);
		this.userIdsPut(username, employee);
		System.out.println(employee.toString());
		return employee;
	}
	public Admin registerAdmin(String username, String password) {
		Admin admin = new Admin(10000000 + getNumAdmins(), username, password);
		admins.add(admin);
		this.usernamesAdd(username);
		this.userIdsPut(username, admin);
		System.out.println(admin.toString());
		return admin;
	}
	public void approveApplication(Application application, Employee employee) {
		if(application.isOpen()) {
			Account account = new Account(10000000+this.getNumAccounts(), application.getPrimaryCustomer(), application.getSecondaryCustomer(), "Open");
			//this.addNumAccounts();
			accounts.add(account);
			application.getPrimaryCustomer().addAccount(account);
			if (application.getSecondaryCustomer()!=null) {
				application.getSecondaryCustomer().addAccount(account);
			}
		
		application.approve(employee, account.getAccountNumber());
		}else
			System.out.println("The application has already been finalized.\n\n");
	}
	public void denyApplication(Application application, Employee employee) {
		if(application.isOpen()) {
			application.deny(employee);
		}else
			System.out.println("The application has already been finalized.\n\n");
	}
	
	
	public void customerApply(Customer primaryCustomer, Customer secondaryCustomer) {
		
		Application app = null;
		if (secondaryCustomer !=null) {
			app = new Application(5000000+this.getNumApplications(), primaryCustomer, secondaryCustomer);
			primaryCustomer.add(app);
			secondaryCustomer.add(app);
		}
		else {
			app = new Application(5000000+this.getNumApplications(), primaryCustomer, null);
			primaryCustomer.add(app);
		}
		//this.addNumApplications();
		//DataManager.saveApplication(app);
		applications.add(app);
	}
	public void cancelAccount(Admin admin, Account account){
		account.cancel(admin);
	}
	
	
	
	public void withdraw(Account account, double amount) {
		if(account.getBalance()>=amount) {
			account.withdraw(amount);
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
