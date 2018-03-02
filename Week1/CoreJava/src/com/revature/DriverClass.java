package com.revature;

public class DriverClass {

	public static void main(String[] args) {

		// instantiate classes and run example methods

		System.out.println("Q1:");
		QuestionOne q1 = new QuestionOne();
		for (int i : q1.bubbleSort()) {
			System.out.println(i);
		}

		System.out.println("\nQ2:");
		QuestionTwo q2 = new QuestionTwo();
		q2.fibonnaci(25);

		System.out.println("\nQ3:");
		QuestionThree q3 = new QuestionThree();
		String reversedString = q3.reverseString("!sgwaD oG");
		System.out.println(reversedString);

		System.out.println("\nQ4:");
		QuestionFour q4 = new QuestionFour();
		System.out.println(q4.nFactorial(6));

		System.out.println("\nQ5:");
		QuestionFive q5 = new QuestionFive();
		System.out.println(q5.substringWithoutSubstring("0123456789", 3, 7));

		System.out.println("\nQ6:");
		QuestionSix q6 = new QuestionSix();
		System.out.println(q6.isEven(91));

		System.out.println("\nQ7:");
		QuestionSeven q7 = new QuestionSeven("Harold", "HR", 69);
		QuestionSeven q7Two = new QuestionSeven("Barbara", "Accounting", 48);
		System.out.println(q7.compare(q7Two, q7));
		System.out.println(q7.compare(q7, q7));
		System.out.println(q7.compare(q7, q7Two));

		System.out.println("\nQ8:");
		QuestionEight q8 = new QuestionEight();
		q8.palindrome();

		System.out.println("\nQ9:");
		QuestionNine q9 = new QuestionNine();
		q9.printPrimes();

		System.out.println("\nQ10:");
		QuestionTen q10 = new QuestionTen();
		System.out.println(q10.minimumValue(8, 24));

		System.out.println("\nQ11:");
		QuestionEleven q11 = new QuestionEleven();
		q11.printFloats();

		System.out.println("\nQ12:");
		QuestionTwelve q12 = new QuestionTwelve();
		q12.printEvens();

		System.out.println("\nQ13:");
		QuestionThirteen q13 = new QuestionThirteen();
		q13.printTriangle(4);

		System.out.println("\nQ14:");
		QuestionFourteen q14 = new QuestionFourteen();
		q14.testCases(3);

		System.out.println("\nQ15:");
		QuestionFifteen q15 = new QuestionFifteen();
		System.out.println(q15.addition(1, 2));
		System.out.println(q15.subtraction(8, 5));
		System.out.println(q15.multiplication(6, 8));
		System.out.println(q15.division(100, 10));

		System.out.println("\nQ16:");
		QuestionSixteen q16 = new QuestionSixteen();
		q16.printNumOfChars(args[0]);

		System.out.println("\nQ17:");
		QuestionSeventeen q17 = new QuestionSeventeen();
		q17.printCalculatedInterest();

		System.out.println("\nQ18:");
		QuestionEighteen q18 = new QuestionEighteen();
		System.out.println(q18.hasUppercaseLetters("coOl"));
		System.out.println(q18.toUpperCase("testing"));
		System.out.println(q18.add10("9"));

		System.out.println("\nQ19:");
		QuestionNineteen q19 = new QuestionNineteen();
		q19.doQuestion19();

		System.out.println("\nQ20:");
		QuestionTwenty q20 = new QuestionTwenty();
		q20.printFormattedArray();
	}

}