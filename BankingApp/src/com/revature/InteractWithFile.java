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
			
			// DataInputStream object
			DataInputStream in = new DataInputStream(new FileInputStream("accounts.txt"));
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
	
	public ArrayList<String> getUserById(int idParam) {
		
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
				String custIdStr = lineArray[0];
				int custId = Integer.parseInt(custIdStr);
				
				if(custId == idParam) {
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
				String accountCustIdTwo = lineArray[3];
				int accountCustIdParsed = Integer.parseInt(accountCustId);
				int accountCustIdParsedTwo = Integer.parseInt(accountCustIdTwo);
				
				if(accountCustIdParsed == custId || accountCustIdParsedTwo == custId) {
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
	
	public ArrayList<String> getCustAccount(int custId) {

		
		ArrayList<String> custAccount = new ArrayList<String>();
		
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
				String accountCustIdTwo = lineArray[3];
				int accountCustIdParsed = Integer.parseInt(accountCustId);
				int accountCustIdParsedTwo = Integer.parseInt(accountCustIdTwo);
				
				if(accountCustIdParsed == custId || accountCustIdParsedTwo == custId) {
					custAccount.add(lineArray[0]);
					custAccount.add(lineArray[1]);
					custAccount.add(lineArray[2]);
					custAccount.add(lineArray[3]);
					custAccount.add(lineArray[4]);
				}
				
			}
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return custAccount;
	}

	public void updateAccountBalance(int accountId, double balance) {
		
		try {
			// Open users.txt file with the FileInputStream class
			FileInputStream fstream = new FileInputStream("accounts.txt");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line = "";
			String input = "";
			// Read file line by line
			while((line = br.readLine()) != null) {
				
				String[] lineArray = line.split(":");
				String idStr = lineArray[0];
				int id = Integer.parseInt(idStr);
				
				if(id == accountId) {
					
					String newLine = lineArray[0] + ":" + lineArray[1] + ":" + lineArray[2] + ":" + lineArray[3] + ":" + balance;
					input += newLine + System.lineSeparator();
					
				} else {
					input += line + System.lineSeparator();
				}
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"));
			writer.append(input);
			writer.close();
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
	}
	
	public ArrayList<String> getPendingApplications() {
		
		ArrayList<String> pendingApplications = new ArrayList<String>();
		
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
				String isApprovedStr = lineArray[1];
				int isApproved = Integer.parseInt(isApprovedStr);
				
				if(isApproved == 0) {
					pendingApplications.add(line);
				}
			}
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return pendingApplications;
	}
	
	public void approveApplication(int appId) {
		
		try {
			// Open users.txt file with the FileInputStream class
			FileInputStream fstream = new FileInputStream("accounts.txt");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line = "";
			String input = "";
			// Read file line by line
			while((line = br.readLine()) != null) {
				
				String[] lineArray = line.split(":");
				String idStr = lineArray[0];
				int id = Integer.parseInt(idStr);
				
				if(id == appId) {
					
					String newLine = lineArray[0] + ":1:" + lineArray[2] + ":" + lineArray[3] + ":" + lineArray[4];
					input += newLine + System.lineSeparator();
					
				} else {
					input += line + System.lineSeparator();
				}
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter("accounts.txt"));
			writer.append(input);
			writer.close();
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
		
		
	}

	public ArrayList<String> getCustomers() {
		
		ArrayList<String> customers = new ArrayList<String>();
		
		try {
			// Open users.txt file with the FileInputStream class
			FileInputStream fstream = new FileInputStream("users.txt");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			String line;
			// Read file line by line
			while((line = br.readLine()) != null) {
				
				String[] split = line.split("");
				
				if(!split[0].equals("1")) {
					customers.add(line);
				}
				
			}
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		return customers;
	}

}
