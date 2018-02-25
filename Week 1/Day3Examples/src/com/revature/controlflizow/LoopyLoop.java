package com.revature.controlflizow;

public class LoopyLoop {
	//if
	//switch
	//loops
	static boolean MattIsDoingWell=true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ifFun(!MattIsDoingWell);
		isBigger(2,5);
		isBigger(5,5);
		System.out.println(whatColor("green"));
		System.out.println(whatColor("potato"));
	}
	
	public static void ifFun(boolean a) {
		/*if(conditional) {
			what to do if true;
		}else if(second contional) {
			what to do if second conditional is true;
		}
		else {
			what to do if false;
		}*/
		
		if(a ) {
			System.out.println("Roll Tide!");
		}else {
		System.out.println("Well,dern");
		}
	}
	public static void isBigger(int a, int b) {
		if(a>b) {
			System.out.println("a is bigger than b");
		}else if(a==b) {
			System.out.println("EQUALS!!!!");
		}
		else {
			System.out.println("b is bigger than a");
		}
	}
	
	//Switch
	 static String color;
	 public static String whatColor(String color) {
		 
	 
	switch (color) {
	case "green":
		return color;
		//break;
	case "purple":
		return color;
		//break;
	case "orange":
		return color;
		//break;
	default:
		return " Good job idiot";
	
		
	
	}
	 }
		
}
	
