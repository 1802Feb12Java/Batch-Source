package com.revature.homeworkfeb19;

//Q17. Write a program that calculates the simple interest on the principal,
//rate of interest and number of years provided by the user. 
//Enter principal, rate and time through the console using the Scanner class.
//Interest = Principal* Rate* Time

import java.util.Scanner;

public class Question17 {
	
	private double intrest;
	private double rate;
	private int time;
	private double principal;
	Scanner sc = new Scanner(System.in);
	
	public void findInterest()
	{
		System.out.println("Principal:");
		this.principal = sc.nextDouble();
		System.out.println("Rate:");
		this.rate = sc.nextDouble();
		System.out.println("Time:");
		this.time = sc.nextInt();
		intrest = principal * rate * time;
		System.out.println(intrest + " is the interest");
	}
	
	
	

}
