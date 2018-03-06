package com.revature.string;

public class StringExample
{
	public static void main(String[] args) {
		String s = "Hello World";
		String s2 = "Hello World";
		System.out.println(s==s2);
		System.out.println(s.equals(s2));
		s2 = new String("Hello World");
		System.out.println(s==s2);
		System.out.println(s.equals(s2));
		s2= s2.intern();
		System.out.println(s==s2);
		
		s=s2.concat(s);
		System.out.println(s);
		s=s2+s2;
		System.out.println(s);
		
	}
}
