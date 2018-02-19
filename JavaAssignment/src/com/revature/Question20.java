/*  This is a class file for read and writing to a file
 * 
 * 	Dominic Nguyen
 */

package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;

public class Question20 {

	// constructor
	public Question20() {
		
	}

	private static final String inFile = "Data.txt";
	private static final String outFile = "Data.txt";
	
	/*  This method creates a file and writes to it
	 *  @param contents - a string input from the user
	 */
	public void writeOutputStreamContents(String contents) {
		OutputStream os = null;
		File file = new File(outFile);
		
		try {
			os = new FileOutputStream(file,true);
			os.write(contents.getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(os != null) {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	/*  This method reads from the file
	 *  @return String - returns a string
	 */
	public String readInputStreamContents() {
		File file = new File(inFile);
		InputStream is = null;
		StringBuilder s = new StringBuilder();
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		String line;
		
		try {
			String delim = ":";
			
			// read a line from the text file
			while((line = bufferedReader.readLine()) != null) {
				String[] token = line.split(delim);
				
				// display the text in a specific format
				for(int i = 0; i < token.length; i++) {
					for(int j = 0; j < 4; j++) {
						if(j == 0) {
							System.out.print("Name: " + token[i] + " ");
						}
						else if(j == 1) {
							System.out.println(token[i]);
						}
						else if(j == 2) {
							System.out.println("Age: " + token[i] + " years");
						}
						else if(j == 3) {
							System.out.println("State: " + token[i] + " State");
						}
						
						i++;
					}
				}
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if( is != null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return s.toString();
	}
}
