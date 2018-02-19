package com.revature;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Q20 {
	/*
	 * Create a notepad file called Data.txt and enter the following:
Mickey:Mouse:35:Arizona
Hulk:Hogan:50:Virginia
Roger:Rabbit:22:California
Wonder:Woman:18:Montana
Write a program that would read from the file and print it out to the screen in the
following format:Name: Mickey Mouse
Age: 35 years
State: Arizona State
	 */

	public static void file() {
		//creates file output
		PrintWriter printWriter;
		try {
			printWriter = new PrintWriter(new FileWriter("Data.txt"));
			printWriter.println("Mickey:Mouse:35:Arizona");
			printWriter.println("Hulk:Hogan:50:Virginia");
			printWriter.println("Roger:Rabbit:22:California");
			printWriter.println("Wonder:Woman:18:Montana");
			printWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//read in files
        String readLine;
        try {
            FileReader fileReader = new FileReader("Data.txt");
            BufferedReader bufferReader=new BufferedReader(fileReader);

			while((readLine = bufferReader.readLine()) != null)
			{
			    String[] split = readLine.split(":");
			    System.out.println("Name: "+split[0]+" "+split[1]);
			    System.out.println("Age: "+split[2]+" years");
			    System.out.println("State: "+split[3]+" State");

}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
