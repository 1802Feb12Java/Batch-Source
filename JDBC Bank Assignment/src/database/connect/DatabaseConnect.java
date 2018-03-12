package database.connect;

import java.util.Scanner;


import java.sql.*;
public class DatabaseConnect {
	private String userName;
	private String password;
	private int balance;
	
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public DatabaseConnect deposit(int add)
	{
		int balance = this.getBalance();
		balance += add;
		this.setBalance(balance);
		return this;
	}
	public DatabaseConnect withdraw(int subtract)
	{
		
		if(this.getBalance() - subtract < 0)
		{
			System.out.println("Balance Exceeded");
			return this;
			
		}
		else
		{
			int balance = this.getBalance();
			balance -= subtract;
			this.setBalance(balance);
		}
		return this;
	}
	public void crud(Connection e) throws SQLException
	{
		
		ResultSet rs;
		System.out.println("1. Create new account");
		System.out.println("2. Login");
		Scanner sc = new Scanner(System.in);
		String in = sc.nextLine();
		if(in.equals("1"))
		{
			System.out.println("Enter new username");
			String userName = sc.nextLine();
			System.out.println("Enter new password");
			String password = sc.nextLine();
			String sqlIdentifier = "SELECT BANK_ACCOUNT_ID.nextval, USER_ID.nextval from dual";
			PreparedStatement pst = e.prepareStatement(sqlIdentifier);
			rs = pst.executeQuery();
			int bankID, userID;
			bankID = 0;
			userID = 0;
			if(rs.next())
			{
				bankID = rs.getInt(1);
				userID = rs.getInt(2);
			}
			PreparedStatement createAccount = e.prepareStatement
			("INSERT INTO BANK VALUES(?,?,?,?,?)");
			createAccount.setInt(1,bankID);
			createAccount.setInt(2,userID);
			createAccount.setString(3,userName);
			createAccount.setString(4,password);
			createAccount.setInt(5, 0);
			createAccount.executeQuery();
			System.out.println("New account created");
		}
		else if(in.equals("2"))
		{
			System.out.println("Enter Username");
			String userName = sc.nextLine();
			System.out.println("Enter password");
			String password = sc.nextLine();
			PreparedStatement login = e.prepareStatement
			("SELECT USER_ID FROM BANK WHERE USERNAME = ? AND PASSWORD = ?");
			login.setString(1,userName);
			login.setString(2,password);
			login.executeQuery();
			this.setUserName(userName);
			this.setPassword(password);
			System.out.println("Successfully logged in");
			boolean makeMoreTransactions = true;
			while(makeMoreTransactions)
			{
				System.out.println("1. View accounts and balances");
				System.out.println("2. Do transactions");
				System.out.println("3. Delete account");
				String sci = sc.nextLine();
				if(sci.equals("1"))
				{
					PreparedStatement viewBalance = e.prepareStatement
					("SELECT BALANCE FROM BANK WHERE USERNAME = ? AND PASSWORD = ?");
					viewBalance.setString(1, this.getUserName());
					viewBalance.setString(2, this.getPassword());
					rs = viewBalance.executeQuery();
					int balance = 0;
					if(rs.next()) {
						balance = rs.getInt(1);
					}
					System.out.println("Your balance is " + balance);
					
				}
				else if(sci.equals("2"))
				{
					
					System.out.println("1. Deposit");
					System.out.println("2. Withdraw");
					PreparedStatement transaction = e.prepareStatement
					("SELECT BALANCE FROM BANK WHERE USERNAME = ? AND PASSWORD = ?");
					transaction.setString(1, this.getUserName());
					transaction.setString(2, this.getPassword());
					rs = transaction.executeQuery();
					int balance = 0;
					if(rs.next())
					{
						balance = rs.getInt(1);
					}
					this.setBalance(balance);
					String aTransaction = sc.nextLine();
					if(aTransaction.equals("1"))
					{
						System.out.println("Deposit amount");
						int aDeposit = sc.nextInt();
						deposit(aDeposit);
						PreparedStatement prepareDeposit = e.prepareStatement
						("UPDATE BANK SET BALANCE = ? WHERE USERNAME = ? AND PASSWORD = ?");
						prepareDeposit.setInt(1, this.getBalance());
						prepareDeposit.setString(2, this.getUserName());
						prepareDeposit.setString(3, this.getPassword());
						prepareDeposit.executeQuery();
						
					}
					else if(aTransaction.equals("2"))
					{
						System.out.println("Withdraw amount");
						int aWithdraw = sc.nextInt();
						withdraw(aWithdraw);
						PreparedStatement prepareWithdraw = e.prepareStatement
						("UPDATE BANK SET BALANCE = ? WHERE USERNAME = ? AND PASSWORD = ?");
						prepareWithdraw.setInt(1, this.getBalance());
						prepareWithdraw.setString(2, this.getUserName());
						prepareWithdraw.setString(3, this.getPassword());
						prepareWithdraw.executeQuery();
					}
					
					sc.nextLine();
					  
				}
				else if(sci.equals("3"))
				{
					System.out.println("Delete Account");
					System.out.println("Username");
					String fromUsername = sc.nextLine();
					System.out.println("Password");
					String fromPassword = sc.nextLine();
					if(this.getBalance() == 0)
                    {		
						PreparedStatement prepareDelete = e.prepareStatement
					    ("DELETE FROM BANK WHERE USERNAME = ? AND PASSWORD = ?");
						prepareDelete.setString(1, fromUsername);
						prepareDelete.setString(2, fromPassword);
						prepareDelete.executeQuery();
						System.out.println("Account successfully deleted");
				    }
                    else
                    {
                    	System.out.println("Accounts");
                    }
                 }
				System.out.println("Would you like to make more transaction? y/n");
				String moreTrans = sc.nextLine();
				if(moreTrans.equals("n"))
				{
					makeMoreTransactions = false;
				}
				
			}
                    
                    
		}
		
	}
	public void superUser(Connection e) throws SQLException
	{
		while(true) 
		{
			PreparedStatement viewAllRecords = e.prepareStatement
		    ("SELECT * FROM BANK");
			ResultSet rs = viewAllRecords.executeQuery();
	        while (rs.next()) 
	        {
	        	int BANK_ACCOUNT_ID = rs.getInt("BANK_ACCOUNT_ID");
	        	int USER_ID = rs.getInt("USER_ID");
	        	String USERNAME = rs.getString("USERNAME");
	        	String PASSWORD = rs.getString("PASSWORD");
	        	int BALANCE = rs.getInt("BALANCE");
	        	System.out.println("Bank account #: " + BANK_ACCOUNT_ID +" " +"User ID: " + USER_ID + " " + "Username: "+ USERNAME + " " +"Password: "+ PASSWORD + " " +"Balance: "+ BALANCE);
	        	//calls all basic functionality for accounts
	        	
	        }
	        crud(e);
		}
	}
	
}
