package com.revature.corejava;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Question20 {
	public static void readFromFile() {
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader("./data.txt"))){
			String line;
			while ((line = bufferedReader.readLine())!= null) {
				String[] wordsInLine = line.split(":");
				System.out.println("Name: " + wordsInLine[0] + " " + wordsInLine[1]);
				System.out.println("Age: " + wordsInLine[2]);
				System.out.println("State: " + wordsInLine[3] + "\n");
				
			}
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
