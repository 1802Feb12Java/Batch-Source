package com.talanian.dao;

import java.io.File;
import java.util.List;

import com.talanian.beans.Reimbursement;
import com.talanian.beans.User;

public interface SystemDAO {
	public int userLogin(String username, String password, String isManager) throws ClassNotFoundException;
	public boolean submitRequest(Reimbursement reimbursement);
	public Reimbursement[] viewReimbursement(User user, int reimbursementType);
	public File getReciept(int R_ID);
	public boolean updateInfo(User user);
	public List<User> viewAllEmployees();
	public Reimbursement[] viewAllReimbursements(int reimbursementType);
	public boolean handleReimbursementRequests(Reimbursement reimbursement);
	public User viewSingleEmployee( int user_ID);
	public boolean register(String username, String password, String email);
	public String returnUserAsSession(String username, String password);
	public List<String> returnRList( int userID, boolean isManager);
	public boolean submitFields(int userID, double amount, String desc, File file, String fileExt);
	public boolean approveall();
	public boolean denyall();
	public boolean statuschange(String RID, String UID, int status);
	public void addname(String fname, String lname, String Username, String Password);
}