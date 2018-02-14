package com.revature.controlflizow;

public class LoopdyLoop {
	//if
	//switch
	//loops
	
	static boolean mattIsDoingWell = true;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ifFun(mattIsDoingWell);
		isBigger(2, 5);
		isBigger(5,5);
		System.out.println(whatColor("green"));
		System.out.println(whatColor("blargh"));
	

	}

	public static void ifFun(boolean a) {
		if(a) {
			System.out.println("Roll Tide :(");
		}else {
			System.out.println("well, dern");
		}
	}
	public static void isBigger(int j, int g) {
		if(j>g) {
			System.out.println("J all the way");
		}else if(g>j){
			System.out.println("G kilin it");
		}else {
			System.out.println("G and J trippin all over each other");
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
		case "blue":
			return color;
		default:
			return color + " ain't no color!";
		}
	}
	
	//Loops
	
}
