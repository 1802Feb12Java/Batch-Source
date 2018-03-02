package com.revature;

public class Wrappers {
	public static Integer i = 9;
	public static Integer j = 10;
	
	public static void funky() {
		System.out.println(i.equals(j));
	}
	
	public static void main (String[] args) {
		funky();
	}
}