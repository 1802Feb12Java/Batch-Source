package com.revature.bankingapp.users;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.revature.bankingapp.SystemDriver;

public class Users implements Serializable {

	//private Scanner sysScanner;
	//public abstract void loginUser();
	
	private ArrayList<User> users;
	private ArrayList<Account> newAccounts;
	private ArrayList<Account> approvedAccounts;
	private Users usersRef;

	public Users() {
		super();
		users = new ArrayList<User>();
		newAccounts = new ArrayList<Account>();
		approvedAccounts = new ArrayList<Account>();
		usersRef = this;
		
	}
	
	public void addUser(User user) {
		//adds new user to users arrayList
		users.add(user);
		return;
	}

	public boolean isUser(String username) {
		
		for (User obj: users) {
			
			if(obj.getUserName().equals(username)) {
				return true;
			}
			
		}
		return false;
		
	}
	
	public void accountApplication(Account newAccount, User user) {
				newAccounts.add(newAccount);
				System.out.println("Account has been added to list and is awaiting approval.");
				//TODO: REMOVE THIS, TESTING PURPOSES ONLY
				//approveAccount(newAccount, (Customer) user);
				
	}
	
	//TODO: REMOVE THIS, FOR TESTING ONLY
	public void approveAccount(Account newAccount, String username) {
		
		for (User user : this.getUsers()) {
			if(user.getUserName().equals(username)) {
				user.addToAccounts(newAccount);
			}
		}
		approvedAccounts.add(newAccount);
		newAccounts.remove(newAccount);
		this.saveUserData(this);
	}

	public ArrayList<User> getUsers() {
		return users;
	}

	public void setUsers(ArrayList<User> users) {
		this.users = users;
	}

	public ArrayList<Account> getNewAccounts() {
		return newAccounts;
	}

	public void setNewAccounts(ArrayList<Account> newAccounts) {
		this.newAccounts = newAccounts;
	}

	public ArrayList<Account> getApprovedAccounts() {
		return approvedAccounts;
	}

	public void setApprovedAccounts(ArrayList<Account> approvedAccounts) {
		this.approvedAccounts = approvedAccounts;
	}

	public static Users initializeAllUsers() {
		
		Users loadData = loadUserData();
		if(loadData == null) {
			loadData = new Users();
			saveUserData(loadData);
		}
		return loadData;
	}
	
	public static void saveUserData(Object object) {
		
		//BankingApp\\src\\
		String filename = "BankUsers.txt";
		File fileN = new File(filename);
		
		FileOutputStream file;
		try {
			file = new FileOutputStream(fileN);
			
			ObjectOutputStream output = new ObjectOutputStream(file);
			
			//TODO: add file name to init.txt
			
			
			output.writeObject(object);
			output.close();
			file.close();
			System.out.println("User data has been upated.");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public static Users loadUserData() {
		String filename = "BankUsers.txt";
		FileInputStream file;
		File fileCheck = new File(filename);
		Users object = null;
		if (!fileCheck.exists()) {
			return object;
		}
		
		try {
			file = new FileInputStream(filename);
			ObjectInputStream in = new ObjectInputStream(file);
			
			object = (Users)in.readObject();
			in.close();
			file.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
        
	}

	public void denyApproval(Account account) {
		// TODO Auto-generated method stub
		this.getNewAccounts().remove(account);
		System.out.println("Account rejected!");
	}

	//public static Users getUsersRef() {
	//	return usersRef;
	//}
	
	
}

	
