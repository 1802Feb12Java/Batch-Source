package com.revature;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile20 {

	public static void main(String[] args) {
		
		File f = new File("C:\\Users\\pooja\\eclipse-workspace\\Homework1\\src\\main\\Data.txt");
		readFileContent(f);
	}

	private static void readFileContent(File file) {	// makes a string array with each word in Data.txt
		
		String[] array;
		String line;
		
		try {
			
			Scanner sc = new Scanner(file);
			
			while(sc.hasNextLine()) {
				
				line = sc.nextLine();	// allows us to work line by line
				array = line.split(":");	// String array holds strings separated by ':'
				
				print(array);
			}
			
			sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void print(String[] arr) {	// prints out elements of string array in desired format
		
		System.out.println("Name: "+arr[0]+" "+arr[1]);
		System.out.println("Age: "+arr[2]);
		System.out.println("State: "+arr[3]+" State");
		
		System.out.println();
	}
}
