package Banking;
import dao.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


@SuppressWarnings("unused")
public class BankingApplication
{
	public final static int VERSION = 1;
	public final static String DB_PATH = "AccountStorage.txt";  //our storage file
	public final static String LETTERS_REGEX = "\\p{L}+"; //letters_regex forces the user to not input special characters
	
	

	public static Optional<Customer> lookupAccount (ArrayList<Customer> accounts, String username)
	{
		return accounts
			.stream() //collections widget allows us to find an account name
			.filter(e->e.usernameMatches(username)) //-> separates parameters from expression
			.findFirst(); //findfirst gets you the first instance of this account
	}

	//if it's not a y, it's assumed a no. easiest way to go about this efficiency-wise
	public static boolean readYesOrNo(Scanner scan)
	{
		return scan.nextLine().substring(0,1).toLowerCase().equals("y");
	}

	public static Customer selectAccount(ArrayList<Customer> accounts, Scanner scan)
	{
		Optional<Customer> accountOp = Optional.empty();
		while (!accountOp.isPresent()) //while the account isnt present
		{
			System.out.print("Account username: ");
			String username = scan.nextLine();
			while (!username.matches(LETTERS_REGEX)) 
			{
				System.out.print("Invalid username, try again: ");
				username = scan.nextLine();
			}

			accountOp = lookupAccount(accounts, username); //look up the account
			if (!accountOp.isPresent()) //if we don't find the account, try again
			{
				System.out.println("Account does not exist, try again.");
			}
		}
		Customer account = accountOp.get();
		return account;
	}
	
	//make sure the withdrawal is a valid data entry and amount
	public static BigDecimal readWithdrawalAmount(ArrayList<Customer> accounts, Scanner scan, Customer account)
	{
		System.out.print("Amount of money to withdraw: ");
		String amountString = scan.nextLine();
		BigDecimal amount = null;
		while (amount == null || account.validatePotentialTransfer(amount))
		{
			try
			{
				amount = new BigDecimal(amountString);
			}
			catch (NumberFormatException ex)
			{
				System.out.print("Invalid amount. Try again: ");
			}
		}

		return amount;
	}

	//make sure the deposit is a valid data entry and amount
	public static BigDecimal readDepositAmount(ArrayList<Customer> accounts, Scanner scan, Customer account)
	{
		System.out.print("Amount of money to deposit: ");
		String amountString = scan.nextLine();
		BigDecimal amount = null;
		while (amount == null || account.validatePotentialDeposit(amount))
		{
			try
			{
				amount = new BigDecimal(amountString);
			}
			catch (NumberFormatException ex)
			{
				System.out.print("Invalid amount. Try again: ");
			}
		}

		return amount;
	}
	
	private static boolean connectToDatabase()
	{
	       try
	        {

	            Class.forName("oracle.jdbc.driver.OracleDriver");

	        }
	        catch (ClassNotFoundException e)
	        {

	            System.out.println("Where is your Oracle JDBC Driver?");
	            e.printStackTrace();
	            return false;

	        }

	        System.out.println("Oracle JDBC Driver Registered!");

	        Connection connection = null;

	        try
	        {
	            connection = DriverManager.getConnection("jdbc:oracle:thin:@mydatabase.ckcbgjmfavpl.us-east-2.rds.amazonaws.com:1521:ORCL", "talanianj", "password");

	        }
	        catch (SQLException e)
	        {

	            System.out.println("Connection Failed! Check output console");
	            e.printStackTrace();
	            return false;
	        }

	        if (connection != null)
	        {
	            System.out.println("You made it, take control your database now!");
	            
	            return true;
	        } 
	        else 
	        {
	            System.out.println("Failed to make connection!");
	            return false;
	        }
	}

