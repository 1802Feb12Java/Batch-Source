package com.revature.beans;

import java.io.Serializable;
import java.util.Scanner;


public class Customer implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private boolean approved = false;
	private static double accBalance;
	//private boolean userExists;
	
	public void runCustomerApp() {
		Scanner sc = new Scanner(System.in);
		//if approved == true then run code below
		while(true) {
			System.out.println("\nEnter <withdraw>, <deposit>, or <transfer> to make changes to your account"
					+ "\n Or type <logout> to exit");
			
			String action = sc.next();
			if(action.equals("withdraw")) {
				System.out.print("How much do you want to take out?   $");
				double withdraw = sc.nextDouble();
				while(true) {
					if(withdraw>Customer.getAccBalance()) {
						System.out.print("Invalid amount. Please enter value < your account balance \n$");
						withdraw = sc.nextDouble();
					}else if(withdraw<0) {
						System.out.print("Invalid amount. Please enter value >= 0 \n$");
						withdraw = sc.nextDouble();
					} else {
						break;
					}
				}
				Customer.withdrawMoney(withdraw);
				continue;
				
			} else if(action.equals("deposit")) {
				System.out.print("How much do you want to deposit?   $");
				double deposit = sc.nextDouble();
				while(deposit < 0) {
					System.out.print("Invalid amount. Please enter value >= 0 \n$");
					deposit = sc.nextDouble();
				}
				Customer.depositMoney(deposit);
				continue;
				
			} else if(action.equals("transfer")) {
				//transfer money to different account
				continue;
				
			} else if(action.equals("logout")) {
				break;
			} else {
				System.out.println("Invalid input. Try Again.");
				action = sc.next();
			}
			return;
		}
		sc.close();
	}
	
	public Customer(String username, String password) {
		super();
		this.username = username;
		this.password = password;
		//this.accBalance = accBalance;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String passoword) {
		this.password = passoword;
	}

	public static double getAccBalance() {
		return accBalance;
	}

	public void setAccBalance(double accBalance) {
		this.accBalance = accBalance;
	}
	

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}


	
	@Override
	public String toString() {
		return "Customer [username=" + username + ", password=" + password + ", accBalance=" + accBalance + ", apprroved=" + approved + "]";
	}
	
	public static void withdrawMoney(double d) {
		accBalance -= d;
		System.out.println("The new total in your account is : $" + (accBalance));
	}
	
	public static void depositMoney(double d) {
		accBalance += d;
		System.out.println("The new total in your account is : $" + (accBalance));
	}
	
	public static void transferMoney() {
		
	}
	

	
	
	

}
