package com.revature.homeworkfeb19;

//Q16. Write a program to display the number of characters for a 
//string input. The string should be entered as a command line argument 
//using (String [ ] args).


public class Question16 {

	static void argsTest(String[] args)
	{
		int a = 0;
		for (String str : args)
			a+=str.length();
		System.out.println(a);
		
	}
	
}
