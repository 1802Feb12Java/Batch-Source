package com.revature.bankingapp2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bankingapp2.beans.Account;

public class FileIOHandling {
	
	public static ArrayList<String> readInUsernameLog(){
		Scanner input = null;
		String usernameLog = "/BankingApp/src/com/revature/bankingapp2/UsernameLog.txt";
		try{
			input = new Scanner(new File(usernameLog));
		}
		catch (FileNotFoundException e){
			System.out.println("Unable to open file " + usernameLog + ".");
		}
		ArrayList<String> stringList = new ArrayList<String>();	
		while(input.hasNextLine()) { //while there is still a line to read
			stringList.add(input.nextLine());//store next line in arrayList
		}//end while loop
	
		return stringList; 
		//each entry in the array should be a string with the format "username:password:userType"
		
	}//end readInUsernameLog method

	public static void writeToUsernameLog(ArrayList<String> usernameLog) {
	     try {
			 String toWrite = "";
			 for(String s : usernameLog) {
				 toWrite = toWrite.concat(s + "\n");
			 }
			 File file = new File("/BankingApp/src/com/revature/bankingapp2/UsernameLog.txt");
			  
			 Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
			 writer.write(toWrite);
			 writer.close();
			  
	      } catch (IOException ioe) {
		   ioe.printStackTrace();
		}
	}//end writeToUsernameLog method
	
	@SuppressWarnings("unchecked")
	public static ArrayList<Account> readInAccountLog(){
		Serializable s = null;
		
		FileInputStream fileIS;
		try {
			fileIS = new FileInputStream("/BankingApp/src/com/revature/bankingapp2/AccountLog.txt");
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
		
		
		return (ArrayList<Account>) s;
		
	}//end readInAccountLog method

	public static void writeToAccountLog(ArrayList<Account> accountLog) {
		FileOutputStream fileOS;
		
		try {
			fileOS = new FileOutputStream("/BankingApp/src/com/revature/bankingapp2/AccountLog.txt");
			ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
			
			objectOS.writeObject(accountLog);
			
			objectOS.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}//end writeToAccountLog method
	
}//end class
