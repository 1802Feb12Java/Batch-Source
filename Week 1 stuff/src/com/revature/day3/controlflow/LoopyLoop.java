package com.revature.day3.controlflow;

public class LoopyLoop {

	/*
	 * If		-
	 * Switch	-
	 * Loops	-
	 */
	
	static boolean mattIsDoingWell;
	
	
	
	
	public static void main(String[] args) {
		//booleans default to false
		ifFun(mattIsDoingWell);
		
		mattIsDoingWell = true;
		ifFun(mattIsDoingWell);
		ifFun(!mattIsDoingWell);
		
		isBigger(2,3);
		isBigger(5,5);
		
		System.out.println(whatColor("green"));
		System.out.println(whatColor("potato"));
		
		
		
	}
	public static void isBigger(int a, int b) {
		if(a>b) {
			System.out.println(a + " is bigger than " + b);
		} else if(a==b) {
			System.out.println("They are the same");
		} else {
			System.out.println(b + " is bigger than " + a);
		}
	}
	
	public static void ifFun(boolean a) {
		
		
		/*if( conditional) {
			what to do if true;
		} else if (2nd conditional) {
			what to do if 2nd conditioal is true
		} else {
			what to do if false;
		}*/
		
		if(a) {
			System.out.println("Roll Tide!");
		} else {
			System.out.println("Well Dern?");
		}
		
	}
	
	//switch
	static String color;
		
	public static String whatColor(String color) {
		
	
		switch (color) {
			
		case "green":
			return color;
			
		case "purple":
			return color;
			
		case "orange":
			return color;
			
		case "red":
			return color;
			
		default:
			return "Good Job Idiot";
		
		}
		
	}
	

}
