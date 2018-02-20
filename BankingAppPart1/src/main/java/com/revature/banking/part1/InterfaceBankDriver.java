package com.revature.banking.part1;

import java.util.Scanner;

public interface InterfaceBankDriver {
	public void welcome(Scanner scan);
	public Customer registerCustomer(Scanner scan);
	public Employee registerEmployee(Admin admin, Scanner scan);
	public Admin registerAdmin(Admin admin, Scanner scan);
	public void login(Scanner scan);
	public void userProfile(User user, Scanner scan);
	public void home(Admin admin, Scanner scan);
	public void home(Employee employee, Scanner scan);
	public void home(Customer customer, Scanner scan);
	public void viewApplicationsCustomer(Customer customer, Scanner scan);
	public void viewApplicationsEmployee(Employee employee, Scanner scan);
	public void customerApply(Customer customer, Scanner scan);
	//public void viewCustomerInfo(User user, Scanner scan);
	public void modAccountBalance(Admin admin, Scanner scan);
	public void modAccountBalance(Customer customer, Scanner scan);
	public void cancelAccount(Admin admin, Scanner scan);
}
