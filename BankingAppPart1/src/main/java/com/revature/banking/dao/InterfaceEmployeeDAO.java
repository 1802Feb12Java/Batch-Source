package com.revature.banking.dao;

import java.util.List;

import com.revature.banking.model.Employee;

public interface InterfaceEmployeeDAO {
	public List<Employee> getAllEmployees();
	public Employee getEmployeeByEmployeeId(int employeeId);
	public boolean updateEmployee(Employee employee);
	public boolean addEmployee(Employee employee);
	public boolean deleteEmployee(Employee employee);
	public int getNumEmployees();
}
