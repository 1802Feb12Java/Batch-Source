package com.revature;

import java.sql.SQLException;

import com.revature.beans.User;

public interface UserDAO {

	void createAcc(String name, String password, double bal) throws SQLException;
	void viewAcc(User u) throws SQLException;
	void deleteAcc(User u) throws SQLException;
	void depositMoney(User u, double d) throws SQLException;
	void withdrawMoney(User u, double d) throws SQLException;
}
