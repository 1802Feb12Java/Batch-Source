package com.revature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MyScanner 
{
	ArrayList<String> usernameArrayList = new ArrayList<String>();
	//usernames are read in from text file and stored in this array to make sure we aren't duplicating the usernames
	
	public MyScanner()
	{
		BankAccount ba = new BankAccount(); //declare a bank account object for deposits and withdarawals
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to the Talanian bank. What would you like: \n1 Create Account\n2 Login\n");
		//String like = scan.next();
		
		System.out.println("Enter the employee's first name: ");
		String firstname = scan.next();
		
		System.out.println("Enter the employee's last name: ");
		String lastname = scan.next();
		
		System.out.println("Enter the employee's username: ");
		String username = scan.next();		
		
		System.out.println("Enter the employee's password: ");
		String password = scan.next();	
		
        String line = null;
        String lastLine = null; //line and lastLine are used to store the current (and former) lines read in by BufferedReader
        String idString = null; //idString
        String writeAccountInfo = null; //writeAccountInfo is the end String written into id.txt
        
        //the ID value is read from the text file and incremented each time the program is ran
		try (BufferedReader reader = new BufferedReader(new FileReader("id.txt")))
		{
		    while (true) 
		    {
				try 
				{
					line = reader.readLine();
					if(line != null)
					{
						String[] lastLineArray = line.split(":");
						String user = lastLineArray[3]; //position 3 is where the username is
						usernameArrayList.add(user);
					}
				} 
				catch (IOException e)
				{
					e.printStackTrace();
				}
				
		        if (line == null) 
		        {
		        		//here we parse the most recent id
		    		    String[] lastLineArray = lastLine.split(":");
		    		    String lastID = lastLineArray[0];
		    		    
		    		    
		    		    //then we increment it, and convert it back to a string to be written
		    			int result = Integer.parseInt(lastID);
		    			result ++;
		    			idString = String.valueOf(result);
		    			
		    			//here we set up our string with all of our data, including the incremented ID
		    			//we first check the username array to make sure we haven't entered a duplicate username, else we force
		    			//the user to make a new username 
		    			boolean sameUsername = true;

		    			while(sameUsername) //check to see if the username is already taken
		    			{
		    				sameUsername = false;
			    			for(String s:usernameArrayList) //loop through the username array
			    			{
			    				if(s.equals(username))
			    				{
			    					sameUsername = true;
			    				}
			    			}
			    			if(sameUsername) 
			    			{
			    				System.out.println("Enter the employee's username: ");
			    				username = scan.next();	
			    			}
		    			}
		    			
		    			writeAccountInfo = idString + ":" + firstname + ":" + lastname + ":" + username + ":" + password;
		    			try( BufferedWriter out = new BufferedWriter(new FileWriter("id.txt",true)) )
				    	{
		    				out.newLine();
		    				out.write(writeAccountInfo);
				    	}
		    			break;
		    	}
		        else
		        {
		        	lastLine = line;
		        }
		        
		    }

		    System.out.println(writeAccountInfo + " is the Employee id : firstname : lastname : username : password");
		    }
		catch(FileNotFoundException e )
		{
			e.printStackTrace();
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}	
		//generate customer
		Customer cm = new Customer(idString, firstname, lastname, username, password);
		//get user bank account info 
		
		
		//deposit and withdraw
		
		while(true)
		{
		System.out.println("What would you like to do? 0: quit, 1: deposit, 2: withdraw");
		String input = scan.next();
		while(!input.equals("0") && !input.equals("1") && !input.equals("2"))
		{
			input = scan.next();
		}
		switch(input)
		{
		case "0":
			System.out.println("Quitting application. Thank you");
			scan.close(); //close the scanner object
			System.exit(0);
			break;
		case "1":
			//savings or checking
			System.out.println("Savings or checking (1,2): ");
			String pickAccount = scan.next();
			switch(pickAccount)
			{
			case "1":
				System.out.println("Enter a value for input: ");
				String takeInput = null;
				while(!isNumeric(takeInput))
				{
					takeInput = scan.next();
				}
				if(isNumeric(takeInput))
				{
					Double depo = Double.parseDouble(takeInput);
					ba.depositChecking(depo);
				}
			case "2":
				System.out.println("Enter a value for input: ");
				String takeInput2 = null;
				while(!isNumeric(takeInput2))
				{
					takeInput2 = scan.next();
				}
				if(isNumeric(takeInput2))
				{
					Double depo = Double.parseDouble(takeInput2);
					ba.depositSavings(depo);
				}
				break;
			default:
				break;
			}
		case "2":
			//savings or checking
			System.out.println("Savings or checking (1,2): ");
			String pickAccount1 = scan.next();
			switch(pickAccount1)
			{
			case "1":
				System.out.println("Enter a value for input: ");
				String takeInput = null;
				while(!isNumeric(takeInput))
				{
					takeInput = scan.next();
				}
				if(isNumeric(takeInput))
				{
					Double depo = Double.parseDouble(takeInput);
					ba.withdrawChecking(depo);
				}
			case "2":
				System.out.println("Enter a value for input: ");
				String takeInput2 = null;
				while(!isNumeric(takeInput2))
				{
					takeInput2 = scan.next();
				}
				if(isNumeric(takeInput2))
				{
					Double depo = Double.parseDouble(takeInput2);
					ba.withdrawSavings(depo);
				}
				break;
			default:
				break;
			}			
			
		}
		}
		
	}

	//func to check string vadility if str is double
	public static boolean isNumeric(String str)  
	{  
	  try  
	  {  
	    double d = Double.parseDouble(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
	
	//func to check string vadility if str is int
	public static boolean isNumericInt(String str)  
	{  
	  try  
	  {  
	    int d = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
	    return false;  
	  }  
	  return true;  
	}
}

