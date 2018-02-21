package com.revature.bankingapp2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import com.revature.bankingapp2.beans.Account;

public class Validation {
	
	public static boolean uniqueUsername(String username) {
		//this method will check the username log and return whether or not the username is found in the log
		ArrayList<String> usernameLog = FileIOHandling.readInUsernameLog();
		for(int i = 0; i < usernameLog.size(); i++) {
			if(usernameLog.get(i).startsWith(username)) {
				return true;
			}//end if
		}//end for
		return false;
	}//end checkForUsername method
	
	public static String validateUsername(String username) {
		String username2 = "";
		boolean b = uniqueUsername(username);
		if(!b) { 
			return username;
		} else {
			while(b) {
				System.out.println("This username is already taken.");
				Scanner read = new Scanner(System.in);
				System.out.print("Enter another username: ");
				username2 = read.nextLine();
				b = uniqueUsername(username2);
			}
			return username2;
		}
	}//end validateUsername
	
	public static Integer randomIDGenerator() {
			
			Random rand = new Random();
			int n = rand.nextInt(1000);
			n = n + 10000;
			ArrayList<Account> accountLog = FileIOHandling.readInAccountLog();
			for(Account a : accountLog) {
				if(a.getAccountNumber() == n) {
					n = randomIDGenerator();
				}
			}
			return n;
	}//end randomIDGenerator method
		
	public static String checkUsernamePasswordMatch(ArrayList<String> userLog, String username, String password) {
		ArrayList<String> stringList = userLog;
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
	
	}//end class
