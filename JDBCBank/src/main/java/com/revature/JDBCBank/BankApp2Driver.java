package com.revature.JDBCBank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.revature.DAO.AccountDAO;
import com.revature.DAO.AccountDAOClass;
import com.revature.DAO.TransactionDAO;
import com.revature.DAO.TransactionDAOClass;
import com.revature.DAO.UserDAO;
import com.revature.DAO.UserDAOClass;

public class BankApp2Driver {

	private static ArrayList<User> allUsers = new ArrayList<User>();
	private static ArrayList<Account> allAccounts = new ArrayList<Account>();
	private static Scanner scan = new Scanner(System.in);
	private static Logger log = Logger.getLogger(BankApp2Driver.class.getName());
	private static Connection conn;		//null to start, set right at start of main
	private static UserDAO userDao;
	private static TransactionDAO transDao;
	private static AccountDAO accountDao;
	
	public static void main(String[] args) throws SQLException {
		System.out.println("Hi, welcome to Trevor's banking app!\n");

		System.out.println("Connecting to database...");
		
		try {
			Properties props = new Properties();
			FileInputStream in = new FileInputStream(new File("database.properties"));
			props.load(in);
			in.close();

			String driver = props.getProperty("driver");
			if (driver != null) {
			    Class.forName(driver) ;
			}

			String url = props.getProperty("url");
			String dbusername = props.getProperty("user");
			String password = props.getProperty("pass");
			conn = DriverManager.getConnection(url, dbusername, password);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		userDao = new UserDAOClass(conn);
		transDao = new TransactionDAOClass(conn);
		accountDao = new AccountDAOClass(conn);
		
		ResultSet accountSet = accountDao.readAllAccounts();		//getting all the accounts back from the DB to put in ArrayLists
		while(accountSet.next()) {
			int account_id = accountSet.getInt(1);
			int user_id = accountSet.getInt(2);
			int second_user_id = accountSet.getInt(3);
			double balance = accountSet.getDouble(4);
			int approved = accountSet.getInt(5);
			
			Account newAccount = new Account(account_id);
			newAccount.addUserID(user_id);
			if(second_user_id != 0) {	//returns 0 if the result is null
				newAccount.addUserID(second_user_id);
			}
			newAccount.setBalance(balance);
			if(approved == 1) {
				newAccount.setApproved(true);
			}
			
			allAccounts.add(newAccount);
		}
		
		ResultSet userSet = userDao.readAllUsers();		//getting all the users back from the DB to put in ArrayLists
		while(userSet.next()) {
			int u_id = userSet.getInt(1);
			//don't need access level
			String username = userSet.getString(3);
			String pass = userSet.getString(4);
			String fullname = userSet.getString(5);

			if(u_id != -1) {
				User newUser = new User(u_id, username, pass, fullname);
				allUsers.add(newUser);
				for(Account a : allAccounts) {
					if(a.getUserIDs().get(0) == u_id) {
						newUser.addAccount(a);
					}
					else if(a.getUserIDs().size() > 1) {
						if(a.getUserIDs().get(1) == u_id) {
							newUser.addAccount(a);
						}
					}
				}
			}
		}
		
		System.out.println("Connected!");
		
		//UNCOMMENT BELOW AND COMMENT ABOVE IF DB NEEDS TO BE RESET
		
//		allUsers.add(new User("joe", "@@", "Jim George"));	//making some initial stuff for the start of a bank
//		allUsers.add(new User("jim", "@13", "Joe Johnson"));
//		allAccounts.add(new Account());
//		allAccounts.get(0).setBalance(500);
//		allAccounts.add(new Account());
//		allAccounts.get(1).setBalance(250);
//		allAccounts.add(new Account());
//		allAccounts.get(2).setBalance(200);
//		allAccounts.add(new Account());
//		allAccounts.get(3).setBalance(80);	//purposefully not approved
//		allUsers.get(0).addAccount(allAccounts.get(0));
//		allUsers.get(0).addAccount(allAccounts.get(1));
//		allUsers.get(1).addAccount(allAccounts.get(2));	//given to second person
//		allUsers.get(1).addAccount(allAccounts.get(3));
//		allAccounts.get(0).setApproved(true);
//		allAccounts.get(1).setApproved(true);
//		allAccounts.get(2).setApproved(true);
//		
//		for(User u : allUsers) {
//			try {
//				PreparedStatement ps = conn.prepareStatement("Insert into usertable values (?,?,?,?,?)");
//				PreparedStatement idps = conn.prepareStatement("select useridsequence.nextval from dual");
//				ResultSet rs = idps.executeQuery();
//				int myID = 0;
//				if(rs.next())
//					myID = rs.getInt(1);
//				ps.setInt(1, myID);
//				ps.setInt(2, 0);
//				ps.setString(3, u.getUsername());
//				ps.setString(4, u.getPassword());
//				ps.setString(5, u.getFullName());
//				ps.executeQuery();  
//	        } catch (SQLException e) {
//	        	System.out.println("User insert failed. Don't forget to drop and re-add the sequences.");
//	          e.printStackTrace();
//	        }
//		}
//		
//		for(Account a : allAccounts) {
//			try {
//				PreparedStatement ps = conn.prepareStatement("Insert into accounts (account_id, user_id, balance, approved) values (?,?,?,?)");
//				PreparedStatement idps = conn.prepareStatement("select accountidsequence.nextval from dual");
//				ResultSet rs = idps.executeQuery();
//				int myID = 0;
//				if(rs.next())
//					myID = rs.getInt(1);
//				ps.setInt(1, myID);
//				ps.setInt(2, a.getUsers().get(0).getUserID());
//				ps.setDouble(3, a.getBalance());
//				ps.setInt(4, 1);
//				ps.executeQuery();  
//	        } catch (SQLException e) {
//	        	System.out.println("Account insert failed. Don't forget to drop and re-add the sequences.");
//	          e.printStackTrace();
//	        }
//		}
		
		String endChoice = "";
		while(!endChoice.equals("quit")) {
			System.out.print("Please indicate whether you are a user or superuser by typing U or S, respectively, then pressing ENTER: ");
			String userType = scan.nextLine().toUpperCase();
			while(!(userType.equals("U") || userType.equals("S"))){
				//while they didn't put in c or a
				System.err.println("Invalid input.");
				System.out.print("Please put either U or S: ");
				userType = scan.nextLine().toUpperCase();
			}
			
			switch(userType) {
				case "U":
					User c = loginUser();
					do{
						endChoice = doUserStuff(c);
					} while(!(endChoice.equals("lo") || endChoice.equals("quit")));
					System.out.println();	//formatting
					break;
					
				case "S":
					SuperUser s = loginSuperUser();
					do{
						endChoice = doSuperUserStuff(s);
					} while(!(endChoice.equals("lo") || endChoice.equals("quit")));
					System.out.println();	//formatting
					break;
			}
		}
		
		System.out.println("\nThanks for using Trevor's banking app!\nCome again soon!\n");	
		conn.close();
		scan.close();
	}
	
	public static User loginUser() throws SQLException {
		User user = null;
		String[] currentUsernames = new String[allUsers.size()];
		for(int i=0; i<allUsers.size(); i++) {
			currentUsernames[i] = allUsers.get(i).getUsername();
		}
		System.out.print("Enter L to login or N to make a new user account: ");
		String userChoice = scan.nextLine().toUpperCase();
		while(!(userChoice.equals("L") || userChoice.equals("N"))){
			System.err.println("Invalid input.");
			System.out.print("Please put either an N or an L: ");
			userChoice = scan.nextLine().toUpperCase();
		}
		switch(userChoice) {
			case "N":
				System.out.print("Please enter your desired username (case-sensitive): ");
				String username = scan.nextLine();
				while(Arrays.stream(currentUsernames).parallel().anyMatch(username::equals)) {
					System.out.print("Username already taken. Sorry, please enter another.\n\nNew username: ");
					username = scan.nextLine();
				}
				System.out.print("Please enter your desired password (case-sensitive): ");
				String password = scan.nextLine();
				System.out.print("Please enter your full name: ");
				String name = scan.nextLine();
				
				int newUserID = userDao.insertUser(0, username, password, name);
				log.info("User " + newUserID + " created.");
				
				user = new User(newUserID, username, password, name);
				allUsers.add(user);
				System.out.println("User account created! Welcome!\n");
				break;
			case "L":
				System.out.print("Please enter your username: ");
				username = scan.nextLine();
				while(!Arrays.stream(currentUsernames).parallel().anyMatch(username::equals)) {
					System.err.println("Username doesn't match one on record.");
					System.out.print("Please try again: ");
					username = scan.nextLine();
				}
				for(User c : allUsers) {
					if(c.getUsername().equals(username))
						user = c;
				}
				System.out.print("Please enter your password: ");
				int failCounter = 1;	//keeps track of how many failed attempts
				password = scan.nextLine();
				while(!user.getPassword().equals(password) && failCounter < 3) {
					System.err.println("Incorrect password for username \"" + username + "\", please try again.");
					System.out.print("Password: ");
					password = scan.nextLine();
					if(!user.getPassword().equals(password))
						failCounter++;
				}
				if(failCounter == 3) {
					System.err.println("\nToo many failed passwords. Exiting.");
					System.exit(0);
					break;
				}
				System.out.println("Welcome back " + user.getFullName() + "!\n");
				break;
		}
		return user;
	}
	
	public static String doUserStuff(User user) throws SQLException {
		System.out.println("Your account list:\n");
		for(Account a : user.getAccounts()) {
			System.out.println(a);
		}
		String optionsList = "\tA: Apply for an account\n"
				+ "\tT: Transfer between two accounts\n"
				+ "\tW: Withdraw from account\n"
				+ "\tD: Deposit to account\n"
				+ "\tC: Cancel an empty account\n"
				+ "\tV: View my transactions\n";
		String[] validUserOptions = {"A","T","W","D","C","V","LO","QUIT"};
		System.out.print("What would you like to do?\n"+optionsList);
		System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
		String userChoice = scan.nextLine().toUpperCase();
		while(!(Arrays.stream(validUserOptions).parallel().anyMatch(userChoice::equals))) {	
			//while they didn't put in a valid option string
			System.err.println("Invalid input. Try again.");
			System.out.println(optionsList);
			System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
			userChoice = scan.nextLine().toUpperCase();
		}
		switch(userChoice) {
			case "QUIT":
				return "quit";
			case "LO":
				return "lo";
			case "A":	//log and db done				
				System.out.print("Would you like to add anyone else to this account? Please type Y or N: ");
				String addAcc = scan.nextLine().toUpperCase();
				while(!(addAcc.equals("Y") || addAcc.equals("N"))) {	
					//while they didn't put in c, e, or a (or their capital versions)
					System.err.println("Invalid input. Please put either Y or N.");
					System.out.print("Y or N: ");
					addAcc = scan.nextLine().toUpperCase();
				}
				User otherUser = null;
				if(addAcc.equals("Y")) {
					System.out.print("Please enter the username of the person you would like to own this account with: ");
					String secondUser = scan.nextLine();
					String[] currentUsernames = new String[allUsers.size()];
					for(int i=0; i<allUsers.size(); i++) {
						currentUsernames[i] = allUsers.get(i).getUsername();
					}
					while(!Arrays.stream(currentUsernames).parallel().anyMatch(secondUser::equals)) {
						System.err.println("Username not found. Please try again.\n");
						System.out.print("New username: ");
						secondUser = scan.nextLine();
					}
					for(int i=0; i<allUsers.size(); i++) {
						if(currentUsernames[i].equals(secondUser))
							otherUser = allUsers.get(i);
					}
				}
				Account newAccount = new Account();
				allAccounts.add(newAccount);
				user.addAccount(newAccount);
				if(otherUser != null) {
					otherUser.addAccount(newAccount);
					int newAccountID = accountDao.insertJointAccount(user.getUserID(), otherUser.getUserID(), 0, false);
					log.info("Joint account " + newAccountID + " created.");
				}
				else {
					int newAccountID = accountDao.insertAccount(user.getUserID(), 0, false);
					log.info("Account " + newAccountID + " created.");
				}
				System.out.println("Account applied for. New Account's ID = " + newAccount.getAccountID());
				System.out.println("Please wait for an admin to approve your account.");
				break;
				
			case "T":	//log and db done
				System.out.print("Please enter the ID of the account to withdraw from: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the ID of the account to withdraw from: ");
			        scan.next(); 
			    }
			    int withdrawAccountID = scan.nextInt();
				System.out.print("Please enter the ID of the account to deposit into: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the ID of the account to deposit into: ");
			        scan.next(); 
			    }
				int depositAccountID = scan.nextInt();
				
				Account withdrawAccount = null;
				Account depositAccount = null;
				for(Account a : allAccounts) {
					if(a.getAccountID() == withdrawAccountID) {
						if(!user.getAccounts().contains(a)) {
							System.err.println("You are not an owner of account " + withdrawAccountID);
							return "";
						}
						else {
							withdrawAccount = a;
						}
					}
					if(a.getAccountID() == depositAccountID) {
						depositAccount = a;
					}
				}	
				System.out.print("Please input the amount to transfer: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the amount to transfer: ");
			        scan.next(); 
			    }
				int amount = scan.nextInt();
				boolean success = withdrawAccount.transfer(depositAccount, amount);
				
