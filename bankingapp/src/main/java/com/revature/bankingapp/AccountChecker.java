package com.revature.bankingapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class AccountChecker {
	public boolean canDoTransactions(String account) {
		String[] accountInfo = account.split(" ");
		if (accountInfo[4].equals("APPROVED"))
			return true;
		else
			return false;
	}
	
	public boolean isJointAccount(String account) {
		String[] accountInfo = account.split(" ");
		if (accountInfo[1].contains("joint"))
			return true;
		else
			return false;
	}
	
	public File getJointOwner(String account) {
		File file = null;
		if (isJointAccount(account)) {
			String[] accountInfo = account.split(" ");
			String jointOwner = (accountInfo[1].split("-"))[1];
			file = new File("./customerAccounts/" + jointOwner);
		}
		return file;

	}
	
	public void applyToJointOwner(String account, String primaryUser) {
		String[] accountInfo = account.split(" ");
		String[] jointOwner = accountInfo[1].split("-");
		File file = new File("./customerAccounts/"+jointOwner[1]);
		ArrayList<String> jointOwnerAccounts = new ArrayList<String>();
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while((line = br.readLine()) != null) {
				jointOwnerAccounts.add(line);
			}
			int i = 0;
			boolean added = false;
			for (String jointAccount : jointOwnerAccounts) {
				if (jointAccount.contains(accountInfo[2])) {
					accountInfo[1] = "joint-" + primaryUser;
					jointOwnerAccounts.set(i, String.join(" ", accountInfo));
					added = true;
				}
				i++;
			}
			
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
				for (String accountToWrite : jointOwnerAccounts) {
					bw.write(accountToWrite);
					bw.newLine();
					if(!added) {
						accountInfo[1] = "joint-" + primaryUser;
						bw.write(String.join(" ", accountInfo));
					}
				}
				if (jointOwnerAccounts.size() == 0) {
					accountInfo[1] = "joint-" + primaryUser;
					bw.write(String.join(" ", accountInfo));
				}
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
