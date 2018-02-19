package com.revature;

import java.io.*;

public class Qu20 {
	
	public Qu20() {
		
		System.out.println("Q20(read and print data from file):");
		
		try {
			// Open Data.txt file with the FileInputStream class
			FileInputStream fstream = new FileInputStream("data.txt");
			
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			
			// Read file line by line
			String strLine;
			while((strLine = br.readLine()) != null) {
				
				String[] strArray = strLine.split(":");
				System.out.println("Name: " + strArray[0] + " " + strArray[1]);
				System.out.println("Age: " + strArray[2] + " years");
				System.out.println("State: " + strArray[3] + " State");
				System.out.println("");
				
			}
			
			// Close input stream
			in.close();
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
		}
		
		
		
	}
	
	
	

}
