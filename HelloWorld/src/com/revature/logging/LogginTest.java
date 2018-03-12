package com.revature.logging;

public class LogginTest {
	
	private static LoggingService ls = LoggingService.getInstance();

	public static void main(String[] args) {

		ls.getLogger().fatal("I am going to crash the program");
		System.exit(0);
		
	}
	
	

}
