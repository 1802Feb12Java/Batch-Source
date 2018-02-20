package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;



public class AccountCreation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Admin's Credential
	 */
	/**/
	//check for user input
	public String checkInpuString(String str)
	{
		String input;
		Scanner sc = new Scanner(System.in);
		if(str.length() == 0)
		{
			System.out.println("Invalid input! Try again!");
			input = sc.nextLine();
			return input;

		}
		return str;
	}
	public void createAdminAccount()
	{
		Bank b1 = new Bank();
		System.out.println("--------------------------------------------------");
		System.out.println("--------------------------------------------------");

		System.out.println("\t\tAdmin Register Interface");
		System.out.println("--------------------------------------------------");
		//retrieve database and 
		ArrayList<Administrator> adminList = b1.readAdminInfo();
		String user,pass,name;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a username: ");
		user = sc.nextLine();
		user = checkInpuString(user);
		if(checkAdminUsername(user,adminList))
		{
			System.out.println("Username is unavailable. Try another one.");
			user = sc.next();
		}
		System.out.println("Please enter a password: ");
		pass = sc.nextLine();
		pass = checkInpuString(pass);

		System.out.println("Please enter your name: ");
		name = sc.nextLine();
		name = checkInpuString(name);

		

		b1.writeAdminInfo(adminList,user, pass,name);
		//update list
		adminList = b1.readAdminInfo();
		for(Object obj : adminList)
		{
			System.out.println(obj);
		}
		System.out.println("Adminstrator Registration Successfully");
		BankInterface bi = new BankInterface();
		bi.adminMainPage();


	}
	public void adminLogin()
	{
		Bank b1 = new Bank();
		System.out.println("------------------------------------------------");
		System.out.println("\t\tAdmin Login Interface");
		System.out.println("------------------------------------------------");

		ArrayList<Administrator> list = b1.readAdminInfo();
		BankInterface bi = new BankInterface();
		Scanner sc = new Scanner(System.in);
		String user,pass;
		System.out.println("Username: ");
		user = sc.nextLine();
		System.out.println("Password: ");
		pass = sc.nextLine();
		//sc.close();
		
			if(checkAdminLogin(user,pass,list))
			{
			//	System.out.println("Login success!!");
					
			}
			else
		{
			System.out.println("Login Failed. Try Again!");
			adminLogin();
			
		}
	
	}
	public boolean checkAdminLogin(String user, String pass, ArrayList <Administrator> admin)
	{
		BankInterface bi = new BankInterface();

		for(int i = 0; i < admin.size(); i++)
		{
			if(admin.get(i).getAdminUsername().equals(user) && admin.get(i).getAdminPassword().equals(pass))
			{
				bi.adminSuccessfullSignUp(admin.get(i));
				return true;
			}
		}	
		return false;	
	}	
	public boolean checkAdminUsername(String user, ArrayList <Administrator> admin)
	{
		for(int i = 0; i < admin.size(); i++)
		{
			if(admin.get(i).getAdminUsername().equals(user))
			{
				return true;
			}
		}	
		return false;	
	}
	 
	 /*
	 * Employees' Credential
	 */
	public void createEmployeeAccount()
	{
		Bank b1 = new Bank();
		
		//retrieve database and 
		ArrayList<Employees> employeeList = b1.readEmployeeInfo();
		String user,pass,name;
		Scanner sc = new Scanner(System.in);
		System.out.println("----------------------------------------------------");
		System.out.println("\t\tEmployee Register");
		System.out.println("----------------------------------------------------");
		System.out.println("----------------------------------------------------");

		System.out.println("Please enter a username: ");
		user = sc.nextLine();
		user = checkInpuString(user);

		if(checkEmployeeUsername(user,employeeList))
		{
			System.out.println("Username is unavailable. Try another one.");
			user = sc.nextLine();
		}
		System.out.println("Please enter a password: ");
		pass = sc.nextLine();
		pass = checkInpuString(pass);

		System.out.println("Please enter your name: ");
		name = sc.nextLine();
		name = checkInpuString(name);

		

		b1.writeEmployeeInfo(employeeList,user, pass,name);
		//update list
		employeeList = b1.readEmployeeInfo();
		for(Object obj : employeeList)
		{
			System.out.println(obj);
		}
		System.out.println("Employee Registration Successfully");
		BankInterface bi = new BankInterface();
		bi.employeeInterface();

	}
	public void employeeLogin()
	{
		Bank b1 = new Bank();

		ArrayList<Employees> list = b1.readEmployeeInfo();
		BankInterface bi = new BankInterface();
		Scanner sc = new Scanner(System.in);
		String user,pass;
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("\t\tEmployee Login Screen");
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");

		System.out.println("Username: ");
		user = sc.next();
		System.out.println("Password: ");
		pass = sc.next();
		//sc.close();
		
			if(checkEmployeeLogin(user,pass,list))
			{
			//	System.out.println("Login success!!");
					
			}
			else
		{
			System.out.println("Login Failed. Try Again!");
			employeeLogin();
		}
	
	}
	public boolean checkEmployeeLogin(String user, String pass, ArrayList <Employees> employee)
	{
		BankInterface bi = new BankInterface();

		for(int i = 0; i < employee.size(); i++)
		{
			if(employee.get(i).getEmployeeUsername().equals(user) && employee.get(i).getEmployeePassword().equals(pass))
			{
				bi.employeeSuccessfulSignUp(employee.get(i));
				return true;
			}
		}	
		return false;	
	}	
	public boolean checkEmployeeUsername(String user, ArrayList <Employees> emp)
	{
		for(int i = 0; i < emp.size(); i++)
		{
			if(emp.get(i).getEmployeeUsername().equals(user))
			{
				return true;
			}
		}	
		return false;	
	}
	/*
	 * Customers' credentials
	 */
	public void createCustomerAccount()
	{
		Bank b1 = new Bank();
		
		//retrieve database and 
		ArrayList<Customers> custList = b1.readCustomerInfo();
		String user,pass,phone,address, name;
		Scanner sc = new Scanner(System.in);
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("\t\tCustomer Registration");
		System.out.println("----------------------------------------------------------------------------");
		System.out.println("----------------------------------------------------------------------------");

		System.out.println("Please enter a username: ");
		user = sc.nextLine();
		user = checkInpuString(user);

		if(checkUsername(user,custList))
		{
			System.out.println("Username is unavailable. Try another one.");
			user = sc.next();
		}
		System.out.println("Please enter a password: ");
		pass = sc.nextLine();
		pass = checkInpuString(pass);

		System.out.println("Please enter your name: ");
		name = sc.nextLine();
		name = checkInpuString(name);

		System.out.println("Please enter a phone number: ");
		phone = sc.nextLine();
		phone = checkInpuString(phone);


		System.out.println("Please enter an address : ");
		address = sc.nextLine();
		address = checkInpuString(address);


		b1.writeCustomerInfo(custList,user, pass,phone,address,name,0);
		//update list
		custList = b1.readCustomerInfo();
		System.out.println("Customer Registration Successfully");
		BankInterface bi = new BankInterface();
		bi.customerInterface();

	}
	
	public void customerLogin()
	{
		Bank b1 = new Bank();

		ArrayList<Customers> list = b1.readCustomerInfo();
		BankInterface bi = new BankInterface();
		Scanner sc = new Scanner(System.in);
		String user,pass;
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");

		System.out.println("\t\tCustomer Login View");
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");

		System.out.println("Username: ");
		user = sc.nextLine();
		System.out.println("Password: ");
		pass = sc.nextLine();
		//sc.close();
		
			if(checkLogin(user,pass,list))
			{
			//	System.out.println("Login success!!");
					
			}
			else
		{
			System.out.println("Login Failed. Try Again!");
			customerLogin();
		}
	
	}
	
	public boolean checkLogin(String user, String pass, ArrayList <Customers> customerInfo)
	{
		BankInterface bi = new BankInterface();

		for(int i = 0; i < customerInfo.size(); i++)
		{
			if(customerInfo.get(i).getCustomerUsername().equals(user) && customerInfo.get(i).getCustomerPassword().equals(pass))
			{
				bi.customerSuccessfulLoginInterface(customerInfo.get(i));
				return true;
			}
		}	
		return false;	
	}	
	public boolean checkUsername(String user, ArrayList <Customers> customerInfo)
	{
		for(int i = 0; i < customerInfo.size(); i++)
		{
			if(customerInfo.get(i).getCustomerUsername().equals(user))
			{
				return true;
			}
		}	
		return false;	
	}
}