	public static BigDecimal readTransferAmount(ArrayList<Customer> accounts, Scanner scan, Customer account)
	{
		System.out.print("Amount of money to transfer: $");
		String amountString = scan.nextLine();
		BigDecimal amount = null;
		while (amount == null || account.validatePotentialTransfer(amount))
		{
			try
			{
				amount = new BigDecimal(amountString);
			}
			catch (NumberFormatException ex)
			{
				System.out.print("Invalid amount. Try again: ");
			}
		}

		return amount;
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException
	{

		//define arraylists to retrive and utilize accounts
		ArrayList<Object> Accounts = new ArrayList<Object>();
		ArrayList<Customer> Customers = new ArrayList<Customer>();
		ArrayList<Employee> Employees = new ArrayList<Employee>();
		ArrayList<Administrator> Administrators = new ArrayList<Administrator>();
		ArrayList<JointCustomer> JointCustomers = new ArrayList<JointCustomer>();
		
		CustomerDAO CDAO = new CustomerDAO();
	//	Customers = CDAO.getCustomers();
	//	Employees = EmployeeDAO.getEmployees();
	//	allAdminAccounts = adminDAO.getAdmins();
	//	allJointAccounts = jointDAO.getJointAccounts();
		
		//add
		Accounts.addAll(Customers);
		Accounts.addAll(Employees);
		Accounts.addAll(Administrators);
		Accounts.addAll(JointCustomers);
		
		
/*
		allAccounts.addAll(allAdminAccounts);
		allAccounts.addAll(allCustomerAccounts);
		//not efficient but necessary for now
		for(JointAccount i: allJointAccounts) {
			for(int j = 0; j < allCustomerAccounts.size();j++) {
				if(i.getCustomerID() == allCustomerAccounts.get(j).getCustomerID()) {
					i.setCustomer(allCustomerAccounts.get(j));
					System.out.println("INSERT");
				}
				System.out.println(allCustomerAccounts.get(j).getCustomerID());
				System.out.println(i.getCustomerID());
			}
		}		
*/
		
	//	connectToDatabase();
		
		ArrayList<Customer> accounts = new ArrayList<>();
		Scanner scan = new Scanner(System.in);

		try
		{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DB_PATH));
			accounts = (ArrayList<Customer>)ois.readObject();
			ois.close();
		}
		
		// there's not an object saved yet, so we'll just continue because there's no db
		catch(IOException ex)
		{
		//	ex.printStackTrace();
			System.out.println("No Database found");
			
		}
		
		//the serialized database needs to be fresh
		catch (ClassNotFoundException ex)
		{
			System.out.println("Error: the serialized database contains classes from a previous version of this program");
			System.out.println("Shutting down, please delete the database and restart.");

			scan.close();
			return;
		}

