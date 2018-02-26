package com.revature.BankingAppPt2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class EmployeeDAO extends UserDAO {

	final static Logger logger = Logger.getLogger(EmployeeDAO.class);
	
	public EmployeeDAO(Connection connection) {
		super(connection);
	}
	
	public List<BankAccount> getPendingAccounts() {
		List<BankAccount> pendingAccounts = new ArrayList<BankAccount>();
		
		String pendingAccountString = "SELECT * from bankAccounts WHERE approval = 'P'";

		try {
			PreparedStatement pendingAccountQuery = connection.prepareStatement(pendingAccountString);
			ResultSet resultSet = pendingAccountQuery.executeQuery();
			while(resultSet.next()) {
				int accountId = resultSet.getInt(1);
				String accountType = resultSet.getString(2);
				int balance = resultSet.getInt(3);
				String approvalStatus = resultSet.getString(4);
				BankAccount bankAccount = new BankAccount(accountId, accountType, balance, approvalStatus);
				pendingAccounts.add(bankAccount);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			logger.error(e.toString());
		}
		return pendingAccounts;
	}

	public void approveAccount(String approval, int accountId) {
		String approvalString = "UPDATE bankAccounts SET approval = ? WHERE accountId = ?";
		try {
			PreparedStatement approvalQuery = connection.prepareStatement(approvalString);
			approvalQuery.setString(1, approval);
			approvalQuery.setInt(2, accountId);
			int status = approvalQuery.executeUpdate();
			if (status > 0) {
				System.out.println("Account Approved");
			}
			else {
				System.out.println("Error in EmpDAO");
				throw new SQLException("Error setting account approval status");
			}
		} catch (SQLException e) {
			logger.error(e.toString());
		}
	}

}
