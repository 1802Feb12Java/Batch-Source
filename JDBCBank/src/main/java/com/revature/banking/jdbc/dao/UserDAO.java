package com.revature.banking.jdbc.dao;

import java.util.ArrayList;
import java.util.List;

import com.revature.banking.jdbc.model.Admin;
import com.revature.banking.jdbc.model.Customer;
import com.revature.banking.jdbc.model.Employee;
import com.revature.banking.jdbc.model.User;
import com.revature.banking.jdbc.utilities.DAOUtilities;;

public class UserDAO implements InterfaceUserDAO {
	public UserDAO() {
	}

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		users.addAll(DAOUtilities.getAdminDAO().getAllAdmins());
		users.addAll(DAOUtilities.getEmployeeDAO().getAllEmployees());
		users.addAll(DAOUtilities.getCustomerDAO().getAllCustomers());
		return users;
	}

	public User getUserByUserId(int userId) {
		User user = (User) DAOUtilities.getAdminDAO().getAdminByAdminId(userId);
		if (user ==null)	
			user = (User) DAOUtilities.getEmployeeDAO().getEmployeeByEmployeeId(userId);
			if(user == null)
				user = (User) DAOUtilities.getCustomerDAO().getCustomerByCustomerId(userId);
		return user;
	}

	public User getUserByUsername(String username) {
		User user = (User) DAOUtilities.getAdminDAO().getAdminByUsername(username);
		if (user ==null)	
			user = (User) DAOUtilities.getEmployeeDAO().getEmployeeByUsername(username);
			if(user == null)
				user = (User) DAOUtilities.getCustomerDAO().getCustomerByUsername(username);
		return user;
	}

	public List<User> getUsersByEmail(String email) {
		List<User> users = new ArrayList<User>();
		for (User user : DAOUtilities.getAdminDAO().getAdminByEmail(email)) {
			if (user!=null)
					users.add(user);
		}
		for (User user : DAOUtilities.getEmployeeDAO().getEmployeeByEmail(email)) {
			if (user!=null)
					users.add(user);
		}
		for (User user : DAOUtilities.getCustomerDAO().getCustomerByEmail(email)) {
			if (user!=null)
					users.add(user);
		}
		return users;
	}

	public boolean usernamesExists(String username) {
		User user = DAOUtilities.getAdminDAO().getAdminByUsername(username);
		if (user!=null)
			return true;
		user = DAOUtilities.getEmployeeDAO().getEmployeeByUsername(username);
		if (user!=null)
			return true;
		user = DAOUtilities.getCustomerDAO().getCustomerByUsername(username);
		if (user!=null)
			return true;
		return false;
	}

	public boolean updateUser(User user) {
		if (user.getRole().equals("Admin"))
			return DAOUtilities.getAdminDAO().updateAdmin((Admin) user);
		else if (user.getRole().equals("Employee"))
			return DAOUtilities.getEmployeeDAO().updateEmployee((Employee) user);
		else if (user.getRole().equals("Customer"))
			return DAOUtilities.getCustomerDAO().updateCustomer((Customer) user);
		else
			return false;
	}

	public boolean addUser(User user) {
		if (user.getRole().equals("Admin"))
			return DAOUtilities.getAdminDAO().addAdmin((Admin) user);
		else if (user.getRole().equals("Employee"))
			return DAOUtilities.getEmployeeDAO().addEmployee((Employee) user);
		else if (user.getRole().equals("Customer"))
			return DAOUtilities.getCustomerDAO().addCustomer((Customer) user);
		else
			return false;
	}

	public boolean deleteUser(User user) {
		if (user.getRole().equals("Admin"))
			return DAOUtilities.getAdminDAO().deleteAdmin((Admin) user);
		else if (user.getRole().equals("Employee"))
			return DAOUtilities.getEmployeeDAO().deleteEmployee((Employee) user);
		else if (user.getRole().equals("Customer"))
			return DAOUtilities.getCustomerDAO().deleteCustomer((Customer) user);
		else
			return false;
	}

	public int getNumUsers() {
		return DAOUtilities.getAdminDAO().getNumAdmins() + DAOUtilities.getEmployeeDAO().getNumEmployees() + DAOUtilities.getCustomerDAO().getNumCustomers();
	}

}
