package com.revature.exceptions;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Except extends Exception{
	
	public Except(String message)
	{
		super(message);
	}
	public Except()
	{
		super("I'm too lazy to write a message");
	}
	public static void main(String[] args)
	{
		//firstExample();
		//secondExample();
		customExceptionExample();
	}
	private static void customExceptionExample() {
		
		try {
			method4();
		} catch (Except e) {
			e.printStackTrace();
		}
	}
	private static void secondExample() {
		try {
			method1();
			method2();
		}
		catch(IOException|ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void firstExample()
	{
		try {
			method1();
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			System.out.println("Hello from the finally.");
		}

		try {
			method2();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		method3();
		System.out.println("Successfully ran main method.");
	}
	public static void method1() throws Exception, FileNotFoundException
	{
		System.out.println("Hello from method 1");
		//throw new Exception("Too much pie. Am full");
	}
	public static void method2() throws IOException
	{
		System.out.println("Hi from method2");
		throw new IOException("Method 2 is also full of pie.");
	}
	public static void method3()
	{
		System.out.println("Hi from Method 3");
		double x = 0;
		x = 5.0/0.0;
		System.out.println(x);
	}
	public static void method4() throws Except
	{
		throw new Except("Message");
	}
}
