package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.revature.beans.BankAccount;
import com.revature.beans.Credential;
import com.revature.dao.impl.BankAccountDaoImpl;
import com.revature.dao.impl.TransactionDaoImpl;
import com.revature.dao.impl.UserDaoImpl;
import com.revature.util.ConnectionFactory;

public class LogicTheWrapper {
	private static final Logger logger = LogManager.getLogger(LogicTheWrapper.class);
	private static UserDaoImpl userDaoImpl = new UserDaoImpl();
	private static BankAccountDaoImpl bankAccDaoImpl = new BankAccountDaoImpl();
	private static TransactionDaoImpl transactionDaoImpl = new TransactionDaoImpl();

	private Connection con = ConnectionFactory.getInstance().getConnection();

	public static boolean checkCredentials(String username, String password) {
		// check username exists first.
		List<Credential> creds;
		try {
			creds = userDaoImpl.getAllUsers();
			if (creds == null)
				return false;
			for (Credential c : creds) {
				if (c.getUsername().equals(username))
					// check password
					if (c.getPassword().equals(password)) {
						// logger.info("Correct Password entered");
						return true;
					}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public static List<BankAccount> getAccounts(int userId) {
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		bankAccDaoImpl.getAllBankAccounts();

		return accounts;
	}

}
