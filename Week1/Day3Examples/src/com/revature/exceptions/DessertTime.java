package com.revature.exceptions;

public class DessertTime {
	public static void finallyExample() {
		int[] arr = new int[4];
		
		try {
			System.out.println("Trying code.");
		} catch (Exception e) {
			System.out.println("Caught exception");
		} finally {
			//always runs
			System.out.println("Finally.");
		}
	}
	
	public static void method2() throws Exception {
		throw new Exception("Ate too much pie!");
	}

	public static void main(String[] args) {
		finallyExample();
		try {
			method2();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}