package com.revature;

import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Reimbursements;
import com.revature.beans.User;

public interface ManagerDAO {

	void approveRequest(int r, int u) throws SQLException;
	void denyRequest(int r, int u) throws SQLException;
	ArrayList<Reimbursements> viewAllPending() throws SQLException;
	ArrayList<String> viewImages() throws SQLException;
	ArrayList<Reimbursements> viewAllResolved() throws SQLException; 
	ArrayList<User> viewAllEmps() throws SQLException;
	ArrayList<User> getAllUsers() throws SQLException;
	ArrayList<Reimbursements> viewSingleRequest(int u) throws SQLException; 
	
}

//maybe some more optional things
