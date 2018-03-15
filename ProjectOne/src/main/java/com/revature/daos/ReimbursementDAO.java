package com.revature.daos;

import java.util.ArrayList;

import com.revature.beans.Reimbursement;
import com.revature.beans.User;
import java.sql.Date;
import java.sql.SQLException;

public interface ReimbursementDAO {

	//return list of all reimbursements from user
	public ArrayList<Reimbursement>getReimbursements(User user) throws SQLException;
	//
	public void addReimbursement(Reimbursement reimb) throws SQLException;

	public byte[] getReimbursementImage(Integer id) throws SQLException;
	
	void resolved(Integer id, Integer resolver, Integer status) throws SQLException;
}