				if(success) {
					accountDao.transferMoney(withdrawAccount.getAccountID(), depositAccount.getAccountID(), amount);
					String transactionDescription = "User " + user.getUserID() + " transferred $" + amount + " from account " + withdrawAccount.getAccountID() + " to " + depositAccount.getAccountID();
					transDao.insertTransaction(user.getUserID(), transactionDescription);
					log.info(transactionDescription);
				}

				
				break;
				
			case "W":
			case "D":		//log and db done
				if(userChoice.equals("W"))
					System.out.print("Please enter the ID of the account to withdraw from: ");
				if(userChoice.equals("D"))
					System.out.print("Please enter the ID of the account to deposit to: ");

			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the account ID: ");
			        scan.next(); 
			    }
				int accountID = scan.nextInt();
				
				Account account = null;
				for(Account a : allAccounts) {
					if(a.getAccountID() == accountID) {
						if(!user.getAccounts().contains(a)) {
							System.err.println("You are not an owner of account " + accountID);
							return "";
						}
						else {
							account = a;
						}
					}
				}
				if(userChoice.equals("W")) {
					System.out.print("Please input the amount to withdraw: ");
				    while (!scan.hasNextInt()) {
						System.err.println("Please enter a number.");
						System.out.print("Input the amount to withdraw: ");
				        scan.next(); 
				    }
					amount = scan.nextInt();
					
					success = account.withdraw(amount);
					
					if(success) {
						accountDao.decreaseBalance(account.getAccountID(), amount);
						String transactionDescription = "User " + user.getUserID() + " withdrew $" + amount + " from account " + account.getAccountID();
						transDao.insertTransaction(user.getUserID(), transactionDescription);
						log.info(transactionDescription);
					}
				}
				if(userChoice.equals("D")) {
					System.out.print("Please input the amount to deposit: ");
				    while (!scan.hasNextInt()) {
						System.err.println("Please enter a number.");
						System.out.print("Input the amount to deposit: ");
				        scan.next(); 
				    }
					amount = scan.nextInt();
					
					success = account.deposit(amount);;
					
					if(success) {
						accountDao.increaseBalance(account.getAccountID(), amount);
						String transactionDescription = "User " + user.getUserID() + " deposited $" + amount + " into account " + account.getAccountID();
						transDao.insertTransaction(user.getUserID(), transactionDescription);
						log.info(transactionDescription);
					}
				}
				break;
			case "C":		//log and db done
				System.out.print("Please enter the account ID: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the account ID: ");
			        scan.next(); 
			    }
				accountID = scan.nextInt();
				
				success = user.cancelAccount(accountID);
				
				if(success) {
					for(Account a : allAccounts) {
						if(a.getAccountID() == accountID)
							allAccounts.remove(a);
					}
					accountDao.deleteAccount(accountID);
					String transactionDescription = "User " + user.getUserID() + " deleted account " + accountID;
					transDao.insertTransaction(user.getUserID(), transactionDescription);
					log.info(transactionDescription);
				}
				break;
			case "V":
				ResultSet rs = transDao.readTransactionListForUser(user.getUserID());
				int rownum = 1;
				if(!rs.next())
					System.out.println("This user has no transactions yet.");
				else {
					System.out.print("Transaction number: " + (rownum++) + " " + rs.getString(2) + " " + rs.getString(3)+"\n");
				}
				while(rs.next()) {
					System.out.print("Transaction number: " + (rownum++) + " " + rs.getString(2) + " " + rs.getString(3)+"\n");
				}
				break;
		}
		//end of userChoice switch
		System.out.print("\nIf you would like to exit the program, type \"quit\".\nIf you would like to log out, type \"lo\".\nOtherwise, press enter. ");
		if(!(userChoice.equals("A") || userChoice.equals("V"))){
			scan.nextLine();	//otherwise it'd skip the nextLine(), don't ask me why
		}
		String endChoice = scan.nextLine().toLowerCase();
		return endChoice;
	}

	public static SuperUser loginSuperUser() {
		SuperUser superUser = new SuperUser(allAccounts, allUsers, conn, log);
		
		System.out.print("Please enter your username: ");
		String username = scan.nextLine();
		while(!username.equals(superUser.getSuperUserUsername())) {
			System.err.println("Username doesn't match the one on record.");
			System.out.print("Please try again: ");
			username = scan.nextLine();
		}
		
		System.out.print("Please input the password: ");
		int failCounter = 1;
		String password = scan.nextLine();
		while(!superUser.getSuperUserPassword().equals(password) && failCounter < 3) {
			System.err.println("Incorrect password for a super user, please try again.");
			System.out.print("Password: ");
			password = scan.nextLine();
			if(!superUser.getSuperUserPassword().equals(password))
				failCounter++;
		}
		if(failCounter == 3) {
			System.err.println("\nYou're not a super user! Ending program.");
			System.exit(0);
		}
		return superUser;
	}
	
	public static String doSuperUserStuff(SuperUser superUser) throws SQLException {
		System.out.println("\nHi super user.");
		String optionsList = "Possible choices - \n"
				+ "\tRA: Read account information\n"
				+ "\tRU: Read user information\n"
				+ "\tR: Review (approve/deny) account applications\n"
				+ "\tT: Transfer between two accounts\n"
				+ "\tW: Withdraw from account\n"
				+ "\tD: Deposit to account\n"
				+ "\tC: Cancel account\n"
				+ "\tDU: Delete user\n"
				+ "\tV: View user's transaction history";
		System.out.println(optionsList);
		System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
		String superUserChoice = scan.nextLine().toUpperCase();
		String[] validSuperUserOptions = {"RA","RU","R","T","W","D","C","DU","V","LO","QUIT"};
		while(!(Arrays.stream(validSuperUserOptions).parallel().anyMatch(superUserChoice::equals))) {	
			//while they didn't put in a valid option string
			System.err.println("\nInvalid input. Try again.");
			System.out.println(optionsList);
			System.out.print("Please input the letter(s) corresponding to what you'd like to do: ");
			superUserChoice = scan.nextLine().toUpperCase();
		}
		System.out.println();	//formatting
		switch(superUserChoice) {
			case "QUIT":
				return "quit";
			case "LO":
				return "lo";
			case "RA":		//no need for log/db updates				
				System.out.print("Available account ID's: ");
				for(Account acc : allAccounts) {
					System.out.print(acc.getAccountID() + " ");
				}
				System.out.println();
				System.out.print("Please input the account ID that you wish to read: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the account ID: ");
			        scan.next(); 
			    }
				int accountID = scan.nextInt();
				System.out.println("\nAttempting to read account " + accountID + ":");
				System.out.print(superUser.readAccount(accountID));
				break;
				
			case "RU":
				System.out.print("Available user ID's: ");
				for(User crustomer : allUsers) {
					System.out.print(crustomer.getUserID() + " ");
				}
				System.out.print("\nPlease enter the user's ID: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the user ID: ");
			        scan.next(); 
			    }
				int userID = scan.nextInt();
				System.out.println("\nAttempting to read user " + userID + ":");
				System.out.println(superUser.readUser(userID));
				break;
				
			case "R":		//log and db done (in SuperUser)
				superUser.reviewAccountApplications();
				break;
				
			case "T":		//log and db done
				System.out.print("Please enter the ID of the account to withdraw from: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the ID of the account to withdraw from: ");
			        scan.next(); 
			    }
			    int withdrawAccountID = scan.nextInt();
				System.out.print("Please enter the ID of the account to deposit into: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the ID of the account to deposit into: ");
			        scan.next(); 
			    }
				int depositAccountID = scan.nextInt();
				System.out.print("Please input the amount to transfer: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the amount to transfer: ");
			        scan.next(); 
			    }
				int amount = scan.nextInt();
				
				boolean success = superUser.transferMoney(withdrawAccountID, depositAccountID, amount);

				if(success) {
					accountDao.transferMoney(withdrawAccountID, depositAccountID, amount);
					String transactionDescription = "User " + superUser.getUserID() + " transferred $" + amount + " from account " + withdrawAccountID + " to " + depositAccountID;
					transDao.insertTransaction(superUser.getSuperUserID(), transactionDescription);
					log.info(transactionDescription);
				}
				break;
				
			case "W":
			case "D":		//log and db done
				System.out.print("Please enter the account ID: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the account ID: ");
			        scan.next(); 
			    }
				accountID = scan.nextInt();
				if(superUserChoice.equals("W")) {
					System.out.print("Please enter the amount to withdraw: ");
				}
				if(superUserChoice.equals("D")) {
					System.out.print("Please enter the amount to deposit: ");
				}
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the amount: ");
			        scan.next(); 
			    }
				amount = scan.nextInt();
				
				success = superUser.editAccount(superUserChoice, accountID, amount);
				
				if(superUserChoice.equals("W") && success) {
					accountDao.decreaseBalance(accountID, amount);
					String transactionDescription = "Super user withdrew $" + amount + " from account " + accountID;
					transDao.insertTransaction(superUser.getSuperUserID(), transactionDescription);
					log.info(transactionDescription);
				}
				if(superUserChoice.equals("D") && success) {
					accountDao.increaseBalance(accountID, amount);
					String transactionDescription = "Super user deposited $" + amount + " into account " + accountID;
					transDao.insertTransaction(superUser.getSuperUserID(), transactionDescription);
					log.info(transactionDescription);
				}
				break;
				
			case "C":		//log and db done
				System.out.print("Please enter the account ID: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the account ID: ");
			        scan.next(); 
			    }
				accountID = scan.nextInt();
				
				success = superUser.editAccount("C", accountID, 0);

				if(success) {
					accountDao.deleteAccount(accountID);
					String transactionDescription = "Super user deleted account " + accountID;
					transDao.insertTransaction(superUser.getSuperUserID(), transactionDescription);
					log.info(transactionDescription);
				}
				break;
			case "DU":
				System.out.print("Available user ID's: ");
				for(User crustomer : allUsers) {
					System.out.print(crustomer.getUserID() + " ");
				}
				System.out.println();	//formatting
				System.out.print("Please enter the ID of the user you wish to delete: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the user ID: ");
			        scan.next(); 
			    }
				userID = scan.nextInt();
				
				success = superUser.deleteUser(userID);

				if(success) {
					userDao.deleteUser(userID);
					String transactionDescription = "Super user deleted user " + userID;
					transDao.insertTransaction(superUser.getSuperUserID(), transactionDescription);
					log.info(transactionDescription);
				}
				break;
			case "V":
				System.out.print("Please enter the ID of the user you wish to view the transactions of: ");
			    while (!scan.hasNextInt()) {
					System.err.println("Please enter a number.");
					System.out.print("Input the user ID: ");
			        scan.next(); 
			    }
				userID = scan.nextInt();
				
				ResultSet rs = transDao.readTransactionListForUser(userID);
				int rownum = 1;
				if(!rs.next())
					System.out.println("This user has no transactions yet.");
				else {
					System.out.print("Transaction number: " + (rownum++) + " " + rs.getString(2) + " " + rs.getString(3)+"\n");
				}
				while(rs.next()) {
					System.out.print("Transaction number: " + (rownum++) + " " + rs.getString(2) + " " + rs.getString(3)+"\n");
				}
				break;
		}

		System.out.print("\nIf you would like to exit the program, type \"quit\".\nIf you would like to log out, type \"lo\".\nOtherwise, press enter. ");
		if(!(superUserChoice.equals("R"))){
			scan.nextLine();	//otherwise it'd skip the nextLine(), don't ask me why
		}
		String endChoice = scan.nextLine().toLowerCase();
		return endChoice;
	}

}
