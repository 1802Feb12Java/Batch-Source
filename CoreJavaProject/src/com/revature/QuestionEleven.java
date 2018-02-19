package com.revature;

import com.revature.question11.FloatClass;

public class QuestionEleven extends FloatClass{

	
	//example of how the public modifier gives us access to
	//variables from a class in another package
	public static void example() {
		// TODO Auto-generated method stub
		
		//just printing everything out
		System.out.println("QuestionEleven class has access to FloatClass's float's"
				+ " and they are in another package!?");
		
		System.out.println("Float1: " + float1);
		System.out.println("Float2: " + float2);

	}

}
