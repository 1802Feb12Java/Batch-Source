package q17.calcinterest;

import java.util.Scanner;

public class CalcInterest {
	public void calcInterest()
	{
		double interest, principal, rate, time;
		Scanner sc = new Scanner(System.in);
		System.out.println("To calculate interest:");
		System.out.println("Enter Principal");
		principal = sc.nextDouble();
		System.out.println("Enter Rate");
		rate = sc.nextDouble();
		System.out.println("Enter number of years");
		time = sc.nextDouble();
		interest = principal * rate * time;
		System.out.println(interest);
		
	}
}
