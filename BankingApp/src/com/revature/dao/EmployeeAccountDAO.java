package com.revature.dao;

import java.util.ArrayList;

import com.revature.accounts.EmployeeAccount;

public interface EmployeeAccountDAO {
	
	public ArrayList<EmployeeAccount> getEmployees();
	public void addEmployee(EmployeeAccount employee);
	public void deleteEmployee(EmployeeAccount employee);
	public void updateEmployee(EmployeeAccount employee);
	
}
