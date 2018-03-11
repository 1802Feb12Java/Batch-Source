package com.revature.daos;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Employee;
import com.revature.beans.User;

public interface EmployeeDAO {

	
	//public EmployeeDAO getInstance() throws ClassNotFoundException, SQLException;
	public void addUserToTable(User user) throws SQLException;
	//returns and arraylist of employee objects in order to build the list of employee
	public ArrayList<Employee> getAllEmployees() throws SQLException;
	//return just one employee, will validate password as well,
	//NOTE: passing admin as the password will skip password validation
	//users will be restricted from having admin as a password.
	public Employee getEmployee(String username, String password) throws SQLException;
	public String getEmployeePassword(String username) throws SQLException;
	public Integer getEmployeeID(String username) throws SQLException;
	//If changing just the password newUsername and oldUsername will be the same or newUsername can be null
	public void updateAccountInformation(String newUsername, String oldUsername, String password) throws SQLException;
	
	
	
}
