package com.revature.beans;

import java.util.ArrayList;

public interface User {

	public String toString();
	public String getUsername();
	public void setUsername(String username);
	public String getPassword();
	public void setPassword(String password);
	public Integer getEmpId();
	public void setEmpId(int i);
	public int getAccess();
	public String getEmail();
	public String getLastname();
	public String getFirstname();
	public ArrayList<Reimbursement> getReimbursements();
	public void setReimbursements(ArrayList<Reimbursement> reimbursements);
}