package com.revature.homeworkfeb19;

//Q18. Write a program having a concrete ;
//subclass that inherits three abstract methods from a superclass.
//Provide the following three implementations in the subclass corresponding 
//to the abstract methods in the superclass:
	 
//1.     Check for uppercase characters in a string, 
	//and return ‘true’ or ‘false’ depending if any are found.
//2.     Convert all of the lower case characters to uppercase in the 
	//input string, and return the result.
//3.     Convert the input string to integer and add 10, 
	//output the result to the console.
//Create an appropriate class having a main method to test the above setup.


public class Question18Sub extends Question18 {

	@Override
	String uppers(String str) {
		for (char c : str.toCharArray())
		{
			if (Character.isUpperCase(c))
				return "True";
		}
		return "False";
	}

	@Override
	String toUp(String str) {
		str = str.toUpperCase();
		return str;
	}

	@Override
	void add10(String str) {
		int temp = 0;
		for (char c : str.toCharArray())
		{
			temp +=c;
		}
		System.out.println(temp+10);
		
	}

}
