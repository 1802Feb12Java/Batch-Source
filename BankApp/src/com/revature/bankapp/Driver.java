package com.revature.bankapp;


import org.json.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
public class Driver {

	static ArrayList<Customer> customers = new ArrayList<Customer>();
	static ArrayList<Account> accounts = new ArrayList<Account>();
	static ArrayList<Employee> employees = new ArrayList<Employee>();
	static ArrayList<Administrator> admins = new ArrayList<Administrator>();
	static Scanner sc = new Scanner(System.in);
	
	static String user;
	static String passw;
	static char userAuth;
	
	static void login()
	{
		System.out.println("username:");
		user = sc.next();
		System.out.println("password:");
		passw = sc.next();
		for (Customer c : customers)
		{
			if(user.equals(c.getUsername())&& passw.equals(c.getPassword()))
			{
				userAuth = 'c';
			}
		}
		for (Employee e : employees)
		{
			if(user.equals(e.getUsername())&& passw.equals(e.getPassword()))
			{
				userAuth = 'e';
			}
		}
		for (Administrator a : admins)
		{
			if(user.equals(a.getUsername())&& passw.equals(a.getPassword()))
			{
				userAuth = 'a';
			}
		}
		
	}
	static void logout()
	{
		userAuth = '0';
		user="0";
		passw="0";
		return;
		
	}
	
