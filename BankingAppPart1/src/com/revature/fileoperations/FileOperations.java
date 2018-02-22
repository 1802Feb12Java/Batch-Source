package com.revature.fileoperations;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import com.revature.users.*;

public class FileOperations {
	public static void writeAdmins(HashMap <String, Admin> map,String filename) throws IOException {
		FileOutputStream fileOut = new FileOutputStream(new File(filename));
		ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
		
	}
	
	public static void writeEmployees(HashMap <String, Employee> map, String filename) {
		
	}
	
	public static void writeCustomers(HashMap <String, Customer> map, String filename) {
		
	}
	
	public static HashMap <String, Admin> readAdmins(String filename) {
		HashMap <String, Admin> map = new HashMap <>();
		
		return map;
	}
	
	public static HashMap <String, Employee> readEmployees(String filename) {
		HashMap <String, Employee> map = new HashMap <>();
		
		return map;		
	}
	
	public static HashMap <String, Customer> readCustomers(String filename) {
		HashMap <String, Customer> map = new HashMap <>();
		
		return map;		
	}
}
