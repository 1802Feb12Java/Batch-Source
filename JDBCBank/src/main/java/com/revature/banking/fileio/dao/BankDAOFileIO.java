package com.revature.banking.fileio.dao;


import com.revature.banking.fileio.controller.BankFileIO;
import com.revature.banking.fileio.utilities.DAOUtilitiesFileIO;

public class BankDAOFileIO {

	public BankDAOFileIO() {
		// TODO Auto-generated constructor stub
	}
	public static BankFileIO getBank() {
		BankFileIO bank = (BankFileIO) DAOUtilitiesFileIO.objectRead(DAOUtilitiesFileIO.bankFile);
		return bank;
	}
	public static boolean saveBank(BankFileIO bank) {
		return DAOUtilitiesFileIO.objectWrite(DAOUtilitiesFileIO.bankFile, bank);
	}
	public boolean updateBank(BankFileIO abank) {
		return BankDAOFileIO.saveBank(abank);
	}
	public boolean addBank(BankFileIO abank) {
		return BankDAOFileIO.saveBank(abank);
	}
	public boolean deleteBank(BankFileIO abank) {
		return DAOUtilitiesFileIO.deleteObject(DAOUtilitiesFileIO.bankFile);
	}
	
}
