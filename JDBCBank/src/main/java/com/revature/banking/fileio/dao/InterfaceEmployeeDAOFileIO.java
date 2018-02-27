package com.revature.banking.fileio.dao;

import java.util.List;

import com.revature.banking.fileio.model.EmployeeFileIO;

public interface InterfaceEmployeeDAOFileIO {
	public List<EmployeeFileIO> getAllEmployees();
	public EmployeeFileIO getEmployeeByEmployeeId(int employeeId);
	public boolean updateEmployee(EmployeeFileIO employee);
	public boolean addEmployee(EmployeeFileIO employee);
	public boolean deleteEmployee(EmployeeFileIO employee);
	public int getNumEmployees();
}
