package com.revature;

public class Chainz {
	public static void main(String [ ]args) {
		
		Mouse mouse1 = new Mouse(10,8,6000);
		Mouse mouse2 = new Mouse(5,564);
		Mouse mouse3 = new Mouse(11);
		Mouse mouse4 = new Mouse();
		
		System.out.println(mouse1.toString());
		System.out.println(mouse2.toString());
		System.out.println(mouse3.toString());
		System.out.println(mouse4.toString());
		
	}
}
