package com.revature.pt1;

import java.util.ArrayList;

public class Admin {
	private int Adminid;

	public int getAdminid() {
		return Adminid;
	}

	public void setAdminid(int adminid) {
		Adminid = adminid;
	}

	public Admin(int adminid) {
		Adminid = adminid;
	}

	@Override
	public String toString() {
		return "Admin [Adminid=" + Adminid + "]";
	}
	
	// see all accounts
	public void oversee(ArrayList<Customers> allA, ArrayList<Applier> allB ) {
		for (int x = 0; x < allA.size(); x++) {
			System.out.println(allA.get(x));
		}
		for (int x = 0; x < allB.size(); x++) {
			System.out.println(allB.get(x));
		}
	}
	
	// approve applicants
	public void approve(String name,ArrayList<Applier> all, ArrayList<Customers> cus) {
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name)) {
				cus.add(new Customers(all.get(x).getUsername(),all.get(x).getPassword(),all.get(x).getAccounttype(),all.get(x).getBalance()));
				all.remove(all.get(x));
				System.out.println("You approved "+name+" and now they are a customer");
			}	
		}
	}
	
	// deny applicants
	public void deny(String name,ArrayList<Applier> all,ArrayList<Customers> cus) {
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name)) {
				all.remove(all.get(x));
				System.out.println("You denied "+name+" and now they are no longer an applicant");
			}			
		}
	}
	
	// deposit money for a customer
	public void deposit(double amount, String name, ArrayList<Customers> all) {
		int index = 0;		// index of where the customer is
		double balance = 0;
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name)) {
				index = x;
			} 
		}
		if (amount > 0 ) {
		balance  = all.get(index).getBalance();
		all.get(index).setBalance(balance+= amount);
		System.out.println("You depositied $"+amount+" in "+all.get(index).getUsername()+"'s account and now they have a total of $"+balance);
		} else {
			System.out.println("You cant deposit negative. Try withdrawing");
		}
	}
	
	// withdraw money for a customer
	public void withdraw(double amount, String name, ArrayList<Customers> all) {
		int index = 0;		// index of where the customer is
		double balance = 0;
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name)) {
				index = x;
			} 
		}
		if (amount > 0) {
		if (all.get(index).getBalance() >= amount) {
		balance = all.get(index).getBalance();
		all.get(index).setBalance(balance -= amount);
		System.out.println("You withdrew $"+amount+" from "+all.get(index).getUsername()+"'s account leaving them with a total of $"+balance);
		} else {
			System.out.println("They dont have enough to be withdrawn");
		}
		} else {
			System.out.println("Cant be withrawing negative amount");
		}
		
	}
	
	// transfer money for a customer to his friend
	public void transfer(String user, double amount, String target, ArrayList<Customers> all) {
		int index = 0;		// index of where the customer is
		int index1 = 0;		// index of where the target account is
		double balance = 0;	// placeholder for User bank balance
		double balance1 = 0;// placeholder for Target bank balance
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(user)) {
				index = x;
			} 
		}
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(target)) {
				index1 = x;
			} 
		}
		if(amount > 0 ) {
		if (all.get(index).getBalance() > amount) {
			balance = all.get(index).getBalance();
			balance1 = all.get(index).getBalance();
			all.get(index).setBalance(balance-=amount);
			all.get(index1).setBalance(balance1+= amount);
			System.out.println(user+" transfered $"+amount+ " to "+target+" and now has "+balance);
		} else {
			System.out.println("They are too broke to help your friend");
		}} else {
			System.out.println("No negative transfer admin. How did you get this job again?");
		}
	}
	
	// cancel a customer account
	public void cancel (String name, ArrayList<Customers> all) {
		for( int x = 0; x < all.size(); x++) {
			if (all.get(x).getUsername().equals(name)) {
				all.remove(all.get(x));
				System.out.println("Account cancelled for "+name);
			} 
		}
	}
}
