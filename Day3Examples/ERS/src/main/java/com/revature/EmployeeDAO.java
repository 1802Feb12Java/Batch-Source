package com.revature;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.beans.Reimbursements;
import com.revature.beans.User;

public interface EmployeeDAO {

	void submitRequest(double amount, String descrip, int author, int type) throws SQLException;
	void uploadReceipt(int r, byte[] b) throws SQLException;
	ArrayList<Reimbursements> viewPending(int u) throws SQLException;
	ArrayList<Reimbursements> viewResolved(int u) throws SQLException;
	ArrayList<User> viewInfo(int u) throws SQLException;
	void updateInfo(int u, String un, String pw, String fn, String ln, String em) throws SQLException;

}
