package com.revature;

import java.util.Scanner;

public class Q20Output {

	public void format() {
		Q20Read q20 = new Q20Read();
		String txt = q20.readISContents(); //uses read class to get text and put to a string 
		
		String person1;
		String person2;
		String person3;
		String person4;
		
		Scanner sc = new Scanner(txt); //Scanner has helpful methods to separate lines 
		person1 = sc.nextLine();
		String[] lineSplit = person1.split(":"); //puts line into string array and remove :
		for(int i = 1; i<4; i++) {
			if(i == 1) {
				System.out.println("Name: " + lineSplit[i-1] + " " + lineSplit[i]);
			} else if(i ==2) {
				System.out.println("Age: " + lineSplit[i]);
			} else if(i==3) {
				System.out.println("State: " + lineSplit[i] + " State\n");
			}
			//^^^kinda lame way to format but it works
		}

		person2 = sc.nextLine(); //new line new person
		lineSplit = person2.split(":");
		for(int i = 1; i<4; i++) {
			if(i == 1) {
				System.out.println("Name: " + lineSplit[i-1] + " " + lineSplit[i]);
			} else if(i ==2) {
				System.out.println("Age: " + lineSplit[i]);
			} else if(i==3) {
				System.out.println("State: " + lineSplit[i] + " State\n");
			}
		}
		
		person3 = sc.nextLine();
		lineSplit = person3.split(":");
		for(int i = 1; i<4; i++) {
			if(i == 1) {
				System.out.println("Name: " + lineSplit[i-1] + " " + lineSplit[i]);
			} else if(i ==2) {
				System.out.println("Age: " + lineSplit[i]);
			} else if(i==3) {
				System.out.println("State: " + lineSplit[i] + " State\n");
			}
		}

		person4 = sc.nextLine();
		lineSplit = person4.split(":");
		for(int i = 1; i<4; i++) {
			if(i == 1) {
				System.out.println("Name: " + lineSplit[i-1] + " " + lineSplit[i]);
			} else if(i ==2) {
				System.out.println("Age: " + lineSplit[i]);
			} else if(i==3) {
				System.out.println("State: " + lineSplit[i] + " State\n");
			}
		}

		
		
	}
}
