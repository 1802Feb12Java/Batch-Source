package com.revature.fileoperations;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import com.revature.users.*;

public class FileOperations {
	
	public static void writeAdmins(HashMap <String, Admin> map,String filename) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(new File(filename));
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		
		for(String key : map.keySet()) {
			objectOut.writeObject(map.get(key));			
		}
		
		objectOut.close();
		fileOut.close();
	}
		
	public static void writeCustomers(HashMap <String, Customer> map, String filename) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(new File(filename));
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		
		for(String key : map.keySet()) {
			objectOut.writeObject(map.get(key));			
		}
		
		System.out.println("complete.");
		objectOut.close();
		fileOut.close();
		
	}
	
	public static HashMap <String, Admin> readAdmins(String filename) {
		HashMap <String, Admin> map = new HashMap <>();
		FileInputStream fileIn = null;
		Admin current = null;
		
		try {
			fileIn = new FileInputStream(new File(filename));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ObjectInputStream objectIn = null;
		try {
			objectIn = new ObjectInputStream(fileIn);
		} catch (IOException e1) {
			System.out.println("none found.");
			return map;
		}
		
		while(true) {
			try {
				current = (Admin) objectIn.readObject();
				map.put(current.getUserName(), current);
			}
			catch (IOException e) {
                break;
             } catch (ClassNotFoundException e) {
                 System.out.println();
             }
		}
		
		System.out.println("complete.");
		
		try {
			objectIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			fileIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
		
	public static HashMap <String, Customer> readCustomers(String filename) {

		HashMap <String, Customer> map = new HashMap <>();
		FileInputStream fileIn = null;
		Customer current = null;
		
		try {
			fileIn = new FileInputStream(new File(filename));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		ObjectInputStream objectIn = null;
		try {
			objectIn = new ObjectInputStream(fileIn);
		} catch (IOException e1) {
			System.out.println("none found.");
			return map;
		}
	
		while(true) {
			try {
				current = (Customer) objectIn.readObject();
				map.put(current.getUserName(), current);
			}
			catch (IOException e) {
                break;
             } catch (ClassNotFoundException e) {
                 System.out.println();
             }
		}

		System.out.println("complete.");
		
		try {
			objectIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fileIn.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return map;		
	}
}
