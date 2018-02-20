package com.revature;

import java.util.ArrayList;
import java.util.Scanner;

public class BankInterface {

	public static void art()
	{
		System.out.println("\r\n" + 
				"\r\n" + 
				"(O))  ((O)    .-.   \\\\\\  /// _      wWw ()_()  ___        \\\\\\  ///       _  \r\n" + 
				" ||    ||   c(O_O)c ((O)(O))/||_    (O)_(O o) (___)__ /)  ((O)(O))(OO) .' ) \r\n" + 
				" || /\\ ||  ,'.---.`, | \\ ||  /o_)   / __)|^_\\ (O)(O)(o)(O) | \\ ||  ||_/ .'  \r\n" + 
				" ||//\\\\|| / /|_|_|\\ \\||\\\\|| / |(\\  / (   |(_))/  _\\  //\\\\  ||\\\\||  |   /    \r\n" + 
				" / /  \\ \\ | \\_____/ ||| \\ | | | ))(  _)  |  / | |_))|(__)| || \\ |  ||\\ \\    \r\n" + 
				"( /    \\ )'. `---' .`||  || | |//  \\ \\_  )|\\\\ | |_))/,-. | ||  || (/\\)\\ `.  \r\n" + 
				" )      (   `-...-' (_/  \\_)\\__/    \\__)(/  \\)(.'-'-'   ''(_/  \\_)     `._) \r\n" + 
				"\r\n" + 
				"");
	}
	public static void exitView()
	{
		art();
		System.out.println("-------------------------------------------------------------");
		System.out.println("\t\tThank you for using WonderBank!!");
		System.out.println("-------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------");

	}
	/*
	 * Employee Portal
	 */
	public static void employeeInterface()
	{
		Scanner sc = new Scanner(System.in);
		int input ;
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");
		System.out.println("\t\tEmployee Portal");
		System.out.println("\t1.Employee login.");
		System.out.println("\t2.Administrator login.");
		System.out.println("\t3.Go back to main screen.");
		System.out.println("\t4.Exit.");


		System.out.println("----------------------------------------------------------");

		input = sc.nextInt();
		switch(input)
		{
		case 2:
			adminMainPage();
			break;

		case 1:
			employeeLoginInterface();
			break;
		case 3:
			mainScreen();
			break;
		case 4: 
			exitView();
			break;
		default:
			break;
		}
	}
	public static void employeeLoginInterface()
	{
		AccountCreation ac = new AccountCreation();
		
		Scanner sc = new Scanner(System.in);
		int input ;
		System.out.println("----------------------------------------------------");
		System.out.println("\t\tEmployee portal");
		System.out.println("----------------------------------------------------");
		System.out.println("----------------------------------------------------");

		System.out.println("\t1. Employee sign up");
		System.out.println("\t2. Employee login");
		System.out.println("\t3. Go back to employee portal");
		System.out.println("\t4. Exit");

		input = sc.nextInt();
		switch(input)
		{
		case 2:
			ac.employeeLogin();
			break;
		case 1:
			ac.createEmployeeAccount();
			break;
		case 3:
			employeeInterface();
			break;
		case 4: 
			exitView();
			break;
		default:
			break;
		}
	}

	public  void employeeSuccessfulSignUp(Employees emp)
	{
		Scanner sc = new Scanner(System.in);
		int input ;
		Bank b1 = new Bank();
		
		ArrayList <Customers> custList = b1.readCustomerInfo();
		System.out.println("----------------------------------------------------------");
		System.out.println("\t\t Employee Functionality View");
		System.out.println("\tHi " + emp.getName());
		System.out.println("\t1. Check customer's balance");
		System.out.println("\t2. Check customer's personal information ");
		System.out.println("\t3. Check customer's account information");
		System.out.println("\t4. Go back to employee view.");
		System.out.println("\t5. Exit");

		input = sc.nextInt();
		switch(input)
		{
		case 2:
			emp.checkPersonalInformation(custList,emp);
			break;
		case 1:
			emp.checkAccountBalance(custList,emp);
			break;
		case 3:
			emp.checkAccountInformation(custList,emp);
			break;
		case 4:
			employeeLoginInterface();
			break;
		case 5:
			exitView();
			break;
		default:
			break;
		}

	}
	
	
	/*
	 * Admin Portal
	 */
	public static void adminMainPage()
	{
		Scanner sc = new Scanner(System.in);
		int input ;
		Bank b1 = new Bank();
		AccountCreation ac = new AccountCreation();
		ArrayList <Customers> custList = b1.readCustomerInfo();
		System.out.println("----------------------------------------------------------");
		System.out.println("-----------------Admin Portal------------------");
		System.out.println("\t1. Administrator sign up");
		System.out.println("\t2. Adminstrator log in ");
		System.out.println("\t3. Go back to employee page");
		System.out.println("\t4. Exit");
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");

		input = sc.nextInt();
		switch(input)
		{
		case 2:
			
			ac.adminLogin();
			break;
		case 1:
			ac.createAdminAccount();
			break;
		case 3:
			employeeInterface();
			break;
		case 4:
			exitView();
			break;
		default:
			break;
		}
	}
	
