package com.revature;

public class Q16 implements Runnable {
	private final String[] args;
	
	public Q16(String... args) {		
		this.args = (args == null ? new String[0] : args);
	}
	
	public static void main(String[] args) {
		final String TAB = "    ";
		System.out.println("Question 16: Argument String Length");
		System.out.println(TAB + "Argument String: \"" + args[0] + "\"");
		System.out.println(TAB + "String Length: " + args[0].length());
	}
	
	@Override
	public void run() {
		Q16.main(args);
	}
	
}
