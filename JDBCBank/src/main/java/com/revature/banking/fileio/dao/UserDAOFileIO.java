package com.revature.banking.fileio.dao;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.revature.banking.fileio.model.AdminFileIO;
import com.revature.banking.fileio.model.CustomerFileIO;
import com.revature.banking.fileio.model.EmployeeFileIO;
import com.revature.banking.fileio.model.UserFileIO;
import com.revature.banking.fileio.utilities.DAOUtilitiesFileIO;;

public class UserDAOFileIO implements InterfaceUserDAOFileIO {
	public UserDAOFileIO() {
	}

	public List<UserFileIO> getAllUsers() {
		List<UserFileIO> users = new ArrayList<UserFileIO>();
		users.addAll(DAOUtilitiesFileIO.getAdminDAO().getAllAdmins());
		users.addAll(DAOUtilitiesFileIO.getEmployeeDAO().getAllEmployees());
		users.addAll(DAOUtilitiesFileIO.getCustomerDAO().getAllCustomers());
		return users;
	}

	public UserFileIO getUserByUserId(int userId) {
		UserFileIO user = (UserFileIO) DAOUtilitiesFileIO.getAdminDAO().getAdminByAdminId(userId);
		if (userId >= DAOUtilitiesFileIO.adminStartId)			
			if (user ==null)
				user = (UserFileIO) DAOUtilitiesFileIO.getEmployeeDAO().getEmployeeByEmployeeId(userId);
				if(user == null)
					user = (UserFileIO) DAOUtilitiesFileIO.getCustomerDAO().getCustomerByCustomerId(userId);
		return user;
	}

	public UserFileIO getUserByUsername(String username) {
		for (String folder : DAOUtilitiesFileIO.usersFolders) {
			for (File file : new File(folder).listFiles()) {
				UserFileIO user = (UserFileIO) DAOUtilitiesFileIO.objectReadFile(file);
				if (user!=null)
					if (user.getUsername().equals(username))
						return user;
			}
		}
		return null;
	}

	public List<UserFileIO> getUsersByEmail(String email) {
		List<UserFileIO> users = new ArrayList<UserFileIO>();
		for (String folder : DAOUtilitiesFileIO.usersFolders) {
			for (File file : new File(folder).listFiles()) {
				UserFileIO user = (UserFileIO) DAOUtilitiesFileIO.objectReadFile(file);
					if (user!=null)
						if (user.getEmail().equals(email))
							users.add(user);
			}
		}
		return users;
	}

	public boolean usernamesExists(String username) {
		for (String folder : DAOUtilitiesFileIO.usersFolders) {
			for (File file : new File(folder).listFiles()) {
				UserFileIO user = (UserFileIO) DAOUtilitiesFileIO.objectReadFile(file);
				if (user!=null)
					if (user.getUsername().equals(username))
						return true;
			}
		}
		return false;
	}

	public boolean updateUser(UserFileIO user) {
		if (user.getRole().equals("Admin"))
			return DAOUtilitiesFileIO.getAdminDAO().updateAdmin((AdminFileIO) user);
		else if (user.getRole().equals("Employee"))
			return DAOUtilitiesFileIO.getEmployeeDAO().updateEmployee((EmployeeFileIO) user);
		else if (user.getRole().equals("Customer"))
			return DAOUtilitiesFileIO.getCustomerDAO().updateCustomer((CustomerFileIO) user);
		else
			return false;
	}

	public boolean addUser(UserFileIO user) {
		if (user.getRole().equals("Admin"))
			return DAOUtilitiesFileIO.getAdminDAO().addAdmin((AdminFileIO) user);
		else if (user.getRole().equals("Employee"))
			return DAOUtilitiesFileIO.getEmployeeDAO().addEmployee((EmployeeFileIO) user);
		else if (user.getRole().equals("Customer"))
			return DAOUtilitiesFileIO.getCustomerDAO().addCustomer((CustomerFileIO) user);
		else
			return false;
	}

	public boolean deleteUser(UserFileIO user) {
		if (user.getRole().equals("Admin"))
			return DAOUtilitiesFileIO.getAdminDAO().deleteAdmin((AdminFileIO) user);
		else if (user.getRole().equals("Employee"))
			return DAOUtilitiesFileIO.getEmployeeDAO().deleteEmployee((EmployeeFileIO) user);
		else if (user.getRole().equals("Customer"))
			return DAOUtilitiesFileIO.getCustomerDAO().deleteCustomer((CustomerFileIO) user);
		else
			return false;
	}

	public int getNumUsers() {
		return DAOUtilitiesFileIO.getAdminDAO().getNumAdmins() + DAOUtilitiesFileIO.getEmployeeDAO().getNumEmployees() + DAOUtilitiesFileIO.getCustomerDAO().getNumCustomers();
	}

}
