package com.revature.day2.constructorstuff;

public class ConstructorChaining {

	public static void main(String[] args) {
		
		Mouse mouse = new Mouse(6000, 10, 8);
		Mouse lesserMouse = new Mouse(5000, 80);
		Mouse leastMouse = new Mouse(1000);
		Mouse voidMouse = new Mouse();
		System.out.println(mouse.toString());
		System.out.println(lesserMouse.toString());
		System.out.println(leastMouse.toString());
		System.out.println(voidMouse.toString());
		
		
	}
	
	
	
	
	
}