	public void adminSuccessfullSignUp(Administrator admin)
	{
		Scanner sc = new Scanner(System.in);
		int input ;
		Bank b1 = new Bank();
		
		ArrayList <Customers> custList = b1.readCustomerInfo();
		System.out.println("------------------------------------------");
		System.out.println("\t\tAdmin Interface");
		System.out.println("------------------------------------------");

		System.out.println("\tHi " + admin.getName());
		System.out.println("\t1. Withdraw");
		System.out.println("\t2. Deposit ");
		System.out.println("\t3. Transfer");
		System.out.println("\t4. Remove Account");
		System.out.println("\t0. Exit");


		input = sc.nextInt();
		switch(input)
		{
		case 1:
			admin.withdraw(custList,admin);
			break;
		case 2:
			admin.deposit(custList,admin);
			break;
		case 3:
			admin.transfering(custList,admin);
			break;

		case 4:
			admin.removeAccount(custList,admin);
			break;

		case 0:
			exitView();
			break;
		default:
			break;
		}

	}

	/*
	 * Customer Portal
	 */
	public void customerSuccessfulLoginInterface(Customers cust)
	{
		System.out.println("---------------------------------------------------");
		System.out.println("\t\tCustomer Portal");
		System.out.println("---------------------------------------------------");
		System.out.println("\tHi " + cust.getName());
		System.out.println("\tCurrent Balance:  " + cust.getBalance());

		Scanner sc = new Scanner(System.in);
		int input ;
		System.out.println("1.Transfer");
		System.out.println("2. Widthraw");
		System.out.println("3. Deposit.");
		System.out.println("4. Go back.");
		System.out.println("5. Exit.");

		input = sc.nextInt();
		switch(input)
		{
		case 2:
			withdrawInterface(cust);
			break;
		case 1:
			tranfer(cust);
			break;
		case 3:
			depositInterface(cust);
			break;
		case 4:
			customerInterface();
			break;
		case 5:
			exitView();
			break;
		default:
			break;
			
		}
	}
	
