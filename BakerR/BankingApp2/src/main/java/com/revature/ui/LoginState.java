package com.revature.ui;

import java.sql.SQLException;
import java.util.Base64;

import com.revature.db.UserDAO;
import com.revature.db.UserDAOImpl;
import com.revature.model.Admin;
import com.revature.model.Customer;
import com.revature.model.Employee;
import com.revature.model.User;
import com.revature.util.PasswordUtils;

public class LoginState extends PromptState {

	@Override
	public void execute() {
		String username = "";
		int tryCount = 5;
		for(;tryCount-->0;) {
			// Prompt: username
			username = prompt("Enter a username: ");
			// Prompt: Password
			char[] pw = PasswordUtils.promptPassword();
			
			
			// Verify password: successful -> break loop.
			User tmpUsr = new Customer();
			tmpUsr.setUserName(username);

			try {
				UserDAO usrDao = new UserDAOImpl();
				String passHash = usrDao.getPWHash(tmpUsr);
				boolean isValid = PasswordUtils.validatePassword(
						PasswordUtils.convertCharsToBytes(pw),
						Base64.getDecoder().decode(passHash));
				if(isValid) break;
			} catch (ClassNotFoundException | SQLException e) {
			}
		}
		
		// Failed password verification -> back to EntryState
		if(tryCount <= 5) {
			setNextState(new EntryState());
		} else {
			// Password verification successful.
			// Get account type and set next state according to user type.
			try {
				UserDAO usrDao = new UserDAOImpl();
				User usr = usrDao.getUserByUsername(username);
				
				if(usr instanceof Customer) {
					setNextState(new CustomerInputState((Customer)usr));
				} else if(usr instanceof Admin) {
					setNextState(new AdminInputState((Admin)usr));
				} else if(usr instanceof Employee) {
					setNextState(new EmployeeInputState((Employee)usr));
				} else {
					setNextState(new EntryState());
				}
				
			} catch (ClassNotFoundException | SQLException e) {
			}
		}
	}

}
