package com.revature.dao;

import java.util.ArrayList;

import com.revature.accounts.EmployeeAccount;

public interface EmployeeAccountDAO {
	
	public ArrayList<EmployeeAccount> getEmployees();
	public void addCustomer(EmployeeAccount employee);
	public void deleteCustomer(EmployeeAccount employee);
	public void updateCustomer(EmployeeAccount employee);
	
}
