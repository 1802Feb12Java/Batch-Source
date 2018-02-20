package com.revature.bankingapp1;
import java.io.BufferedWriter;
/*
 * 
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class FileKeeping {

	public static ArrayList<String> readInUsernamePasswordLog(){
		Scanner input = null;
		String userNamePasswordLog = "src\\com\\revature\\bankingapp1\\UsernamePasswordLog.txt";
		try{
			input = new Scanner(new File(userNamePasswordLog));
		}
		catch (FileNotFoundException e){
			System.out.println("Unable to open file " + userNamePasswordLog + ".");
		}
		ArrayList<String> stringList = new ArrayList<String>();	
		while(input.hasNextLine()) { //while there is still a line to read
			stringList.add(input.nextLine());//store next line in arrayList
		}//end while loop
		//System.out.println(stringList.toString());
		return stringList; //each entry in the array should be a string with the format "username:password:userType"
		
	}//end readInUsernamePasswordLog method
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Integer> readInAccountNumberLog(){
		Serializable s = null;
		
		FileInputStream fileIS;
		try {
			fileIS = new FileInputStream("C:\\Users\\colso\\Desktop\\Git Things\\Batch-Source\\BankingApp\\src\\com\\revature\\bankingapp1\\AccountNumberLog.txt");
			ObjectInputStream objectIS = new ObjectInputStream(fileIS);
			s = (Serializable) objectIS.readObject();
			
			objectIS.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return (ArrayList<Integer>) s;
		
	}//end readInAccountNumberLog method
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Customer> readInCustomerLog(){
		Serializable s = null;
		
		FileInputStream fileIS;
		try {
			fileIS = new FileInputStream("C:\\Users\\colso\\Desktop\\Git Things\\Batch-Source\\BankingApp\\src\\com\\revature\\bankingapp1\\CustomerLog.txt");
			ObjectInputStream objectIS = new ObjectInputStream(fileIS);
			s = (Serializable) objectIS.readObject();
			
			objectIS.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return (ArrayList<Customer>) s;
	}//end readInCustomerLog method
	
	public static void addToCustomerLog(ArrayList<Customer> customerLog, Customer newCustomer) {
		customerLog.add(newCustomer);
	}//end addToCustomerLog
	
	
	@SuppressWarnings("unchecked")
	public static ArrayList<BankEmployee> readInEmployeeLog(){
		Serializable s = null;
		
		FileInputStream fileIS;
		try {
			fileIS = new FileInputStream("C:\\Users\\colso\\Desktop\\Git Things\\Batch-Source\\BankingApp\\src\\com\\revature\\bankingapp1\\EmployeeLog.txt");
			ObjectInputStream objectIS = new ObjectInputStream(fileIS);
			s = (Serializable) objectIS.readObject();
			
			objectIS.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return (ArrayList<BankEmployee>) s;
	}//end readInEmployeeLog method
	
	public static void addToEmployeeLog(ArrayList<BankEmployee> employeeLog, BankEmployee newEmployee) {
		employeeLog.add(newEmployee);
		
	}//end addToEmployeeLog
	
	public static void addToUsernamePasswordLog(ArrayList<String> usernamePassLog, Customer newUser) {
		String username = newUser.getUsername();
		String password = newUser.getPassword();
		String accessType = newUser.getAccessType();
		String newLogEntry = username + ":" + password + ":" + accessType;
		usernamePassLog.add(newLogEntry);
		
	}//end addToUsernamePasswordLog
	
	public static void addToUsernamePasswordLog(ArrayList<String> usernamePassLog, BankEmployee newUser) {
		String username = newUser.getUsername();
		String password = newUser.getPassword();
		String accessType = newUser.getAccessType();
		String newLogEntry = username + ":" + password + ":" + accessType;
		usernamePassLog.add(newLogEntry);
		
	}//end addToUsernamePasswordLog
	
	public static void addToUsernamePasswordLog(ArrayList<String> usernamePassLog, BankAdmin newUser) {
		String username = newUser.getUsername();
		String password = newUser.getPassword();
		String accessType = newUser.getAccessType();
		String newLogEntry = username + ":" + password + ":" + accessType;
		usernamePassLog.add(newLogEntry);
		writeToUsernamePasswordFile(usernamePassLog);
	}//end addToUsernamePasswordLog
	
	public static boolean checkForUsername(ArrayList<String> usernamePassLog, String username) {
		//this method will check the username log and return whether or not the username is found in the log
		ArrayList<String> stringList = usernamePassLog;
		for(int i = 0; i < stringList.size(); i++) {
			if(stringList.get(i).startsWith(username)) {
				return true;
			}//end if
		}//end for
		return false;
	}//end checkForUsername method
	
	public static String checkUsernamePasswordMatch(ArrayList<String> usernamePassLog, String username, String password) {
		ArrayList<String> stringList = usernamePassLog;
		for(int i = 0; i < stringList.size(); i++) {
			if(stringList.get(i).startsWith(username)) {
				String[] userArray = stringList.get(i).split(":");
				if(password.equals(userArray[1])) {
					return userArray[2]; //will return the user type
				} else {
					return "Password is incorrect.";
				}
			}
			
		}//end for
		
		return "Username not found.";
	}//end checkUsernamePasswordMatch method
	
	public static void writeToAccountNumberFile(ArrayList<Integer> accountNumLog) {
		FileOutputStream fileOS;
		
		try {
			fileOS = new FileOutputStream("C:\\Users\\colso\\Desktop\\Git Things\\Batch-Source\\BankingApp\\src\\com\\revature\\bankingapp1\\AccountNumberLog.txt");
			ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
			
			objectOS.writeObject(accountNumLog);
			
			objectOS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end writeToAccountNumberFile
	
	public static void writeToUsernamePasswordFile(ArrayList<String> usernamePasswordLog) {
	     try {
			 String toWrite = "";
			 for(String s : usernamePasswordLog) {
				 toWrite = toWrite.concat(s + "\n");
			 }
			 File file = new File("C:\\Users\\colso\\Desktop\\Git Things\\Batch-Source\\BankingApp\\src\\com\\revature\\bankingapp1\\UsernamePasswordLog.txt");
			  
			 Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			 writer.write(toWrite);
			 writer.close();
			  
		      //System.out.println("File written Successfully");

	      } catch (IOException ioe) {
		   ioe.printStackTrace();
		}
	}//end writeToUsernamePasswordFile
	
	public static void writeToCustomerLogFile(ArrayList<Customer> customerLog) {
		FileOutputStream fileOS;
		
		try {
			fileOS = new FileOutputStream("C:\\Users\\colso\\Desktop\\Git Things\\Batch-Source\\BankingApp\\src\\com\\revature\\bankingapp1\\CustomerLog.txt");
			ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
			
			objectOS.writeObject(customerLog);
			
			objectOS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end writeToCustomerLog method
	
	public static void writeToEmployeeLogFile(ArrayList<BankEmployee> employeeLog) {
		FileOutputStream fileOS;
		
		try {
			fileOS = new FileOutputStream("C:\\Users\\colso\\Desktop\\Git Things\\Batch-Source\\BankingApp\\src\\com\\revature\\bankingapp1\\EmployeeLog.txt");
			ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
			
			objectOS.writeObject(employeeLog);
			
			objectOS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end writeToEmployeeLog method
	
}//end class
