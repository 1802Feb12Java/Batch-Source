package com.revature.homeworkfeb19;

import com.revature.Question11.*;

//Q11. Write a program that would access two float-variables 
//from a class that exists in another package. 
//Note, you will need to create two packages to demonstrate 
//the solution.


public class Question11 {
	{
		Question11Helper Q11H = new Question11Helper();
		System.out.println(Q11H.getA() +" "+Q11H.getB());
	}
}