	static String getData()
	{
		String data = new String();
		try {
			FileReader reader = new FileReader("Data.txt");
			int i;
			while ( (i=reader.read()) != -1)
				data = data+(char)i;
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return data;
	}
	
	public static void main (String[] args)
	{
		
		customers.add(new Customer(1, "JD", "password", 1, true));
		customers.add(new Customer(2, "Mom", "Password", 2, true));
		accounts.add(new Account(1, "JD", null, true, 1000000));
		accounts.add(new Account(2, "Mom", "JD", false, 500.99));
		employees.add(new Employee(1, "employee", "emp"));
		employees.add(new Employee(2, "employee2", "emp2"));
		admins.add(new Administrator(3, "employee3", "admin"));

		Customer currentCust = null;
		Account currentAcct = null;
		Account destination = null;
		int current = 0;
		String temp;
		double amount;
		Integer source;
		Integer dest;
		
		temp = getData();
		System.out.println(temp);
		while (true)
		{
	
		System.out.println("What to do?");
		temp = sc.nextLine();
		switch(temp)
		{
		case "joint account":
			if (userAuth == 'e' || userAuth=='a')
			{
				System.out.println("what account");
				current =sc.nextInt();
				System.out.println("name");
				temp = sc.next();
				for (Account a : accounts)
				{
					//System.out.println(a.getAcctNumber());
					if (current == a.getAcctNumber())
						{
							a.setName2(temp);
							System.out.println("joined account");
							break;
						}
				}
			}
			else
			{
				System.out.println("no permission");
			}
			break;
		
		case "cancel account":
			if (userAuth=='a')
			{
				System.out.println("what account:");
				current = sc.nextInt();
				for (Account a : accounts)
				{
					//System.out.println(a.getAcctNumber());
					if (current == a.getAcctNumber())
						{
							System.out.println(a.close());
							break;
						}
				}
			}
			break;
		
		case "deny account":
			if (userAuth == 'e' || userAuth=='a')
			{
				System.out.println("what account");
				current =sc.nextInt();
				for (Account a : accounts)
				{
					//System.out.println(a.getAcctNumber());
					if (current == a.getAcctNumber())
						{
							a.setAproved(false);
							System.out.println("dennied account");
							break;
						}
				}
			}
			else {
				System.out.println("you do not have that permission");
			}
			break;
		
		case "view account":
			if (userAuth == 'e' || userAuth=='a')
			{
				System.out.println("what account");
				current =sc.nextInt();
				for (Account a : accounts)
				{
					//System.out.println(a.getAcctNumber());
					if (current == a.getAcctNumber())
						{
							System.out.println(a.toString());
							break;
						}
				}
			}
			break;
		
		case "aprove account":
			if (userAuth == 'e' || userAuth=='a')
			{
				System.out.println("what account");
				current =sc.nextInt();
				for (Account a : accounts)
				{
					//System.out.println(a.getAcctNumber());
					if (current == a.getAcctNumber())
						{
							a.setAproved(true);
							System.out.println("approved account");
							break;
						}
				}
			}
			else {
				System.out.println("you do not have that permission");
			}
			break;
		
		case "logout":
			logout();
			System.out.println(userAuth);
			break;
			
		case "login":
			login();
			System.out.println("access level: "+userAuth);
			break;
		
		case "deposit":
			if (userAuth == 'e' || userAuth=='a' || userAuth=='c')
			{
			System.out.println("amount:");
			amount=sc.nextDouble();
			currentAcct.deposit(amount);
			}
			
			break;
			
		case "withdraw":
			if (userAuth == 'e' || userAuth=='a' || userAuth=='c')
			{
			System.out.println("amount:");
			amount=sc.nextDouble();
			currentAcct.withdraw(amount);
			}
			break;
			
		case "transfer":
			if (userAuth == 'e' || userAuth=='a' || userAuth=='c')
			{
			System.out.println("source");
			source = sc.nextInt();
			for (Account a : accounts)
			{
				System.out.println(a.getAcctNumber());
				if (source.equals(a.getAcctNumber()))
					{
						currentAcct=a;
						System.out.println("found account");
					}
			}
			System.out.println("destination");
			dest = sc.nextInt();
			for (Account a : accounts)
			{
				System.out.println(a.getAcctNumber());
				if (dest==a.getAcctNumber())
					{
						destination=a;
						System.out.println("found account");
					}
			}
			System.out.println("amount");
			amount = sc.nextDouble();
			Account.transfer(currentAcct, destination, amount);
			}
			break;
			
		case "select customer":
			if (userAuth == 'e' || userAuth=='a')
			{
			System.out.println("customer id:");
			temp = sc.nextLine();
			for (Customer c : customers)
			{
				if (temp.equals(Integer.toString(c.getCustomerId())))//must cast int as string to get true.
					{
						currentCust=c;
						System.out.println("found customer");
					}
			}
			}
			else
			{
				System.out.println("no permision");
			}
			break;
			
		case "select account":
			if (userAuth == 'e' || userAuth=='a' )
			{
			System.out.println("account number:");
			temp = sc.nextLine();
			for (Account a : accounts)
			{
				//System.out.println(a.getAcctNumber());
				if (temp.equals(Integer.toString(a.getAcctNumber())))
					{
						currentAcct=a;
						System.out.println("found account");
					}
			}
			}
			else {
				System.out.println("no permission");
			}
			break;
			
		case "new customer":
			if (userAuth == 'e' || userAuth=='a' )
			{
			customers.add(new Customer(sc));
			current = customers.size() - 1;
			currentCust =customers.get(current);
			currentCust.apply(sc);
			}
			else
			{
				System.out.println("no permission");
			}
			break;
					
		case "new account":
			if (userAuth == 'e' || userAuth=='a' )
			{
			accounts.add(new Account());
			current = accounts.size() - 1;
			currentAcct = accounts.get(current);
			currentAcct.setAcctNumber(currentCust.getAccountNumber());
			currentAcct.setName1(currentCust.getUsername());
			currentAcct.setAproved(false);
			}
			else
			{
				System.out.println("no permission");
			}
			break;
			
		case "quit":
			logout();
			ArrayList<String> allData = new ArrayList<String>();
			for (Customer c : customers)
			{
				allData.add(c.toString());
			}
			for (Account c : accounts)
			{
				allData.add(c.toString());
			}
			for (Employee c : employees)
			{
				allData.add(c.toString());
			}
			for (Administrator c : admins)
			{
				allData.add(c.toString());
			}
			String dataout = new String();
			dataout = allData.toString();
			try {
				FileWriter writer = new FileWriter("Data.txt");
				for (Customer c: customers)
				{
					writer.write(dataout);
				}
				writer.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			sc.close();			
			System.out.println("bye");
			System.exit(0);
			
		default :
			System.out.println("i dont understand. try again");
			break;
			
		}
		//still in true loop
		}
		
	}
}
