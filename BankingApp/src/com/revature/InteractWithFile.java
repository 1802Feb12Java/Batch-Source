package com.revature;

import java.io.*;
import java.util.ArrayList;

public class InteractWithFile {
	
	public int getLastUserId() {
		
		int result = 0;
		
		try {
			// Open users.txt file with the FileInputStream class
			FileInputStream fstream = new FileInputStream("users.txt");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			// Read file line by line
			String currentLine;
			String lastLine = null;
			while((currentLine = br.readLine()) != null) {
				
				lastLine = currentLine;
				
			}
			
			// Split last record into array
			String[] lastLineArray = lastLine.split(":");
			String lastId = lastLineArray[0];
			result = Integer.parseInt(lastId);
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return result;
	}
	
	public int getLastAccountId() {
		
		int result = 0;
		
		try {
			// Open users.txt file with the FileInputStream class
			FileInputStream fstream = new FileInputStream("accounts.txt");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			// Read file line by line
			String currentLine;
			String lastLine = null;
			while((currentLine = br.readLine()) != null) {
				
				lastLine = currentLine;
				
			}
			
			// Split last record into array
			String[] lastLineArray = lastLine.split(":");
			String lastId = lastLineArray[0];
			result = Integer.parseInt(lastId);
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return result;
	}
	
	public void addNewCustomer(String str) {
	    try {
	    	
	    		BufferedWriter writer = new BufferedWriter(new FileWriter("users.txt", true));
	    		writer.newLine();
			writer.append(str);
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	}
	
	public ArrayList<String> readUsernames() {
		
		ArrayList<String> usernames = new ArrayList<String>();
		
		try {
			// Open users.txt file with the FileInputStream class
			FileInputStream fstream = new FileInputStream("users.txt");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			// Read file line by line
			while((line = br.readLine()) != null) {
				
				String[] lineArray = line.split(":");
				String username = lineArray[3];
				usernames.add(username);
				
			}
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return usernames;
	}
	
	public ArrayList<String> getUser(String username) {
		
		ArrayList<String> user = new ArrayList<String>();
		
		try {
			// Open users.txt file with the FileInputStream class
			FileInputStream fstream = new FileInputStream("users.txt");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			// Read file line by line
			while((line = br.readLine()) != null) {
				
				String[] lineArray = line.split(":");
				String currentUsername = lineArray[3];
				
				if(username.equals(currentUsername)) {
					user.add(lineArray[0]);
					user.add(lineArray[1]);
					user.add(lineArray[2]);
					user.add(lineArray[3]);
					user.add(lineArray[4]);	
				}
			}
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return user;
	}
	
	public void createNewAccount(String str) {
		try {
	    	
	    		BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true));
	    		writer.newLine();
			writer.append(str);
			writer.close();
	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createJointAccount(String str) {
		try {
	    	
	    		BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt", true));
	    		writer.newLine();
			writer.append(str);
			writer.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean checkForAccount(int custId) {
		
		boolean hasAccount = false;
		
		try {
			// Open users.txt file with the FileInputStream class
			FileInputStream fstream = new FileInputStream("accounts.txt");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			// Read file line by line
			while((line = br.readLine()) != null) {
				
				String[] lineArray = line.split(":");
				String accountCustId = lineArray[2];
				int accountCustIdParsed = Integer.parseInt(accountCustId);
				if(accountCustIdParsed == custId) {
					hasAccount = true;
				}
				
			}
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return hasAccount;
	}

}
