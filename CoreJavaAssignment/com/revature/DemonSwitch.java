package com.revature;

import java.util.Arrays;
import java.util.Date;

public class DemonSwitch 
{
	void doSomething(int i)
	{
		switch(i)
		{
		case 1:
			System.out.println(Math.sqrt(25));
			break;
		case 2:
			Date date = new Date(); //initialize date object
			System.out.println(date.toString());
			break;
		case 3:
			String learning = "I am learning Core Java";
			final int mid = Math.round(learning.length() / 2); //get the middle of the String
			String[] parts = {learning.substring(0, mid),learning.substring(mid)};
			System.out.println(Arrays.toString(parts));
			break;
		default:
			break;
		}
	}
}
