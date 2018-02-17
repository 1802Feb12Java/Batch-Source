package com.revature.homeworkone;

//import java.util.ArrayList;
import java.util.Scanner;

public class Question16 {
	
	public Question16() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void answer(String[] args) {
		//ArrayList<String> scan = new ArrayList<>();
		//Scanner scanr = new Scanner(System.in);
		System.out.println("\nQuestion #16 : Passing in Command Line Args");
		//scan.add(scanr.next());
		//continues to add tokens from scanner until there
		//are no more
		
		System.out.println(args[0].length());
		
		//can't use scanner.close() becuase it shuts down
		//System.in and you can't reopen it.  Since other questions 
		//need to use System.in we need to leave it open for now
		//scanr.close();
		
	}
	/*
	private void findNumChars(ArrayList<String> args) {
		int numChars = 0;
		int i = 0;
		
		for(String str : args) {
			
			numChars += str.toCharArray().length;
		}
		System.out.println(numChars);
	}
	*/
	
	
}