		boolean keepMainMenuGoing = true;
		while (keepMainMenuGoing)
		{
			ObjectOutputStream oos = new ObjectOutputStream( new FileOutputStream(DB_PATH));
			System.out.print("Customer accounts: ");
			for (Customer e : accounts)
			{
				System.out.print(e.toString());
			}
			System.out.println();
		/*	System.out.print("Customer accounts(2): ");
			
			for (Object o : Accounts)
			{
				System.out.print("D - " + o.toString());
			}*/
			System.out.println();

			System.out.print("Main menu. Enter C if you're a customer, E for employee, and A for admin. Q for quit: ");
			String userType = scan.nextLine();
			String[] validUserTypes = {"A","a","C","c","E","e", "q", "Q"};
			//string holds all valid input entries, checking each one to see if it's an allowed input
			
			while(!(Arrays.stream(validUserTypes).anyMatch(userType::equals)))
			{	
				System.out.println("Invalid input. Please put either C, E, or A.");
				userType = scan.nextLine();
			}

			// here we do the actual business loop
			if (userType.toLowerCase().equals("c"))
			{
				System.out.print("Are you already registered? Input Y or N: ");
				if (readYesOrNo(scan))
				{
					Customer account = selectAccount(accounts, scan);
					String password = scan.nextLine();

					if (account.authenticate(password) && account.approved() == 1 && !(account.cancelled() == 0))
					{
						System.out.println("Succesfully logged in.");
						boolean keepGoing = true;
						while (keepGoing) //start switch
						{
							System.out.println("1: Deposit, 2: Withdraw, 3: Transfer, 4: Quit");
							String option = scan.nextLine();
							BigDecimal amount = null;
							switch (option)
							{
								case ("1"):
									amount = readDepositAmount(accounts, scan, account);
									account.deposit(amount);
									break;
								case ("2"):
									amount = readWithdrawalAmount(accounts, scan, account);
									account.withdraw(amount);
									break;
								case ("3"):
									amount = readTransferAmount(accounts, scan, account);
									System.out.println("Please select a destination.");
									Customer destination = selectAccount(accounts, scan);
									account.transfer(destination, amount);
									break;
								default:
								case ("4"):
									keepGoing = false;
									break;
							} //end switch
							
							CDAO.addCustomer(account);
							
							oos.flush(); //write the object
							oos.writeObject(accounts);
						//	oos.close();
						}
					}
					else
					{
						if (account.approved() == 0) //check if the account is approved
						{
							System.out.println("Sorry, your account is currently not approved. Please contact an administrator.");
						}
						else if (account.cancelled() == 1) //check if the account is terminated
						{
							System.out.println("Your account has been terminated. If you feel this was not correct, please contact an administrator");
						}
						else if (!account.authenticate(password)) //authenticate the user's password
						{
							System.out.println("Incorrect account credentials. Please try again later.");
						}
					}
				}
				else //register
				{
					System.out.println("Are you making a joint account? Input Y or N: ");
					if (readYesOrNo(scan)) //read y/n input
					{
						System.out.print("Please enter the primary account's new username: ");
						String primaryUsername = scan.nextLine();
						while (!primaryUsername.matches(LETTERS_REGEX))
						{
							System.out.print("Invalid username, try again: ");
							primaryUsername = scan.nextLine();
						}
						System.out.print("Password: ");
						String primaryPassword = scan.nextLine();
						while (!primaryPassword.matches(LETTERS_REGEX)) {
							System.out.print("Invalid password, try again: ");
							primaryPassword = scan.nextLine();
						}
						System.out.print("Name: ");
						String primaryName = scan.nextLine();
						while (!primaryUsername.matches(LETTERS_REGEX))
						{
							System.out.print("Invalid username, try again: ");
							primaryUsername = scan.nextLine();
						}

						
						System.out.print("Please enter the secondary account's new username: ");
						String secondaryUsername = scan.nextLine();
						while (!secondaryUsername.matches(LETTERS_REGEX))
						{
							System.out.print("Invalid username, try again: ");
							secondaryUsername = scan.nextLine();
						}
						
						System.out.print("Password: ");
						String secondaryPassword = scan.nextLine();
						while (!secondaryPassword.matches(LETTERS_REGEX)) {
							System.out.print("Invalid password, try again: ");
							secondaryPassword = scan.nextLine();
						}
						System.out.print("Name: ");
						String secondaryName = scan.nextLine();
						while (!secondaryUsername.matches(LETTERS_REGEX))
						{
							System.out.print("Invalid username, try again: ");
							secondaryUsername = scan.nextLine();
						}

						accounts.add(new JointCustomer(primaryUsername, primaryPassword, primaryName, secondaryUsername, secondaryPassword, secondaryName));

					}
					else
					{	
						System.out.print("Please enter the primary account's new username: ");
						String username = scan.nextLine();
						while (!username.matches(LETTERS_REGEX))
						{
							System.out.print("Invalid username, try again: ");
							username = scan.nextLine();
						}
						
						//if the username already exists, try again
						while (lookupAccount(accounts, username).isPresent())
                        {
                            System.out.print("Username taken, try again: ");
                            username = scan.nextLine();
                        }
						
						System.out.print("Password: ");
						String password = scan.nextLine();
						while (!password.matches(LETTERS_REGEX)) {
							System.out.print("Invalid password, try again: ");
							password = scan.nextLine();
						}
						System.out.print("Name: ");
						String name = scan.nextLine();
						while (!name.matches(LETTERS_REGEX))
						{
							System.out.print("Invalid name, try again: ");
							name = scan.nextLine();
						}
						CDAO.addCustomer(new Customer(username, password, name, 0));
						accounts.add(new Customer(username, password, name, 0));
					}
				System.out.println("Congratulations, you are now registered. Please contact an administrator for account approval. Goodbye.");
				}
			}
			else if (userType.toLowerCase().equals("e"))
			{
				System.out.println("Succesfully logged in.");
				boolean keepGoing = true;
				while (keepGoing)
				{
					System.out.println("1: See balance, 2: See name, 3: See username, 4: See password, 5: Quit");
					String option = scan.nextLine();
					Customer account = null;
					if (!option.equals("10"))
					{
						System.out.println("Select an account to operate on");
						account = selectAccount(accounts, scan);
					}
					switch (option)
					{
						case ("1"):
							System.out.println("$"+Employee.viewCustomerBalance(account));
							break;
						case ("2"):
							System.out.println(Employee.viewCustomerName(account));
							break;
						case ("3"):
							System.out.println(Employee.viewCustomerUsername(account));
							break;
						case ("4"):
							System.out.println(Employee.viewCustomerPassword(account));
							break;
						default:
						case ("5"):
							keepGoing = false;
							break;
					}
					oos.flush(); //write the object
					oos.writeObject(accounts);
				//	oos.close();
				}
			}
			else if (userType.toLowerCase().equals("a"))
			{
				System.out.println("Succesfully logged in.");
				boolean keepGoing = true;  //condition of while loop
				while (keepGoing) //start of while loop
				{
					System.out.println("1: Deposit, 2: Withdraw, 3: Transfer, 4: Approve/Deny, 5: Cancel Account, 6: See balance, 7: See name, 8: See username, 9: See password, 10: Quit");
					String option = scan.nextLine();
					Customer account = null;
					if (!option.equals("10"))
					{
						System.out.println("Select an account to operate on");
						account = selectAccount(accounts, scan);
					}
					BigDecimal amount = null;
					switch (option) //start of switch statement
					{
						case ("1"):
							amount = readDepositAmount(accounts, scan, account);
							account.deposit(amount);
							break;
						case ("2"):
							amount = readWithdrawalAmount(accounts, scan, account);
							account.withdraw(amount);
							break;
						case ("3"):
							amount = readTransferAmount(accounts, scan, account);
							System.out.println("Please select a destination.");
							Customer destination = selectAccount(accounts, scan);
							account.transfer(destination, amount);
							break;
						case ("4"):
							System.out.print("Approve? Input Y or N");
							if (readYesOrNo(scan))
							{
								Administrator.approveAccount(account); //aprove the user's account
							}
							// account.setApproved(scan.nextLine().substring(0,1).toLowerCase().equals("y"));
							break;
						case ("5"):
							System.out.print("Cancel? Input Y or N");
							if (readYesOrNo(scan))
							{
								Administrator.cancelAccount(account); //deny the user's account
							}
							break;
						case ("6"):
							System.out.println("$"+Administrator.viewCustomerBalance(account));
							break;
						case ("7"):
							System.out.println(Administrator.viewCustomerName(account));
							break;
						case ("8"):
							System.out.println(Administrator.viewCustomerUsername(account));
							break;
						case ("9"):
							System.out.println("$"+Administrator.viewCustomerBalance(account));
							break;
						default:
						case ("10"):
							keepGoing = false;
							oos.flush(); //flush the oos input
							break;
						} //end switch
						oos.flush(); //sanity check to make sure input gets flushed
						oos.writeObject(accounts); //write the object to the notepad file
					//	oos.close(); //close it
						
					}
				}
				else if (userType.toLowerCase().equals("q"))
				{
					oos.flush(); //sanity check to make sure input gets flushed
					oos.writeObject(accounts); //write the object to the notepad file
				//	oos.close(); //close it
					break;
				}	
				System.out.println(accounts.toString());
				oos.writeObject(accounts); //sanity check to make sure input gets flushed
				oos.flush(); //write the object to the notepad file
				oos.close(); //close it
			}
			// program ends here, flush back to disk
			//close our scanner
			scan.close();

		}	
	}
