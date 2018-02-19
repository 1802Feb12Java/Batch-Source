package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.StringTokenizer;

public class FileInput {

	public static void example() {
		// TODO Auto-generated method stub
		readFileAndFormat("Data.txt");	
		
	}

	public static void readFileAndFormat(String fileName) {
		
		StringBuilder string = new StringBuilder("");
		
		File file = new File(fileName);
		
		//we are going to try and open the FileInputStream, throw any errors that may occur
		try {
			FileInputStream fileInput = new FileInputStream(file);
			int content;
			
			//read in and append content to StringBUilder
			while(((content = fileInput.read()) != -1)) {
				string.append((char)content);
			}

			fileInput.close();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		//we are putting our new string into the tokenizer object, and setting
		//the delimiters in the second argument to separate the tokens
		StringTokenizer tokens = new StringTokenizer(string.toString(), ":\n");
		
		//print through tokens in StringTokenizer object
		int counter = 0;
		while(tokens.hasMoreTokens()) {
			
			//reset counter after it reaches 4
			if(counter == 4) {
				counter = 0;
			}
			
			//print out all tokens based on case, this is to simply format the tokens
			switch(counter) {
			
			case 0:
				System.out.print("Name: " + tokens.nextToken());
				break;
			case 1:
				System.out.println(" " + tokens.nextToken());
				break;
			case 2:
				System.out.println("Age: " + tokens.nextToken());
				break;
			case 3:
				System.out.println("State: " + tokens.nextToken());
				break;
			default:
				System.out.println("DEFAULT CASE: Bug in code.");
				break;
			
			}
			
			counter++;
			
			
		}
	}
	
}
