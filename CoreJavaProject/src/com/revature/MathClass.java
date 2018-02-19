package com.revature;

public class MathClass implements MathInterface{

	//example of simle math methods
	public static void example() {
		// TODO Auto-generated method stub
		
		MathClass math = new MathClass();
		
		System.out.println("5 + 5 = " + math.addition(5, 5));
		System.out.println("5 - 5 = " + math.subtraction(5, 5));
		System.out.println("5 * 5 = " + math.multiplication(5, 5));
		System.out.println("5 / 5 = " + math.division(5, 5));
		
	}
	
	//override addition, add implementation
	@Override
	public int addition(int num, int num2) {
		// TODO Auto-generated method stub
		return num + num2;
	}

	//override subtraction, add implementation
	@Override
	public int subtraction(int num, int num2) {
		// TODO Auto-generated method stub
		return num - num2;
	}

	//override multiplication, add implementation
	@Override
	public int multiplication(int num, int num2) {
		// TODO Auto-generated method stub
		return num * num2;
	}

	//override division, add implementation
	@Override
	public int division(int num, int num2) {
		// TODO Auto-generated method stub
		
		//I would rather not crash the program
		if(num2 == 0)
			return -1;
		
		return num / num2;
	}

}
