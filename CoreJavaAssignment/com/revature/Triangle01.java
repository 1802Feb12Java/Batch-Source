package com.revature;

public class Triangle01 
{
	public Triangle01()
	{
		for(int i=1;i<=4;i++)
		{
			for(int b=1;b<=i;b++)
			{
				System.out.print(((i+b)%2) + " "); //add i + b and divide them by 2. print each result on the current line
			}
			System.out.println(); //create a new line after each line
		}
	}
}
