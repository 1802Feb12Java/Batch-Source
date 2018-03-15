package com.revature.daos;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Employee;
import com.revature.beans.User;

public interface EmployeeDAO {

	
	//public EmployeeDAO getInstance() throws ClassNotFoundException, SQLException;
	public void addUserToTable(User user) throws SQLException;
	//returns and arraylist of employee objects in order to build the list of employee
	public ArrayList<User> getAllEmployees() throws SQLException;
	//return just one employee, will validate password as well,
	//NOTE: passing admin as the password will skip password validation
	//users will be restricted from having admin as a password.
	public User getEmployee(String username) throws SQLException, ClassNotFoundException;
	public String getEmployeePassword(String username) throws SQLException;
	public Integer getEmployeeID(String username) throws SQLException;
	//Updates all information, some information can remain same
	void updateAccountInformation(Integer empId, String newUsername, String password, String email, String firstname,
			String lastname) throws SQLException;
	
	
	
}
