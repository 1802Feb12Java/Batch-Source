package com.revature;

public class Fibonacci
{
	 private int f1 = 0;
	 private int f2 = 1;
	 private int f3 = 0;   
	 
	 String calculateFibonacci(int count)
	 {    
		String fibonacciString = "Fibonacci Sequence:\n";
	    while(count>0)
	    {    
	         f3 = f1 + f2;    //f3 is equal to the sum of f1 and f2
	         f1 = f2;    //set f1 to the value of f2
	         f2 = f3;    //set f2 to the value of f3
	         fibonacciString += (" " + f3);   
	         count --;   
	     }    
	    return fibonacciString;
	 }    
}
