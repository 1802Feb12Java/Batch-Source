package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Organism {
	private String firstName;
	private String lastName;
	private int age;
	private String state;
	
	public Organism() {
		this("", "", 0, "");
	}
	
	public Organism(String firstName, String lastName, int age, String state) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.state = state;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}

class OrganismGenerator {
	private static final String TAB = "    ";
	public static List<Organism> getOrganismsFromFile(File inFile) {
		// First name and last name match anything that's not ':'
		// Age matches 1 or more digits
		// State matches any remaining characters
		// All fields are delimited by ':'
		// All fields must have at least 1 character to be valid
		Pattern linePattern = Pattern.compile(
				"(?<firstName>[^:]+):(?<lastName>[^:]+):(?<age>[0-9]+):(?<state>.+)");
		List<Organism> organismList = new ArrayList<>();
		
		try(Scanner scan = new Scanner(new FileInputStream(inFile))) {
			while(scan.hasNextLine()) {
				try {
					String line = scan.nextLine();
					Matcher lineMatcher = linePattern.matcher(line);
					if(lineMatcher.matches()) {
						// Successful pattern match
						Organism o = new Organism();
						o.setFirstName(lineMatcher.group("firstName"));
						o.setLastName(lineMatcher.group("lastName"));
						o.setAge(Integer.parseInt(lineMatcher.group("age")));
						o.setState(lineMatcher.group("state"));
						
						organismList.add(o);
					}
				} catch(Exception ex) {
					// Ignore lines that throw an exception.
				}
			}
		} catch(FileNotFoundException ex) {
			System.out.println("File \"" + inFile.getAbsolutePath() + "\" does not exist.");
		}
		return organismList;
	}
}

public class Q20 implements Runnable {
	private static final String TAB = "    ";
	private File inFile;
	
	public Q20(File f) {
		inFile = f;
	}
	
	@Override
	public void run() {
		System.out.println("Question 20: Reading Data from Files");
		List<Organism> organismList = OrganismGenerator.getOrganismsFromFile(inFile);
		final MessageFormat msgFmt = new MessageFormat(
				TAB + "Name: {0} {1}\n"
				+ TAB + "Age: {2,number,integer}\n"
				+ TAB + "State: {3} State");
		
		organismList.forEach((Organism org) -> {
			// Create message.
			String msg = msgFmt.format(
				new Object[] {
					org.getFirstName(), 
					org.getLastName(),
					org.getAge(), 
					org.getState()
				}
			);
			
			System.out.println();
			System.out.println(msg);
		});
	}

}
