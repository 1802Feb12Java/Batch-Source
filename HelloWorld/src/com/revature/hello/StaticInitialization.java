package com.revature.hello;

public class StaticInitialization {
	
	
	/*
	 //cannot reference a field before it is defined.
	 * static {
		System.out.println(message);
	}*/
	
	public static String message = "variable";
	
	static {
		System.out.println(message);
	}
	public static void main(String... a)
	{
		System.out.println("Main");
	}
	static {
		System.out.println("static block 2: electric boogaloo");
	}
}
