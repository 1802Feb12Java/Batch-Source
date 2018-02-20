package com.revature.banking.part1;

public interface InterfaceEmployee {
	public void checkApplications();
	public void approveApplication(Employee employee, Application application);
	public void denyApplication(Employee employee, Application application);
	public void getAccountByAccountNumber(int accountNumber);
	public void getAllCustomers();
}
