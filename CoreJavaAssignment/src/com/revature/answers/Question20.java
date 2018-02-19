package com.revature.answers;

import java.io.File;
import java.util.Scanner;

public class Question20 {

	private static final String inFile = "Data.txt";
	
	public static void main(String[] args) {
		
		readIt();
		
	}
	
	public static void readIt() {
		 Scanner sc;						//creating a scanner so I can read the file line by line.
	        String tokenizer[]; 			//the tokenizer is an array of strings
	        try {
	            sc = new Scanner(new File(inFile));
	            sc.useDelimiter("\\n");			//setting the delimeter of the scanner
	                while(sc.hasNextLine()) {
	                    tokenizer = sc.next().split(":"); 	//here we add the strings to tokenizer delimited by ':'
	                     System.out.println("Name: " + tokenizer[0] + " " + tokenizer[1] + "\n" + "Age: "
	                    + tokenizer[2] + "\n" + "State: " + tokenizer[3]);

	                    }


	        }catch(Exception e){}
	}

}
