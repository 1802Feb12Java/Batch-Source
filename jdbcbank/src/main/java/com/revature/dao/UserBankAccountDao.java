package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

public interface UserBankAccountDao {

	public void addUserBankAccount(int userId, int accountId) throws SQLException;

	public void getUserBankAccount(int userId, int accountId) throws SQLException;

	public void updateUserBankAccountUserId(int userId, int accountId) throws SQLException;

	public void deleteUserBankAccount(int userId, int accountId) throws SQLException;

	public List<Integer> getAllUserBankAccounts() throws SQLException;

	public List<Integer> getAllUserBankAccountsWithUserId(int userId) throws SQLException;

}
