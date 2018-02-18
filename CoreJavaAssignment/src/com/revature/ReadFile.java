package com.revature;

import java.io.*;
import java.util.stream.*;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

public class ReadFile {
	private static final String filename = "src/com/revature/Data.txt";
	
	private static List<String> strList = new ArrayList<String>(10);
	
	
	//read from data.txt
	public static void readFromData() {
		//read in data line by line
		//store each line into a list
		try(Stream<String> stream = Files.lines(Paths.get(filename))){
			strList = stream.collect(Collectors.toList());
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void printInFormat() {
		//goes through the list and print each line out in expected format
		for(String curStr: strList) {
			String[] splitStr = curStr.split(":",0);
			System.out.println("\t------------------");
			System.out.println("\tName: " + splitStr[0] + " " + splitStr[1]);
			System.out.println("\tAge: " + splitStr[2] + " years");
			System.out.println("\tState: " + splitStr[3] + " State");
			System.out.println("");
		}
	}
}