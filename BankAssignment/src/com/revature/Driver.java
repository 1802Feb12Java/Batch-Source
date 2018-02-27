package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;


public class Driver {
		
	public static void main(String args[]) throws SQLException
	{
		
		Connection connection=null;
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
            connection = DriverManager.getConnection(url, dbusername, password);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
		
		User Dumbledore = new User(1, "Albus", "Dumbledore", "y");
//		System.out.println("-------- Oracle JDBC Connection Testing ------");
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (ClassNotFoundException e) {
//            System.out.println("Where is your Oracle JDBC Driver?");
//            e.printStackTrace();
//            return;
//        }
//        System.out.println("Oracle JDBC Driver Registered!");
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(
//                    "jdbc:oracle:thin:@feb12usf.ct0zicxs3wls.us-east-2.rds.amazonaws.com:1521:ORCL", "ryderj", "feb12usf");
//        } catch (SQLException e) {
//            System.out.println("Connection Failed! Check output console");
//            e.printStackTrace();
//            return;
//        }
        if (connection != null) {
            System.out.println("Connected!");
        } else {
            System.out.println("Failed to make connection!");
        }
        
        int selection = 0;
        int loggedInSelection = 0;
        Scanner sc = new Scanner(System.in);
        String username;
        String password;
        User currentUser = Dumbledore;
        Accounts currentAcct = new Accounts();
        
        
        while(true) {
        	loggedInSelection = 0;
        	if (currentUser == Dumbledore) {
        		System.out.println("Welcome to Gringotts, do you have your key?");
        		System.out.println("1) I do");
        		System.out.println("2) I am new");
        		System.out.println("3) Exit");
        		selection = sc.nextInt();
        	}

        	
        	switch(selection) {
        	case 1:	System.out.println("Please login.");
        			System.out.println("Username:");
        			sc.nextLine();
        			username = sc.next();
        			//System.out.println(username);
        			System.out.println("Password:");
        			password = sc.next();
        			//System.out.println(password);
        			
        			currentUser = currentUser.login(connection, username, password, sc);
        			if (currentUser != null && currentUser!= Dumbledore) {
        				System.out.println("Logged in");
        				
        				while(loggedInSelection != 7)
        				{
        		        	if (currentUser != null && currentUser!= Dumbledore)
        		        	{
        		        		System.out.println("You have your key, what would you like to do?");
        		        		System.out.println("1) View User details");
        		        		System.out.println("2) View Account details");
        		        		System.out.println("3) Delete account");
        		        		System.out.println("4) Deposit");
        		        		System.out.println("5) Withdral");
        		        		System.out.println("6) New account");
        		        		System.out.println("7) Logout");
        		        	}
        		        	loggedInSelection = sc.nextInt();
        		        	sc.nextLine();
        		        	
        		        	switch(loggedInSelection)
        		        	{
        		        	case 1:		currentUser.viewUser(connection);
        		        				break;
        		        				
        		        	case 2:		currentAcct.viewAccount(connection, currentUser);
        		        				break;
        		        				
        		        	case 3:		currentUser.deleteUser(connection, currentUser.getUserid());
        		        				currentUser = Dumbledore;
        		        				loggedInSelection=7;
        		        				System.out.println("You have been deleted.");
        		        				break;
        		        				
        		        	case 4:		System.out.println("How much would you like to deposit?");
        		        				double amt = sc.nextDouble();
        		        				sc.nextLine();
        		        				currentAcct.deposit(connection, amt, currentUser);
        		        				break;
        		        				
        		        	case 5:		System.out.println("How much would you like to withdraw?");
	        							double ammt = sc.nextDouble();
	        							sc.nextLine();
	        							currentAcct.withdraw(connection, ammt, currentUser);
	        							break;
	        							
        		        	case 6:		currentAcct.newAcct(connection, currentUser);
        		        				break;
	        				
        		        	case 7:		currentUser = Dumbledore;
        		        				break;
        		        				
        		        	default: 	System.out.println("That's not a valid input, try again."); 
        		        	}
        				}
        				
        				
        				break;
        			}
        			System.out.println("login failed");
        			currentUser=Dumbledore;
        			break;
        		
        	case 2: System.out.println("Please enter a username:");
        			sc.nextLine();
        			username = sc.nextLine();
        			System.out.println(username);
        			System.out.println("Please enter a password:");
        			password = sc.nextLine();
        			System.out.println(password);
        			Dumbledore.createUser(connection, username, password);
        			break;
        				
        	case 3:	System.out.println("Goodbye");
        			sc.close();
        			connection.close();
        			System.exit(0);
        				
        			
        	default: System.out.println("That's not a valid input, try again.");
        	}
        }
        
        
        
        
        
        	
        
	}
}
