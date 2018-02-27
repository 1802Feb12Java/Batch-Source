package JDBCBank.JDBCBank.consoleinterface;

import java.util.ArrayList;
import java.util.Scanner;

import JDBCBank.JDBCBank.customer.bean.Customer;
import JDBCBank.JDBCBank.customer.bean.CustomerDaoImpl;
import JDBCBank.JDBCBank.customeraccount.bean.CustomerAccount;
import JDBCBank.JDBCBank.customeraccount.bean.CustomerAccountDaoImpl;
import JDBCBank.JDBCBank.oraclejdbc.SelectQueries;

public class CustomerGUI {
	//----------------------Display Customer Main View-------------------------------------

	public void displayCustomerGUI()
	{
		Scanner sc = new Scanner(System.in);
		int input ;
		System.out.println("============ Customer Portal ===================");
		System.out.println("\t\t1.Login");
		System.out.println("\t\t2.Register");
		System.out.println("\t\t0.Exit");
		while(!sc.hasNextInt())//check if user input is int or not
		{
			System.out.println("Invalid input. Enter a number.");
			sc.next(); 
		}
		input = sc.nextInt();
		switch (input)
		{
			case 1: 
				displayLoginView();
				break;
			case 2:
				displayRegisterView();
				break;
			case 0:
				exitView();
				break;
			default:
				System.out.println("Invalid input. Enter 1, 2, or 0");
				displayCustomerGUI();//reloop if not put in 1,2,0
				break;
		}
		
	}
	public void exitView()
	{
		System.out.println("=================Exit======================");
		System.out.println("\tThank you for using our bank!!!!");
		System.exit(0);//exit console
	}
	//----------------------Display Login View-------------------------------------
	public void displayLoginView()
	{
		Scanner sc = new Scanner(System.in);
		String user,pass;
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		System.out.println("============ Customer Login ===================");
		do
		{
			System.out.println("\t\tEnter username: ");
			user = sc.nextLine();

		} while(!checkString(user));
		do
		{
			System.out.println("\t\tEnter password:");
			pass = sc.nextLine();

		} while(!checkString(user));
		
		if(cdi.matchCustomer(pass, user))
		{
			System.out.println("Login Successfully");
			Customer c = cdi.getCustomer(user);
			displayLoginSuccess(c);
		}
		else
		{
			System.out.println("Wrong password/username. Try again!");
			displayLoginView();//loop back if input is wrong
		}		
	}
	//function to use for input string
	public boolean checkString(String check)
	{
		if (check.length() == 0)
		{
			System.out.println("Can't enter an empty string");
			return false;
		}
		return true;
	}
//---------Display register view -----------------------------------------
	public void displayRegisterView()
	{
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		Scanner sc = new Scanner(System.in);
		int input ;
		String username, password, fname, lname, addr, phone;
		System.out.println("============ Customer Register ===================");
		do
		{
			System.out.println("\t\tEnter a username: ");
			username = sc.nextLine();
			while(cdi.matchUsername(username))//check for available username
			{
				System.out.println("Username is taken. Try again!");
				username = sc.nextLine();

			}
		} while(!checkString(username));
		do
		{
			System.out.println("\t\tEnter a password: ");
			password = sc.nextLine();

		} while(!checkString(password));
		do
		{
			System.out.println("\t\tEnter a last name: ");
			lname = sc.nextLine();

		} while(!checkString(lname));
		do
		{
			System.out.println("\t\tEnter a first name: ");
			fname = sc.nextLine();

		} while(!checkString(fname));
		do
		{
			System.out.println("\t\tEnter a address: ");
			addr = sc.nextLine();

		} while(!checkString(addr));
		do
		{
			System.out.println("\t\tEnter a phone number: ");
			phone = sc.nextLine();

		} while(!checkString(phone));
		//execute statement, insert new customer information to customer db
		SelectQueries sq = new SelectQueries();
		sq.insertCustomerTableQuery(username, password, fname, lname, addr, phone);
		displayCustomerGUI();
	}
	//----------------------------------------------- display customer account -------------------------------
	public void displayLoginSuccess(Customer c)
	{
		Scanner sc = new Scanner(System.in);
		ArrayList<CustomerAccount> caList = new ArrayList();
		CustomerAccountDaoImpl ca = new CustomerAccountDaoImpl();
		CustomerAccount cab = new CustomerAccount();
		cab = ca.getCustomerAccount(c.getcId());
		caList = ca.getAllCustomerAccount(c.getcId());
		
		int input ;
		System.out.println("============ Customer Logged in  ===================");
		System.out.println("Hello " + c.getFname() + " " + c.getLname());
		if (caList.isEmpty())
		{
			System.out.println("You currently don't have any bank account");
		}
		else
		{
			for (int i = 0; i < caList.size(); i++)
			{
				System.out.println("Bank account id: " + caList.get(i).getCaid() + "\tBalance: " + caList.get(i).getBalance());
			}
		}
		System.out.println("\t\tWhat do you want to do today?");
		System.out.println("\t1. Create new bank account");
		System.out.println("\t2. Deposit");
		System.out.println("\t3. Withdraw");
		System.out.println("\t4. Delete Bank Account");

		System.out.println("\t5. Back");
		while (!sc.hasNextInt())
		{
			System.out.println("Invalid input. Enter an integer please");
			sc.next(); 

		}
		input = sc.nextInt();
		switch(input) {
		case 1:
			createBankAccounts(cab,c );
			break;
		case 2:
			deposit(cab,c);
			break;
		case 3:
			withdraw(cab,c);
			break;
		case 4:
			deleteBankAccount(cab,c);
			break;
		case 5:
			displayCustomerGUI();//go back to main gui
			break;
		default: //reloop if user inputs wrong number
			System.out.println("Please enter option 1,2,3,4,5");
			displayLoginSuccess(c);
		}
	}
	public void deleteBankAccount(CustomerAccount ca, Customer c)
	{
		SelectQueries sq = new SelectQueries();
		Scanner sc = new Scanner(System.in);
		int input;
		ArrayList<CustomerAccount> caList = new ArrayList();
		CustomerAccountDaoImpl cadi = new CustomerAccountDaoImpl();
		ca = cadi.getCustomerAccount(c.getcId());
		caList = cadi.getAllCustomerAccount(c.getcId());
		System.out.println("---------------------------Bank Account Deletion--------------------------");
		System.out.println("\tEnter a bank account id: ");
		while(!sc.hasNextInt())
		{
			System.out.println("Please enter id in number.");
			sc.nextLine();
			
		}
		input = sc.nextInt();
		if(compareBankId(input, caList))//if bank account is valid
		{
			ca = cadi.getMatchingAccountNumber(input, c.getcId());//pull fresh
			if(ca.getBalance() == 0)
			{
				sq.removeCustomerAccount(ca.getCaid());
				System.out.println("Account removed successfully.");
				displayLoginSuccess(c);//loop back

			}
			else
			{
				System.out.println("There is a remaining balance in your account.\n Please withdraw the remaining balance");
				displayLoginSuccess(c);//loop back
			}
		}
		else
		{
			System.out.println("Bank account id is not found!");
			displayLoginSuccess(c);//loop back

		}
		
		
	}

