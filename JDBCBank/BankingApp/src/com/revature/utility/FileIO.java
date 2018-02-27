package com.revature.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.revature.accounts.Account;

public class FileIO {
	
	public static void writeToAccounts(ArrayList<Account> accounts){		
		
		FileOutputStream fileOS;
		
		//attempt to write ArrayList to Accounts.txt
		try {
			//open streams
			fileOS = new FileOutputStream("Accounts.txt");
			ObjectOutputStream objectOS = new ObjectOutputStream(fileOS);
			
			//write to Accounts.txt
			objectOS.writeObject(accounts);
			
			//close stream
			objectOS.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}catch(IOException e) {
			e.printStackTrace();
		}
			
	}
	
	public static ArrayList<Account> readFromAccounts(){		

		Serializable s = null;
		
		FileInputStream fileIS;
		
		//attempt to retrieve objects from Accounts.txt
		try {
			fileIS = new FileInputStream("Accounts.txt");
			ObjectInputStream objectIS = new ObjectInputStream(fileIS);
			s = (Serializable)objectIS.readObject();
			
			//close ObjectInputStream
			objectIS.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();	
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//if there are no accounts, return null
		if(s == null) {
			return null;
		}
		
		return (ArrayList<Account>) s;
	}
	
}
