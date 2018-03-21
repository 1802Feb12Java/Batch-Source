package com.revature.reimbursements;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface UserDAO {
//	-An Employee can login
//	-An Employee can view the Employee Homepage
//	-An Employee can logout
//	-An Employee can submit a reimbursement request
//	-An Employee can upload an image of his/her receipt as part of the reimbursement request
//	-An Employee can view their pending reimbursement requests
//	-An Employee can view their resolved reimbursement requests
//	-An Employee can view their information
//	-An Employee can update their information
//	-An Employee receives an email when one of their reimbursement requests is resolved (optional)
//
//	-A Manager can login
//	-A Manager can view the Manager Homepage
//	-A Manager can logout
//	-A Manager can approve/deny pending reimbursement requests
//	-A Manager can view all pending requests from all employees
//	-A Manager can view images of the receipts from reimbursement requests
//	-A Manager can view all resolved requests from all employees and see which manager resolved it
//	-A Manager can view all Employees	
//	-A Manager can view reimbursement requests from a single Employee

//	-A Manager can register an Employee, which sends the Employee an email with their username and temp password (optional)
//	-An Employee can reset their password (Optional - tied with above functional requirement)
	
	public void createUser(Connection c, User u) throws SQLException;
    public User readUser(int id) throws SQLException;
    public void updateUser(Connection c, String toUpdate, String newValue, int id) throws SQLException;
    public void deleteUser(Connection c, int id) throws SQLException;
    public ArrayList<User> getAllUsers() throws SQLException;
	
	
}
