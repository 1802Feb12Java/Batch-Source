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
	
	public static void writeEmployees(HashMap <String, Employee> map, String filename) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(new File(filename));
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		
		for(String key : map.keySet()) {
			objectOut.writeObject(map.get(key));			
		}
		
		System.out.println("complete.");
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
	
	public static HashMap <String, Admin> readAdmins(String filename) throws IOException, ClassNotFoundException {
		HashMap <String, Admin> map = new HashMap <>();
		FileInputStream fileIn = new FileInputStream(new File(filename));
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		
		Admin current = (Admin) objectIn.readObject();
		map.put(current.getUserName(), current);
		System.out.println("complete.");
		
		objectIn.close();
		fileIn.close();
		
		return map;
	}
	
	public static HashMap <String, Employee> readEmployees(String filename) throws IOException, ClassNotFoundException {
		HashMap <String, Employee> map = new HashMap <>();
		FileInputStream fileIn = new FileInputStream(new File(filename));
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		
		
		Employee current = (Employee) objectIn.readObject();
		map.put(current.getUserName(), current);
		System.out.println("complete.");
		
		objectIn.close();
		fileIn.close();

		return map;		
	}
	
	public static HashMap <String, Customer> readCustomers(String filename) throws ClassNotFoundException, IOException {
		HashMap <String, Customer> map = new HashMap <>();
		FileInputStream fileIn = new FileInputStream(new File(filename));
		ObjectInputStream objectIn = new ObjectInputStream(fileIn);
		
		System.out.print("  Loading customers...");
		Customer current = (Customer) objectIn.readObject();
		map.put(current.getUserName(), current);
		System.out.println("complete.");
		
		objectIn.close();
		fileIn.close();

		return map;		
	}
}
