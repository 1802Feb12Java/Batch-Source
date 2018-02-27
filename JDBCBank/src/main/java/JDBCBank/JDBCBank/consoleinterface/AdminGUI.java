package JDBCBank.JDBCBank.consoleinterface;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import JDBCBank.JDBCBank.customer.bean.Customer;
import JDBCBank.JDBCBank.customer.bean.CustomerDaoImpl;
import JDBCBank.JDBCBank.customeraccount.bean.CustomerAccount;
import JDBCBank.JDBCBank.customeraccount.bean.CustomerAccountDaoImpl;
import JDBCBank.JDBCBank.employee.bean.Employee;
import JDBCBank.JDBCBank.employee.bean.EmployeeDaoImpl;
import JDBCBank.JDBCBank.oraclejdbc.SelectQueries;

public class AdminGUI {

	static Logger log = Logger.getLogger(AdminGUI.class);

	public void displayAdminMainView()
	{
		Scanner sc = new Scanner(System.in);
		int input ;
		System.out.println("============ Administrator Portal ===================");
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
				displayAdminMainView();//reloop if not put in 1,2,0
				break;
		}
			
	}
	public void displayLoginView()
	{
		Scanner sc = new Scanner(System.in);
		String user,pass;
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		System.out.println("============ Administrator Login ===================");
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
		
		//function checks from database to see if user and pass match 
		if(edi.matchEmployee(user, pass))
		{
			System.out.println("Login Successfully");
			Employee e = edi.getEmployee(user);
			displayLoginSuccess(e);
			log.debug("Login successfully");
		}
		else
		{
			log.debug("Login failed/ Wrong login");
			System.out.println("Wrong password/username. Try again!");
			displayLoginView();//loop back if input is wrong
		}	
	}
	public void displayRegisterView()
	{
		System.out.println("============ Adminstrator Registration  ===================");
		EmployeeDaoImpl edi = new EmployeeDaoImpl();
		Scanner sc = new Scanner(System.in);
		int input ;
		String username, password, fname, lname;
		do
		{
			System.out.println("\t\tEnter a username: ");
			username = sc.nextLine();
			while(edi.matchEmployeeUsername(username))//check for available username
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
	
		//execute statement, insert new customer information to customer db
		edi.insertEmployeeQuery(username,password,fname,lname);
		log.debug("Created a customer account");
		displayAdminMainView();//goes back to main view
	}
	public void exitView()
	{
		System.out.println("Thank you for your hard work!");
		System.exit(0);
	}
	
	//display logged in successfully view
	public void displayLoginSuccess(Employee e)
	{
		Scanner sc = new Scanner(System.in);
		ArrayList<CustomerAccount> caList = new ArrayList(); //customer account w/ balance
		ArrayList<Customer> cusList = new ArrayList();// all customers' information
		CustomerAccountDaoImpl ca = new CustomerAccountDaoImpl();//instantiate object
		CustomerAccount cab = new CustomerAccount();//instantiate object
		
		log.debug("admin logged in");
		int input ;
		System.out.println("============ Adminstrator Logged in  ===================");
		System.out.println("Hello " + e.getFname() + " " + e.getLname());
		
		///---------------------------- Admin Options -------------------------------
		//A superuser can view, create, update, and delete all users.

		System.out.println("\t\tWhat do you want to do today?");
		System.out.println("\t1. View Customer Account");
		System.out.println("\t2. Create Customer Account");
		System.out.println("\t3. Update Customer Account");
		System.out.println("\t4. Delete Customer Account");

		System.out.println("\t5. Back");
		while (!sc.hasNextInt())
		{
			System.out.println("Invalid input. Enter an integer please");
			sc.next(); 

		}
		input = sc.nextInt();
		switch(input) {
		case 1:
			viewCustomerAccountOption(e );
			break;
		case 2:
			createCustomerAccount(e);
			break;
		case 3:
			updateCustomerAccount(e);
			break;
		case 4:
			deleteCustomerAccount(e);
			break;
		case 5:
			displayAdminMainView();//go back to main gui
			break;
		default: //reloop if user inputs wrong number
			System.out.println("Please enter option 1,2,3,4,5");
			displayLoginSuccess(e);
		}
	}
	//function uses to delete customer's account
	public void deleteCustomerAccount(Employee e)
	{
		System.out.println("---------------------------Administrator: Delete Customer View------------------------");

		Scanner sc = new Scanner(System.in);
		int input;
		Customer c = new Customer();
		ArrayList<CustomerAccount> caList = new ArrayList();
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		CustomerAccountDaoImpl cadi = new CustomerAccountDaoImpl();
		EmployeeDaoImpl edi = new EmployeeDaoImpl();//get customer updating queries
		String user,pass, lname, fname, addr, phone;
		System.out.println("\tEnter a customer id");
		while(!sc.hasNextInt())//check if user input is int or not
		{
			System.out.println("Invalid input. Enter a number.");
			sc.next(); 
		}
		input = sc.nextInt();
		//check if customer id is valid
		while(!cdi.matchCustomerId(input)) {
			System.out.println("Customer id is invalid");
			viewOneCustomer(e);//loop back
		}
		//if customer id is found, execute the following tasks
		c = cdi.getOneCustomer(input); //pulls out customer information
		caList = cadi.getAllCustomerAccount(input); //fetch customer account
		if(cadi.checkCustomerAccountBalance(input))//if account has a remaining balance, admin can't delete account
		{
			System.out.println("Customer id " + c.getcId() + " has remained balances.");
			System.out.println("Please wait for the customer to withdraw everything.");
			displayLoginSuccess(e); //loop back
		}
		//if remaining balance is 0
		cadi.deleteCustomerAccount(input);//delete foreign keys
		cdi.deleteCustomer(input);//delete primary key
		log.debug("finished deleting an account");
		System.out.println("Do yo want to continue? y/n ");
		String y_n ;
			y_n = sc.nextLine();
	 while (!y_n.equals("Y") ||!y_n.equals("y") || !y_n.equals("n") ||!y_n.equals("N"))
			 {
			if (y_n.equals("y") ||y_n.equals("Y"))
					{
				deleteCustomerAccount(e);
			}
			else if(y_n.equals("n") || y_n.equals("N")) {
				displayLoginSuccess(e);//loop back
			}
			else
			{
				System.out.println("Type y/n");	
				y_n = sc.nextLine();
			}
		}
	
	}
	
	//function that let admin creates a customer account
	public void createCustomerAccount(Employee e) {
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		Scanner sc = new Scanner(System.in);
		int input ;
		String username, password, fname, lname, addr, phone;
		System.out.println("============ Administrator : Register a customer account ===================");
		do
		{
			System.out.println("\t\tEnter customer's username: ");
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
		log.debug("signed up a customer account success");
		displayLoginSuccess(e);
	}
	//views customer accounts and balances
	public void viewCustomerAccountOption(Employee e) {
		
		Scanner sc = new Scanner(System.in);
		int input;
		System.out.println("============ Adminstrator View Accounts ===================");

		System.out.println("\t1. View All Customers");
		System.out.println("\t2. View 1 Customer ");
		System.out.println("\t3. Back ");

		while(!sc.hasNextInt())//check if user input is int or not
		{
			System.out.println("Invalid input. Enter a number.");
			sc.next(); 
		}
		input = sc.nextInt();
		switch(input) {
		case 1:
			viewAllCustomers(e);
			break;
		case 2:
			viewOneCustomer(e);
			break;
		case 3:
			displayLoginSuccess(e);//loop back
			break;
		default: //loop back for wrong input
			System.out.println("Wrong input");
			viewCustomerAccountOption(e);
			break;
		}
		

	}
	//View all customer and their balances
	public void viewAllCustomers(Employee e)
	{
		Scanner sc = new Scanner(System.in);
		int input; 
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		CustomerAccountDaoImpl cadi = new CustomerAccountDaoImpl();
		ArrayList<Customer> cList  = cdi.getAllCustomer();
		ArrayList<CustomerAccount> caList = cadi.getEverythingInCustomerAccount();
		System.out.println("============ All Customers' Information ===================");

		for(Object o : cList)//prints customers' informations
		{
			System.out.println(o);
		}
		System.out.println("============ All Customers' Bank Accounts ===================");

		for(Object o : caList)//prints customer's balances
		{
			System.out.println(o);
		}
		System.out.println("1. Back");
		while(!sc.hasNextInt())//check if user input is int or not
		{
			System.out.println("Invalid input. Enter a number.");
			sc.next(); 
		}
		input = sc.nextInt();
		switch(input) {
		case 1: 
			displayLoginSuccess(e);
			break;
		default:
			displayLoginSuccess(e);
			break;
		}
	}
	//View 1 customer and his/her balances
	public void viewOneCustomer(Employee e) {
		log.debug("View 1 account view");
		Scanner sc = new Scanner(System.in);
		int input;
		Customer c = new Customer();
		ArrayList<CustomerAccount> caList = new ArrayList();
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		CustomerAccountDaoImpl cadi = new CustomerAccountDaoImpl();
		System.out.println("\tEnter a customer id");
		while(!sc.hasNextInt())//check if user input is int or not
		{
			System.out.println("Invalid input. Enter a number.");
			sc.next(); 
		}
		input = sc.nextInt();
		while(!cdi.matchCustomerId(input)) {
			System.out.println("Customer id is invalid");
			viewOneCustomer(e);//loop back
		}
		//if customer id is found
		System.out.println("Customer information");
		c = cdi.getOneCustomer(input);
		System.out.println(c.toString());
		caList = cadi.getAllCustomerAccount(input);
		System.out.println("Customer's Bank Accounts");
		for (Object o : caList)
		{
			System.out.println(o);
		}
		//display exit
		System.out.println();
		System.out.println("1. Back");
		while(!sc.hasNextInt())//check if user input is an int or not
		{
			System.out.println("Invalid input. Enter a number.");
			sc.next(); 
		}
		input = sc.nextInt();
		switch(input) {
		case 1: 
			displayLoginSuccess(e);
			break;
		default:
			displayLoginSuccess(e);
			break;
		}
		
	}

	//uses to update a customer account
	public void updateCustomerAccount(Employee e)
	{
		log.debug("Administrator update customer view");
		System.out.println("----------------Administrator: Update Customer View------------------");
		Scanner sc = new Scanner(System.in);
		int input;
		Customer c = new Customer();
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		CustomerAccountDaoImpl cadi = new CustomerAccountDaoImpl();
		EmployeeDaoImpl edi = new EmployeeDaoImpl();//get customer updating queries
		String user,pass, lname, fname, addr, phone;
		System.out.println("\tEnter a customer id");
		while(!sc.hasNextInt())//check if user input is int or not
		{
			System.out.println("Invalid input. Enter a number.");
			sc.next(); 
		}
		input = sc.nextInt();
		//check if customer id is valid
		while(!cdi.matchCustomerId(input)) {
			System.out.println("Customer id is invalid");
			updateCustomerAccount(e);//loop back
		}
		//if customer id is found, execute the following tasks
		c = cdi.getOneCustomer(input);//gets that customer information
		System.out.println(c.toString());//prints out who it is
		//updating username
		System.out.println("Do you want to update customer's username? y/n");
		String y_n ;
		y_n = sc.next();
		if (y_n.equals("y") ||y_n.equals("Y"))
		{
			do
			{
				//pull latest information
				c = cdi.getOneCustomer(input);//gets that customer information
				System.out.println("What is the new username for customer id " + c.getcId() + " ?" );
				user = sc.next();//get user input
				if(cdi.matchUsername(user))//check if username is valid
						{
							System.out.println("Username is taken!");
							updateCustomerAccount(e);//loops back
						}
				edi.updateCustomerUser(user,input); // update username


			} while(!checkString(user));

		}
		//---------------------------------------------------------------------
		//update password
		System.out.println("Do you want to update customer's password? y/n");
		y_n = sc.next();
		if (y_n.equals("y") ||y_n.equals("Y"))
		{
			do
			{
				//pull latest information
				c = cdi.getOneCustomer(input);//gets that customer information
				System.out.println("What is the new password for customer id " + c.getcId() + " ?" );
				pass = sc.next();
				edi.updateCustomerPass(pass, input);//calls update pass query

			} while(!checkString(pass));
		}
		//---------------------------------------------------------------------

		//update last name
		System.out.println("Do you want to update customer's last name? y/n");
		y_n = sc.next();
		if (y_n.equals("y") ||y_n.equals("Y"))
		{
			do
			{
				//pull latest information
				c = cdi.getOneCustomer(input);//gets that customer information
				System.out.println("What is the new last name for customer id " + c.getcId() + " ?" );
				lname = sc.next();
				edi.updateCustomerLname(lname, input);//calls update pass query

			} while(!checkString(lname));
			
		}
			
	
		
		//---------------------------------------------------------------------

		//update first name
		System.out.println("Do you want to update customer's first name? y/n");
		y_n = sc.next();
		if (y_n.equals("y") ||y_n.equals("Y"))
		{
			do
			{
				//pull latest information
				c = cdi.getOneCustomer(input);//gets that customer information
				System.out.println("What is the new first name for customer id " + c.getcId() + " ?" );
				fname = sc.next();
				edi.updateCustomerFname(fname, input);//calls update lname query

			} while(!checkString(fname));	
		}
		//---------------------------------------------------------------------

		//update address
		System.out.println("Do you want to update customer's address? y/n");
		y_n = sc.next();
		if (y_n.equals("y") ||y_n.equals("Y"))
		{
			do
			{
				//pull latest information
				c = cdi.getOneCustomer(input);//gets that customer information
				System.out.println("What is the new address for customer id " + c.getcId() + " ?" );
				addr = sc.next();
				edi.updateCustomerAddr(addr, input);//calls update pass query
			} while(!checkString(addr));				
		}
		
		//---------------------------------------------------------------------

		//update phone number
		System.out.println("Do you want to update customer's phone number? y/n");
		y_n = sc.next();
		if (y_n.equals("y") ||y_n.equals("Y"))
		{
			do
			{
				//pull latest information
				c = cdi.getOneCustomer(input);//gets that customer information
				System.out.println("What is the new phone number for customer id " + c.getcId() + " ?" );
				phone = sc.next();
				edi.updateCustomerPhone(phone, input);//calls update pass query
			} while(!checkString(phone));						
		}
		
		c = cdi.getOneCustomer(input);//gets that customer information

		System.out.println("Information is update");
		System.out.println(c.toString());//prints out new info
		System.out.println("Do you want to continue? y/n ");
		y_n = sc.nextLine();
	 while (!y_n.equals("Y") ||!y_n.equals("y") || !y_n.equals("n") ||!y_n.equals("N"))
			 {
			if (y_n.equals("y") ||y_n.equals("Y"))
				{
					updateCustomerAccount(e);
				}
			else if(y_n.equals("n") || y_n.equals("N")) {
				displayLoginSuccess(e);//loop back to main screen
			}
			else
			{
				System.out.println("Type y/n");	
				y_n = sc.nextLine();
			}
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
		
//public static void main(String[] args) {
//		
//	AdminGUI gui = new AdminGUI();
//		gui.displayAdminMainView();
//	}
//
}
