package com.revature.controlflizow;

public class LoopyLoop {

	//if
	//switch
	//loops
	
	static boolean mattIsDoingWell = true;
	
	public static void main(String[] args) {
		
		ifFun(mattIsDoingWell);
		
		isBigger(2,5);
		isBigger(5,5);
		System.out.println(whatColor("green"));
		System.out.println(whatColor("potato"));
		

	}//end of main
	
	
	
	public static void ifFun(boolean a) {
				
		if(a) {
			System.out.println("Roll Tide!");
		}else {
			System.out.println("Well, dern");
		}
				
	}//end of ifFun
	
	public static void isBigger(int a, int b) {
		
		if(a>b) {
			System.out.println("a is bigger than b");
		} else if(a==b) {
			System.out.println("a and b are equal");
		}else {
			System.out.println("b is bigger than a");
		}
				
	}//end of isBigger
	
	//Switch
	static String color;
	
	public static String whatColor(String color) {
		
		switch (color) {
		case "green":
			return color;
		
		case "purple":
			return color;
		
		case "orange":
			return color;
			
		default:
			return "Good job idiot";
		}
	}//end of whatColor
	
	
	
	
	
	
	

}//end of class
