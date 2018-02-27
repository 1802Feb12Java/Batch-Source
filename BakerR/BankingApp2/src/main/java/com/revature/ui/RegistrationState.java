package com.revature.ui;

import java.sql.SQLException;
import java.util.Base64;

import com.revature.db.UserDAO;
import com.revature.db.UserDAOImpl;
import com.revature.model.Admin;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.model.UserType;
import com.revature.util.PasswordUtils;

public final class RegistrationState extends PromptState {
	private UserType type;
	
	public RegistrationState(UserType type) {
		super();
		this.type = type;
	}
	
	@Override
	public void execute() {
		// Prompt: FName
		String fname = prompt("Enter first name: ");
		
		// Prompt: LName
		String lname = prompt("Enter last name: ");
		
		// Prompt: Username
		String uname = prompt("Enter username: ");
		
		// Prompt: Password 
		char[] pw = PasswordUtils.promptPassword();
		byte[] pwHash = PasswordUtils.hashPassword(PasswordUtils.convertCharsToBytes(pw));
		String pwHash64 = Base64.getEncoder().encodeToString(pwHash);
		
		UserDAO dao;
		try {
			dao = new UserDAOImpl();
			User usr = null;
			
			// Create a user accordingly & use DAO to log it into DB.
			switch(type) {
			case ADMIN:
				 usr = new Admin();
				break;
			case EMPLOYEE:
				usr = new Employee();
				break;
			case CUSTOMER:
				usr = new Customer();
				break;
			default:
				// Unknown account type.
				return;
			}
			
			usr.setFirstName(fname);
			usr.setLastName(lname);
			usr.setUserName(uname);
			
			usr = dao.createUser(usr, pwHash64);
		} catch (ClassNotFoundException | SQLException e1) {
		}
		
		
		// Next state: EntryState
		setNextState(new EntryState());
	}
}
