package com.revature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Administrator implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String adminUsername;
	private String adminPassword;
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdminUsername() {
		return adminUsername;
	}
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}
	public String getAdminPassword() {
		return adminPassword;
	}
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}
	@Override
	public String toString() {
		return "Administrator [adminUsername=" + adminUsername + ", adminPassword=" + adminPassword + ", name=" + name
				+ "]";
	}
	public Administrator(String adminUsername, String adminPassword) {
		super();
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
	}
	
	public Administrator(String adminUsername, String adminPassword, String name) {
		super();
		this.adminUsername = adminUsername;
		this.adminPassword = adminPassword;
		this.name = name;
	}
	
	public void customerAccountStatus()
	{
		
	}
	public void removeAccount(ArrayList<Customers> custList,Administrator admin)
	{
		Scanner sc = new Scanner(System.in);
		Bank b1 = new Bank();
		String accountUsername;
		
		System.out.println("---------------------------------------------------------------------");
		System.out.println("\t\tAdministrator Remove Account View");
		System.out.println("---------------------------------------------------------------------");

		System.out.println("\tEnter customer's username to withdraw from: ");
		accountUsername = sc.next();
		
		for(int i = 0; i < custList.size(); i++)
		{
			//remove customer account
			if(custList.get(i).getCustomerUsername().equals(accountUsername))
			{
				custList.remove(i);
			}
		}
		//loop back
		BankInterface bi = new BankInterface();
		bi.adminSuccessfullSignUp(admin);
	}
	public void withdraw(ArrayList<Customers> custList,Administrator admin)
	{
		BankInterface bi = new BankInterface();
		Scanner sc = new Scanner(System.in);
		Bank b1 = new Bank();
		String accountUsername;
		double amount ;
		double newAmount;
		System.out.println("---------------------------------------------------------------------");
		System.out.println("\t\tAdministrator Withdraw View");
		System.out.println("---------------------------------------------------------------------");

		System.out.println("Enter customer's username to withdraw from: ");
		accountUsername = sc.next();
		System.out.println("Enter the amount:");
		amount = sc.nextDouble();
		if(amount < 0)
		{
			System.out.println("Invalid amount. Try again!");
			amount = sc.nextDouble();

		}
		for(int i = 0; i < custList.size(); i++)
		{
			//search for customer object in arraylist
			if(custList.get(i).getCustomerUsername().equals(accountUsername))
			{
				
				if(custList.get(i).getBalance() < amount)
				{
					System.out.println("This account doesn't have enough money");
				//	bi.adminSuccessfullSignUp();
				}
				else
				{
					Customers c2 = new Customers();
					newAmount = custList.get(i).getBalance() - amount;
					custList.get(i).setBalance(newAmount);
					c2 = custList.get(i); //save to c2 object before delete
					custList.remove(i);//delete object
					//write object to file
					b1.writeCustomerInfo(custList,c2.getCustomerUsername(), c2.getCustomerPassword(),c2.getPhone(),c2.getAddress(),c2.getName(),(c2.getBalance()));
					System.out.println("Withdraw Success! New balance" + custList.get(i).getBalance());

				}
			}
		}
		//loop back
		bi.adminSuccessfullSignUp(admin);
	}
	public void deposit(ArrayList<Customers> custList,Administrator admin)
	{
		Scanner sc = new Scanner(System.in);

		Bank b1 = new Bank();
		String accountUsername;
		double amount ;
		double newAmount;
		System.out.println("---------------------------------------------------------------------");
		System.out.println("\t\tAdministrator Deposit View");
		System.out.println("---------------------------------------------------------------------");

		System.out.println("Enter customer's username to deposit from: ");
		accountUsername = sc.next();
		System.out.println("Enter the amount:");
		amount = sc.nextDouble();
		if(amount < 0)
		{
			System.out.println("Invalid amount. Try again!");
			amount = sc.nextDouble();

		}
		for(int i = 0; i < custList.size(); i++)
		{
			//search for customer object in arraylist
			if(custList.get(i).getCustomerUsername().equals(accountUsername))
			{
				Customers c2 = new Customers();
				newAmount = custList.get(i).getBalance() + amount;
				custList.get(i).setBalance(newAmount);
				c2 = custList.get(i); //save to c2 object before delete
				custList.remove(i);//delete object
				//write object to file
				b1.writeCustomerInfo(custList,c2.getCustomerUsername(), c2.getCustomerPassword(),c2.getPhone(),c2.getAddress(),c2.getName(),(c2.getBalance()));
				System.out.println("Deposit Success! New balance" + custList.get(i).getBalance());
				}
			}
		//loop back
		BankInterface bi = new BankInterface();
		bi.adminSuccessfullSignUp(admin);

	}
	public void transfering(ArrayList<Customers> custList, Administrator admin)
	{
		Scanner sc = new Scanner(System.in);

		Bank b1 = new Bank();
		String accountUsername1,accountUsername2 ;
		double amount ;
		double newAmount;
		System.out.println("Enter customer's username to transfer from: ");
		accountUsername1 = sc.next();
		System.out.println("Enter customer's username to transfer to: ");
		accountUsername2 = sc.next();
		System.out.println("Enter the amount:");
		amount = sc.nextDouble();
		if(amount < 0)
		{
			System.out.println("Invalid amount. Try again!");
			amount = sc.nextDouble();

		}
		//search for first customer and subtract the balance
		for(int i = 0; i < custList.size(); i++)
		{
			//search for customer object in arraylist
			if(custList.get(i).getCustomerUsername().equals(accountUsername1))
			{
				
				if(custList.get(i).getBalance() < amount)
				{
					System.out.println("This account doesn't have enough money");
				}
				else
				{
					Customers c2 = new Customers();
					newAmount = custList.get(i).getBalance() - amount;
					custList.get(i).setBalance(newAmount);
					c2 = custList.get(i); //save to c2 object before delete
					custList.remove(i);//delete object
					//write object to file
					b1.writeCustomerInfo(custList,c2.getCustomerUsername(), c2.getCustomerPassword(),c2.getPhone(),c2.getAddress(),c2.getName(),(c2.getBalance()));

				}
			}	
		}
		//search for second customer and add the balance
				for(int i = 0; i < custList.size(); i++)
				{
					//search for customer object in arraylist
					if(custList.get(i).getCustomerUsername().equals(accountUsername2))
					{
						
						if(custList.get(i).getBalance() < amount)
						{
							System.out.println("This account doesn't have enough money");
						}
						else
						{
							Customers c2 = new Customers();
							newAmount = custList.get(i).getBalance() + amount;
							custList.get(i).setBalance(newAmount);
							c2 = custList.get(i); //save to c2 object before delete
							custList.remove(i);//delete object
							//write object to file
							b1.writeCustomerInfo(custList,c2.getCustomerUsername(), c2.getCustomerPassword(),c2.getPhone(),c2.getAddress(),c2.getName(),(c2.getBalance()));

						}
					}	
				}
				//loop back
				BankInterface bi = new BankInterface();
				bi.adminSuccessfullSignUp(admin);
	}
}