	//create bank account
	public void createBankAccounts(CustomerAccount ca, Customer c)
	{
		Scanner sc = new Scanner(System.in);
		double amount ;
		System.out.println("---------------------------Bank Account Creation--------------------------");
		System.out.println("\tHow much is your initial deposit?");
		while(!sc.hasNextDouble())
		{
			System.out.println("Please enter an amount in number. 0 is excepted.");
			sc.nextLine();
			
		}
		amount = sc.nextDouble();
		SelectQueries sq = new SelectQueries();
		sq.insertCustomerAccountTableQuery(amount, c.getcId());
		System.out.println("Bank account created.");
		displayLoginSuccess(c);
		
	}
	//------------------------------------Deposit View
	public void deposit(CustomerAccount ca, Customer c)
	{
		ArrayList<CustomerAccount> caList = new ArrayList();
		Scanner sc = new Scanner(System.in);
		int input;
		double amount;
		SelectQueries sq = new SelectQueries();

		CustomerAccountDaoImpl cadi = new CustomerAccountDaoImpl();
	//	ca = cadi.getCustomerAccount(c.getcId());//pull everything fresh
		caList = cadi.getAllCustomerAccount(c.getcId());
		
		System.out.println("------------------------Deposit View----------------------");
		System.out.println("Enter a bank account id to deposit");
		while(!sc.hasNextInt())//check for integer input
		{
			System.out.println("Invalid input. Type in a number");
			sc.next(); 	
		}
		input = sc.nextInt(); //get input for bank account id
		
		if(compareBankId(input,caList))//if bank account is valid
		{
			ca = cadi.getMatchingAccountNumber(input, c.getcId());//new function 

			System.out.println("Enter an amount");
			while(!sc.hasNextDouble())//check for number input
			{
				System.out.println("Enter a valid number");
				sc.next();//flushed out old input
			}
			amount = sc.nextDouble();
			while(!ca.checkDepositBalance(amount))//check if amount is valid
			{
			
				System.out.println("Try Again");
				amount = sc.nextDouble();

			}
			//if balance is valid
				double newBalance = ca.getBalance() + amount;// update to new balance
				//execute update query
				sq.updateCustomerAccountTableQuery(newBalance, ca.getCaid()); 
				System.out.println("Deposit Successful");
				//CustomerAccountDaoImpl cadi= new CustomerAccountDaoImpl();
				
				ca = cadi.getMatchingAccountNumber(input, c.getcId());//new function 
				System.out.println("Account new balance is " + ca.getBalance());
				System.out.println("Do you want to continue? y/n ");
				String y_n ;
					y_n = sc.next();
			 while (!y_n.equals("Y") ||!y_n.equals("y") || !y_n.equals("n") ||!y_n.equals("N"))
					 {
					if (y_n.equals("y") ||y_n.equals("Y"))
							{
						deposit(ca,c);
					}
					else if(y_n.equals("n") || y_n.equals("N")) {
						displayLoginSuccess(c);
					}
					else
					{
						System.out.println("Type y/n");	
						y_n = sc.nextLine();
					}
				}
			
			
		}
		//happens when bank account id is not found
		else {
			System.out.println("Bank account id is not found. Try again");
			deposit(ca,c);
		}
	}


