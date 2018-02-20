package com.revature.bankingapp;

import java.util.Scanner;

import com.revature.bankingapp.users.*;

public class UserDriver {

	public static final void enterUser(Scanner sysScanner, User user) {
		
		user.userDriverStart(sysScanner);
		
	}
	
	public static void transfer(Account acc1, User user2, Account acc2, Double amount) {
		/*
		 * Transfer FROM acc1
		 * TO acc2
		 * 
		 * transfers from active account to new account
		 * this.acc1 - amount -> user2.acc2 + amount
		 * 
		 */
		
		withdraw(acc1, amount);
		deposit(acc2, amount);
		System.out.println("Transfer complete: $"+amount+" moved from "+acc1.getAccountName()+" to "+user2.getUserName()+"'s account "+acc2.getAccountName());
		
	}
	
	public static void deposit(Account acc, Double amount) {
		acc.setBalance(acc.getBalance() + amount);
	}
		
	public static void withdraw(Account acc, Double amount) {
		acc.setBalance(acc.getBalance() - amount);
	}
	
	
}
