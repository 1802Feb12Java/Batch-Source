package com.revature.corejavaassignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Q20 {
/*
 * Q20. Create a notepad file called Data.txt and enter the following:
 * 
 * Mickey:Mouse:35:Arizona
 * Hulk:Hogan:50:Virginia
 * Roger:Rabbit:22:California
 * Wonder:Woman:18:Montana
 * 
 * Write a program that would read from the file and print it out to the screen in the following format:
 * 
 * Name: Mickey Mouse
 * Age: 35 years
 * State: Arizona State

 */
	public static void answer() {
		System.out.println("Q20. Create a notepad file called Data.txt and enter the following:\r\n" + 
				" * \r\n" + 
				" * Mickey:Mouse:35:Arizona\r\n" + 
				" * Hulk:Hogan:50:Virginia\r\n" + 
				" * Roger:Rabbit:22:California\r\n" + 
				" * Wonder:Woman:18:Montana\r\n" + 
				" * \r\n" + 
				" * Write a program that would read from the file and print it out to the screen in the following format:\r\n" + 
				" * \r\n" + 
				" * Name: Mickey Mouse\r\n" + 
				" * Age: 35 years\r\n" + 
				" * State: Arizona State");
		createDataFile();
		readDataFile();
		
	}
	/*
	 * These methods create a data file with
	 * 
	 * Mickey:Mouse:35:Arizona
	 * Hulk:Hogan:50:Virginia
	 * Roger:Rabbit:22:California
	 * Wonder:Woman:18:Montana
	 * 
	 * then reads the data file and prints the information in the format
	 * Name: Mickey Mouse
	 * Age: 35 years
	 * State: Arizona State
	 * 
	 */
	public static void createDataFile() {
		FileOutputStream file;
		try {
			file = new FileOutputStream("Data.txt");
			String s = "Mickey:Mouse:35:Arizona\r\n" + 
					"Hulk:Hogan:50:Virginia\r\n" + 
					"Roger:Rabbit:22:California\r\n" + 
					"Wonder:Woman:18:Montana\r\n" ;
			file.write(s.getBytes());
			file.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void readDataFile() {
		File file;
		try {
			file = new File("Data.txt");
			List<String> listString = Files.readAllLines(file.toPath());
			for (String entry: listString) {
				String[] entries=entry.split(":");
				System.out.println("Name: " + entries[0] + " " + entries[1] +"\r\n" +
							"Age: " + entries[2] + "\r\n" +
							"State: " + entries[3] + " State\r\n" );
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