	//---------------------------------check if bank account is valid
	public boolean compareBankId(int caid, ArrayList<CustomerAccount> caList)
	{
		for (int i = 0; i < caList.size(); i++)
		{
			if (caList.get(i).getCaid() == caid)
				return true;
		}
		return false;
	}
	//--------------------------------------------Withdraw View
	public void withdraw(CustomerAccount ca, Customer c)
	{
		ArrayList<CustomerAccount> caList = new ArrayList();
		Scanner sc = new Scanner(System.in);
		int input;
		double amount;
		SelectQueries sq = new SelectQueries();

		CustomerAccountDaoImpl cadi = new CustomerAccountDaoImpl();
		//ca = cadi.getCustomerAccount(c.getcId());
		caList = cadi.getAllCustomerAccount(c.getcId());
		System.out.println("------------------------Withdraw View----------------------");
		System.out.println();
		for (int i = 0; i < caList.size(); i++)
		{
			System.out.println("Bank account id: " + caList.get(i).getCaid() + "\tBalance: " + caList.get(i).getBalance());
		}
		System.out.println("Enter a bank account id to withdraw");
		while(!sc.hasNextInt())//check for int input
		{
			System.out.println("Invalid input. Type in a number");
			sc.next();
		}
		input = sc.nextInt();
		if(compareBankId(input, caList))//if bank account is valid
		{
			ca = cadi.getMatchingAccountNumber(input, c.getcId());//new function 
			System.out.println("Enter an amount: ");
			while(!sc.hasNextDouble())//check for a number input
			{
				System.out.println("Enter a valid number: ");
				sc.next();
			}
			amount = sc.nextDouble();
			while(!ca.checkWithdrawBalance(amount))
			{
				System.out.println("Try Again");
				amount = sc.nextDouble();
			}
			//execute update statement
			double newBalance = ca.getBalance() - amount;//update balance to a new balance
			sq.updateCustomerAccountTableQuery(newBalance, ca.getCaid());
			
			System.out.println("Withdraw Successful");//continues transaction
			ca = cadi.getMatchingAccountNumber(input, c.getcId());//new function 
			System.out.println("Account new balance is " + ca.getBalance());
			
			System.out.println("Do you want to continue? y/n ");
			String y_n ;
				y_n = sc.nextLine();
				 while (!y_n.equals("Y") ||!y_n.equals("y") || !y_n.equals("n") ||!y_n.equals("N"))
				 {
				if (y_n.equals("y") ||y_n.equals("Y"))
						{
					withdraw(ca,c);
				}
				else if(y_n.equals("n") || y_n.equals("N")) {
					displayLoginSuccess(c);
				}
				else
				{
					System.out.println("Type y/n");	
					y_n = sc.nextLine();
				}
			}
		
		}
		else {
			System.out.println("Bank account id is number founnd. Try again");
			withdraw(ca,c);
		}
	}
}
