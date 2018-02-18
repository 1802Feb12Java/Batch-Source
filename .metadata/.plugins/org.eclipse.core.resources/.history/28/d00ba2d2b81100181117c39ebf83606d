package com.revature.controlflow;

public class LoopyLoop {
	//if
	//switch
	//loops
	
	static boolean mattIsDoingWell = true;
	
	public static void main(String[] args) {
		ifFun(!mattIsDoingWell);
		isBigger(2, 5);
		isBigger(5, 5);
		System.out.println(whatColor("green"));
		System.out.println(whatColor("potato"));
		
		System.out.println();

		int x=0;
		boolean isBull = true;
		do {
			for(int i=0; i<5; i++) {
				while(isBull) {
					x++;
					if(x%5 == 0) {
						isBull=false;
					}
				}
				isBull=true;
			}
			System.out.println("We have run through "+x/5+" times.");
		} while(x<100);
	}
	
	public static void ifFun(boolean a) {
		if(a) {
			System.out.println("True");
		}
		else {
			System.out.println("Now you fricked up");
		}
	}
	
	public static void isBigger(int a, int b) {
		if(a>b) {
			System.out.println("a is greater than b");
		}
		else if(a==b) {
			System.out.println("EQUALS!!!!");
		}
		else {
			System.out.println("b is bigger than a");
		}
		
	}
	
	//Switch
	static String color;
	public static String whatColor(String color) {
		switch(color) {
			case "green":
				return color;
			case "purple":
				return color;
			case "orange":
				return color;
			default:
				return "Good job idiot";
		}
	}
	
}