	public void tranfer(Customers cust)
	{
		Scanner sc = new Scanner(System.in);
		Bank b1 = new Bank();
		String user;
		double input ;
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");

		System.out.println("\t\tTransfer Interface");
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");

		System.out.println("Who do you want to transfer: enter a username: ");
		user = sc.next();
		System.out.println("Enter how much you want to tranfer: ");
		input = sc.nextDouble();
		if(cust.transfer(input))
		{
			ArrayList<Customers> custList = b1.readCustomerInfo();
			Customers transferCustomer = new Customers();
			
			//update balance for tranferred customer
			transferCustomer = returnTransferCustomer(custList,user);
			double bal = transferCustomer.getBalance();
			bal += input;
			for(int i = 0; i < custList.size(); i++)
			{
				//remove current list item
				if(custList.get(i).getCustomerUsername().equals(transferCustomer.getCustomerUsername()))
				{
					custList.remove(i);
				}
			}
			//add updated balance for the tranfered customer
			b1.writeCustomerInfo(custList,transferCustomer.getCustomerUsername(), transferCustomer.getCustomerPassword(),transferCustomer.getPhone(),transferCustomer.getAddress(),transferCustomer.getName(),bal);

			//change output
			for(int i = 0; i < custList.size(); i++)
			{
				//remove current list item
				if(custList.get(i).getCustomerUsername().equals(cust.getCustomerUsername()) && custList.get(i).getCustomerPassword().equals(cust.getCustomerPassword()))
				{
					custList.remove(i);
				}
			}
			//add new one
			b1.writeCustomerInfo(custList,cust.getCustomerUsername(), cust.getCustomerPassword(),cust.getPhone(),cust.getAddress(),cust.getName(),(cust.getBalance()));
		}
		customerSuccessfulLoginInterface(cust);

	}
	public Customers returnTransferCustomer(ArrayList<Customers> list,String name)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getCustomerUsername().equals(name))
			{
				return list.get(i);
			}
		}
		return null;

	}

	public void withdrawInterface(Customers cust)
	{
		Scanner sc = new Scanner(System.in);
		Bank b1 = new Bank();
		double input ;
		System.out.println("----------------------------------------------------------");
		System.out.println("\t\tWithrawing Interface");
		System.out.println("----------------------------------------------------------");
		System.out.println("----------------------------------------------------------");

		System.out.println("Enter how much you want to withdraw: ");
		input = sc.nextDouble();
		//if withdraw succesfully, update current object's balance
		if(cust.withdraw(input))
		{
			ArrayList<Customers> custList = b1.readCustomerInfo();
			for(int i = 0; i < custList.size(); i++)
			{
				//remove current list item
				if(custList.get(i).getCustomerUsername().equals(cust.getCustomerUsername()) && custList.get(i).getCustomerPassword().equals(cust.getCustomerPassword()))
				{
					custList.remove(i);
				}
			}
			//add new one
			b1.writeCustomerInfo(custList,cust.getCustomerUsername(), cust.getCustomerPassword(),cust.getPhone(),cust.getAddress(),cust.getName(),(cust.getBalance()));

			customerSuccessfulLoginInterface(cust);

		}
		else
			customerSuccessfulLoginInterface(cust);
	}
	public void depositInterface(Customers cust)
	{
		Bank b1 = new Bank();
		Scanner sc = new Scanner(System.in);
		double input ;
		System.out.println("----------------------------------------------------------");

		System.out.println("\t\tDeposit Interface");
		System.out.println("----------------------------------------------------------");

		System.out.println("Enter how much you want to deposit: ");
		input = sc.nextDouble();
		cust.deposit(input);
		ArrayList<Customers> custList = b1.readCustomerInfo();
		for(int i = 0; i < custList.size(); i++)
		{
			//remove current list item
			if(custList.get(i).getCustomerUsername().equals(cust.getCustomerUsername()) && custList.get(i).getCustomerPassword().equals(cust.getCustomerPassword()))
			{
				custList.remove(i);
				
			}
		}
		//add new one
		b1.writeCustomerInfo(custList,cust.getCustomerUsername(), cust.getCustomerPassword(),cust.getPhone(),cust.getAddress(),cust.getName(),(cust.getBalance()));
		customerSuccessfulLoginInterface(cust);

	}
	public static void customerInterface()
	{
		AccountCreation ac = new AccountCreation();
		Bank b1 = new Bank();
		Scanner sc = new Scanner(System.in);
		int input ;
		System.out.println("---------------------------------------------------------");
		System.out.println("---------------------------------------------------------");
		System.out.println("\t\t Customer View");
		System.out.println("1. Register a customer account");
		System.out.println("2. Customer Login");
		System.out.println("3. Go Back to Main View");
		System.out.println("4. Exit");

		System.out.println("---------------------------------------------------------");
		System.out.println("---------------------------------------------------------");

		input = sc.nextInt();
		switch(input)
		{
		case 2:
			ac.customerLogin();
			break;

		case 1:
			ac.createCustomerAccount();
			break;
		case 3:
			mainScreen();
			break;
		default:
			break;
		}
		
	}

	public static void mainScreen()
	{
		Scanner sc = new Scanner(System.in);
		int input ;	
		System.out.println("-----------------------------------------------------");
		System.out.println("-------------Welcome to WonderBank-----------------");

		System.out.println("1. Customer account");
		System.out.println("2. Employee portal");
		System.out.println("3. Exit");

		System.out.println("-----------------------------------------------------");
		input = sc.nextInt();
		switch(input)
		{
		case 2:
			employeeInterface();
			break;

		case 1:
			customerInterface();
			break;
		case 3:
			exitView();
			break;
		default:
			break;
		}
	}
	

}
