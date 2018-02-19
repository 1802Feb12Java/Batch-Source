package com.revature.homeworkfeb19;

//Q20. Create a notepad file called Data.txt and enter the following:
//Mickey:Mouse:35:Arizona
//Hulk:Hogan:50:Virginia
//Roger:Rabbit:22:California
//Wonder:Woman:18:Montana
// 
//Write a program that would read from the file and print it out to the 
//screen in the following format:
// 
//Name: Mickey Mouse
//Age: 35 years
//State: Arizona State
import java.io.*;
import java.util.Scanner;

public class Question20 {
	
	String line;
	String[] splits;
	

	
	
	
	File file = new File("Data.txt");
	{

    try {

        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()) {
            line = sc.nextLine();
            splits = line.split(":");
            System.out.println(line);
            
            
            System.out.println("Name: "+splits[0]+" "+splits[1]);
            System.out.println("Age: "+splits[2]+" years");
            System.out.println("State: "+splits[3]);
            System.out.println();
        }
        sc.close();
    } 
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
 }
	
	
	
	
	
	
	
	
	
}
