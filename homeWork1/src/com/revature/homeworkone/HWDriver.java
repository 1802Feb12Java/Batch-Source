package com.revature.homeworkone;

import java.util.Arrays;

public class HWDriver {

	public static void main(String[] args) {
		
		
		//HomeWorkQuestion 1 (BubbleSort.java)
		System.out.println("Question #1 : " + Arrays.toString(BubbleSort.bubbleSort()));
		//HomeWorkQuestion 2 (FibNumGenerator.java)
		System.out.println("Question #2 : " + Arrays.toString(FibNumGenerator.genFibNum()));
		//HomeWorkQuestion 3
		System.out.println("Question #3 : " + Question3.getReverse("RollTide"));
		//HomeWorkQuestion 4
		System.out.println("Quesiton #4 : " + Question4.factorial(5));
		//HomeWorkQuestion 5
		System.out.println("Quesiton #5 : " + Question5.subStringQ("RollTide", 4));
		//HomeWorkQuestion 6
		System.out.println("Question #6 : " + Question6.isEven(79));
		//HomeWorkQuestion 7
		Question7 ans = new Question7();
		System.out.println("Question #7 : " + ans.answer().toString());
		//HomeWorkQuestion 8
		System.out.println("Question #8 : " + Question8.q8Answer().toString());
		//HomeWorkQuestion 9
		System.out.println("Question #9 : " + Question9.answer().toString());
		//HomeWorkQuestion 10
		System.out.println("Question #10 : " + Question10.answer(15, 12));
		//HomeWorkQuestion 11
		Question11.answer();
		//HomeWorkQuestion 12
		System.out.println("\nQuestion #12 : \n" + Question12.answer().toString());
		//HomeWorkQuestion 13
		Question13.answer();
		//HomeWorkQuestion 14
		Question14.answer();
		//HomeWorkQuestion 15
		System.out.println("\nQuestion #15 :");
		Question15 q15 = new Question15();
		System.out.println("20+10 = " + q15.addition(20, 10));
		System.out.println("20-10 = " + q15.subtraction(20, 10));
		System.out.println("20*10 = " + q15.multiplication(20, 10));
		System.out.println("20/10 = " + q15.division(20, 10));
		//HomeWorkQuestion 16
		Question16 q16 = new Question16();
		q16.answer(args);
		//HomeWorkQuestion 17
		Question17 q17 = new Question17();
		q17.answer();
		//HomeWorkQuestion 18
		Question18 q18 = new Question18();
		q18.answer();
		//HomeWorkQuestion 19
		Question19.answer();
		//HomeWorkQuestion 20
		Question20.answer();
		
		
		
		
		
	}
	
	
}
