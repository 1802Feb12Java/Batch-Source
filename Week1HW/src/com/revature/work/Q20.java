package com.revature.work;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Q20 {
	private static final String inFile="Data.txt";
	
	public void reader() throws IOException {
		BufferedReader all = new BufferedReader(new FileReader(inFile));
		String line;
		// reads line and checks if its empty 
		while ((line = all.readLine()) != null) {
			// create string array for the most recent line read and fill it with String seperated by ":"
			String[] whole = new String[line.length()];
			whole = line.split(":");
			// hardcode assuming all lines read in the same pattern
			System.out.println("Name: "+whole[0]+" "+whole[1]);
			System.out.println("Age: "+whole[2]);
			System.out.println("State: "+whole[3]);
		}
		// close the line reader
		all.close();
	}
}
