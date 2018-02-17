package com.revature.homeworkone;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.List;

public class Question20 {

	
	public static void answer() {
		
		File inTXT = new File("C:\\Users\\tehub\\Desktop\\git\\Batch-Source\\homeWork1\\src\\com\\revature\\homeworkone\\Data.txt");
		try {
			List<String> output = Files.readAllLines(inTXT.toPath());
			System.out.println("\nQuestion #20");
			
			for(String str : output){
				
				String[] split = str.split(":");
				System.out.println("Name: "+split[0] + " " + split[1]);
				System.out.println("Age: "+split[2]);
				System.out.println("State: "+split[3]+" State");
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		/*
		try {
			FileInputStream inIPS = new FileInputStream(inTXT);
			inIPS.readAllLines();
		
		
		
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	
	
	
}
