package com.revature.dao;

import java.util.ArrayList;

import com.revature.accounts.JointAccount;

public interface JointAccountDAO {

	public ArrayList<JointAccount> getJointAccounts();
	public void addCustomer(JointAccount employee);
	public void deleteCustomer(JointAccount employee);
	public void updateCustomer(JointAccount employee);
	
}
