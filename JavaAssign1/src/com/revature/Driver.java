package com.revature;

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import com.revature.question11.*; //imports floats from another package


public class Driver {
	public static void main(String[] args) {
		Q1 question1 = new Q1();
		int[] myArray = {1,0,5,6,3,2,3,7,9,8,4};
		question1.bubbleSort(myArray);
		System.out.println("========================================================================");
		//^^when you see this, it means separation of questions
		
		System.out.println("\n");
		Q2 question2 = new Q2();
		question2.fibonacci();
		System.out.println("\n========================================================================");
		
		System.out.println("\n");
		Q3 question3 = new Q3();
		String word = "pineapple"; //the string here is reversed
		question3.reverseStr(word);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q4 question4 = new Q4();
		int n = 6;
		question4.factorial(n);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q5 question5 = new Q5();
		String newWord = "oranges"; int index = 3; //prints word from 0 to index 
		question5.createSubString(newWord,index);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q6 question6 = new Q6();
		question6.isEven(7);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q7Employee employees[] = new Q7Employee[2];
		Q7Employee employee1 = new Q7Employee("Sonam", "Revature", 22); //employee with data created here
		employees[0] = employee1; //employee put into array
		Q7Employee employee2 = new Q7Employee("Josh", "Not Revature", 25);
		employees[1] = employee2;
		Arrays.sort(employees, new Q7NameComparator()); //sorts by name and prints
		System.out.println("Order of employees after sorting by Name: ");
		for(int i = 0; i<employees.length; i++) {
			System.out.println(employees[i].getName() + "\t" + employees[i].getDepartment() + "\t" + employees[i].getAge());
		}
		Arrays.sort(employees, new Q7DptComparator()); //compare and prints by department
		System.out.println("\nOrder of employees after sorting by Department: ");
		for(int i = 0; i<employees.length; i++) {
			System.out.println(employees[i].getName() + "\t" + employees[i].getDepartment() + "\t" + employees[i].getAge());
		}
		Arrays.sort(employees, new Q7AgeComparator()); //compares age
		System.out.println("\nOrder of employees after sorting by Age: ");
		for(int i = 0; i<employees.length; i++) {
			System.out.println(employees[i].getName() + "\t" + employees[i].getDepartment() + "\t" + employees[i].getAge());
		}
		System.out.println("========================================================================");

		
		System.out.println("\n");
		Q8 question8 = new Q8();
		ArrayList<String> wordz= new ArrayList<String>() {{add("karen");add("madam");add("tom");add("civic");add("radar");add("jimmy");add("kayak");add("john");add("refer");add("billy");add("did");}};
		System.out.println("OG ArrayList: \n" + wordz.toString()); //^^words put into array here
		question8.getPalindromes(wordz);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q9 question9 = new Q9();
		question9.getPrimes();
		System.out.println("\n========================================================================");
		
		System.out.println("\n");
		Q10 question10 = new Q10();
		System.out.print("The minimum value is ");
		question10.ternary(8,2);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q11 question11 = new Q11();
		question11.useFloats(FloatVars.F1, FloatVars.F2);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q12 question12 = new Q12();
		question12.evenArray();
		System.out.println("\n========================================================================");
		
		System.out.println("\n");
		Q13 question13 = new Q13();
		question13.triangle();
		System.out.println("========================================================================");
		
		System.out.println("\n");
		int caseNum = 3;			//you can switch the case from 1 , 2 , 3
		Q14 question14 = new Q14();
		question14.switchCase(caseNum); 
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q15Implement question15 = new Q15Implement();
		int a = 4; int b = 7;
		question15.addition(a , b); //uses same ints for all methods
		question15.subtraction(a, b);
		question15.multiplication(a, b);
		question15.division(a, b);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q16 question16 = new Q16();
		String content;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter a word: "); //the whole code will stop here until you input a word
		content = sc.next();
		question16.countLetters(content);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q17 question17 = new Q17();
		question17.calcInterest(); //also uses scanner for input so code will pause here until inputted
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q18Concrete question18 = new Q18Concrete();
		String anotherOne = "nooo";
		System.out.println(question18.checkForUppercase(anotherOne));
		question18.convertToUppercase(anotherOne);
		question18.convertToIntThenAdd(anotherOne);
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q19 question19 = new Q19();
		question19.playWithNums();
		System.out.println("========================================================================");
		
		System.out.println("\n");
		Q20Output question20 = new Q20Output();
		question20.format();
	}
}
