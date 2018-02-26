package com.revature.banking.dao;

import java.io.File;

import com.revature.banking.controller.Bank;
import com.revature.banking.model.Account;
import com.revature.banking.model.Admin;
import com.revature.banking.utilities.DAOUtilities;

public class BankDAO {

	public BankDAO() {
		// TODO Auto-generated constructor stub
	}
	public static Bank getBank() {
		Bank bank = (Bank) DAOUtilities.objectRead(DAOUtilities.bankFile);
		return bank;
	}
	public static boolean saveBank(Bank bank) {
		return DAOUtilities.objectWrite(DAOUtilities.bankFile, bank);
	}
	public boolean updateBank(Bank abank) {
		return BankDAO.saveBank(abank);
	}
	public boolean addBank(Bank abank) {
		return BankDAO.saveBank(abank);
	}
	public boolean deleteBank(Bank abank) {
		return DAOUtilities.deleteObject(DAOUtilities.bankFile);
	}
	
}
