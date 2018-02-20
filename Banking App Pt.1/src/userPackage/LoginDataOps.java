package userPackage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import userPackage.LoginDataNode;

public class LoginDataOps {

	public static LoginDataNode front = null;			// head of the linked list
	
	public static LoginDataNode createNode(String user, String pwd, double depo, String[] userNew) {	// create a new account node
		
		LoginDataNode temp = new LoginDataNode(user, pwd, depo, userNew);
		Scanner sc = new Scanner(System.in);
		
		if(front == null) {
			
			front = temp;
		}else {
			
			LoginDataNode ptr = front;
			
			while(ptr.next != null) {
				
				ptr = ptr.next;
			}
			
			ptr.next = temp;
		}
	
		return temp;
	}	
	
	public static void printToFile(){		// prints nodes of accounts to users.txt
		
		File fileName = new File("src\\userPackage\\users.txt");
		FileOutputStream outputStream = null;
		int k = 1, j = 0;
		
		try {
			
			outputStream = new FileOutputStream(fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for(LoginDataNode i = front; i != null; i = i.next){		// iterate through linked list and print node contents to users.txt
			
			byte[] strToBytes = ("Customer Details:\n").getBytes();
			
			try {
				
		    	outputStream.write(strToBytes);
		    	
		    	strToBytes = (i.username+"\n").getBytes();
			    outputStream.write(strToBytes);
		    	
			    strToBytes = (i.password+"\n").getBytes();
		    	outputStream.write(strToBytes);
		    	
		    	while(j < i.users.length) {
			    	
			    	strToBytes = ((i.users)[j]+";").getBytes();
			    	outputStream.write(strToBytes);
			    	j++;
			    }
		    	
		    	strToBytes = ("\n"+i.Balance+"\n").getBytes();
		    	outputStream.write(strToBytes);

		    	outputStream.write("\n".getBytes());
		    	
		    	strToBytes = (i.fullName+"\n").getBytes();
			    outputStream.write(strToBytes);
			    
		    	strToBytes = (i.birth+"\n").getBytes();
			    outputStream.write(strToBytes);
		    	
		    	strToBytes = (i.age+"\n").getBytes();
			    outputStream.write(strToBytes);
			    
		    	strToBytes = (i.phone+"\n").getBytes();
			    outputStream.write(strToBytes);
			    
		    	outputStream.write(("\n").getBytes());
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			k++;
		}
		
		System.out.println("\n");

	}
	
	public static LoginDataNode traverse(String user, String pwd) {	// lookup the account node
		
		if(user == null || pwd == null) {
			
			System.out.println("Oh, that's ok. Here are your options.");
			return null;
		}
		
		LoginDataNode ptr = front;
	
		while(ptr != null) {
						
			if(ptr.username.equals(user)) {
				
				if(ptr.password.equals(pwd)) {
					
					System.out.println("An account has been linked to that login.");
					return ptr;
				}
			}
			
			ptr = ptr.next;
		}
		
			System.out.println();
			return null;	
	}

	public static LoginDataNode check(String name) {		// find a node (account) and return

		Scanner sc = new Scanner(System.in);
		LoginDataNode ptr = front;
		
		while(ptr != null) {
			
			if(ptr.fullName.equals(name)) {
				
				return ptr;
			}
			
			ptr = ptr.next;
		}
		
		return null;
	}
	
	public static void readFile(Scanner s) {		// reads users.txt and recreates linked list
		
		LoginDataNode temp = null;
		
		while(s.hasNextLine()) {
			
			String username = s.nextLine();
			
			if(username.contains("Customer") || username.length() == 0) {
				
				continue;
			}
			
			String pwd = s.nextLine();
			String names = s.nextLine();
			String[] arr = names.split(";");
			double balance = s.nextDouble();
			
			String ignore = s.nextLine();
			String ign = s.nextLine();
			
			String full = s.nextLine();
			String birth = s.nextLine();	// its now reading full name*****************
			String ageS = s.nextLine();
			int age = Integer.parseInt(ageS);
			String phone = s.nextLine();
			
			temp = LoginDataOps.createNode(username, pwd, balance, arr);
			temp.fullName = full;
			temp.birth = birth;
			temp.age = age;
			temp.phone = phone;
		}
	}
}
