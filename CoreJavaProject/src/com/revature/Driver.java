package com.revature;

public class Driver {

	public static void main(String[] args) {
		
		format(1);
		BubbleSort.example();
		
		format(2);
		Fibonacci.example();
		
		format(3);
		Reverse.example();
		
		format(4);
		Factorial.example();
		
		format(5);
		SubString.example();
		
		format(6);
		Even.example();
		
		format(7);
		Employee.example();
		
		format(8);
		Palindrome.example();
		
		format(9);
		Prime.example();
		
		format(10);
		TernaryMinimum.example();
		
		format(11);
		QuestionEleven.example();
		
		format(12);
		PrintEvenQ12.example();
		
		format(13);
		BinaryTriangle.example();
		
		format(14);
		SwitchStatement.example();
		
		format(15);
		MathClass.example();
		
		format(16);
		StringInput.example(args);
		
		format(17);
		Interest.example();
		
		format(18);
		StringManipulation.example();
		
		format(19);
		QuestionNineteen.example();
		
		format(20);
		FileInput.example();
	}
	
	//simple function to help format each answer
	public static void format(int questionNumber) {
		System.out.println("------------------------------------------------------");
		System.out.println("Question " + questionNumber + ": ");
	}

}
