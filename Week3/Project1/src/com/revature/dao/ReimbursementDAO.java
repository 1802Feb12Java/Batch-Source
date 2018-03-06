package com.revature.dao;

public interface ReimbursementDAO {
	//Create
	void submitReimbursement();
	
	//Read
	void getReimbursements(String user);
	void getPendingReimbursements();
}