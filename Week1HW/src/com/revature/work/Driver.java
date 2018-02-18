package com.revature.work;

import java.util.ArrayList;
import java.util.Collections;
import java.io.IOException;

public class Driver {
	public static void main(String args[]) throws IOException {
		// Q1 
		int[] myArray = {1,0,5,6,3,2,3,7,9,8,4};
		Q1 pup= new Q1();
		pup.bubbleSort(myArray);
		pup.print(myArray);
		System.out.println("Q1 Successfully sorted the good old fashioned way!");
		
		//Q2
		Q2 fib = new Q2();
		fib.create(25); //nth number of fibonaccis. can be replaced
		fib.print();
		System.out.println("Q2 Succesful fibonaccis");
		
		//Q3
		String name = "Sonam"; //can change for different String to be reversed
		Q3 bub = new Q3();
		System.out.print(name+" reversed to ");
		bub.reverse("\n"+name);
		System.out.println("Q3 Succesful Reversal");
		
		//Q4
		int n = 5; //variable for the nth factorial
		Q4 Nums = new Q4();
		Nums.factorial(n);
		System.out.println("Q4 Sucessfully factoriallllized");
		
		//Q5
		String str = "Example"; //Variable getting passed to be substringed 
		Integer idx = 5; //will display from 0 to this number - 1
		Q5 sub = new Q5();
		System.out.println(sub.subWord(str,idx));
		System.out.println("Q5 Sucessfully SubStringed");
		
		//Q6
		Integer ex6 = 10; // variable being checked for even
		Q6 dumb = new Q6();
		System.out.println("Is "+ex6+" even: "+dumb.isEven(ex6));
		System.out.println("Q6 Successfully evened out");
		
		//Q7
		ArrayList<Q7Employee> em = new ArrayList<Q7Employee>();
		// creating new employees 
		em.add(new Q7Employee("Josh","Finance",27));
		em.add(new Q7Employee("Dave","Sales",32));
		Collections.sort(em, new Q7Sorted());
		System.out.println("Sorted by name:");
		for (int x = 0; x < em.size(); x++) {
			System.out.println(em.get(x));
		}
		System.out.println("Q7 successfully Compared");
		
		//Q8
		// strArr can be changed to add more Strings in it or decrease them
		String[] strArr = {"karan","madam","tom","civic","radar","jimmy","kayak","refer","billy","did"};
		Q8 drone = new Q8();
		drone.placeInArray(strArr);
		System.out.println("Q8 succesfully Palindroned");
		
		//Q9
		Q9 matey = new Q9();
		matey.isPrime(100); //change 100 with different value for new limit
		System.out.println("Q9 sucessfully Primed");
		
		//Q10
		Q10 piratey = new Q10();
		piratey.findMin(20, 15); //you can change input values for different number comparision
		System.out.println("Q10 Successfully Mined");
		
		//Q11
		Q11A birdie = new Q11A();
		birdie.print();
		System.out.println("Q11 Succesfully contacted");
		
		//Q12
		Q12 numb = new Q12();
		int tot = 100; // Change value for new limit
		numb.findEven(tot);
		System.out.println("Q12 Successfuly Printed");
		
		//Q13
		Q13 tri = new Q13();
		tri.triangle(1);	// starting point for the switch case display
		System.out.println("Q13 Successfully Trained");
		
		//Q14
		Q14 casa = new Q14(9,"I am Learning Core Java"); //Number for case 1 and String for case 3
		int choice = 2; // Choice are 1,2,3 for the 3 cases
		casa.Work(choice);
		System.out.println("Q14 Successfully Chosen");
		
		//Q15
		Q15 in = new Q15(); 
		// calling all implemented methods with hardcoded inputs. can be changed 
		in.addition(5, 3);
		in.substraction(6, 2);
		in.multiplication(2, 3);
		in.division(10, 3);
		System.out.println("Q15 Sucessfully implemented");
		
		//Q16
		String s1 = "Does this work"; // String can be changed for other checks
		Q16 chara = new Q16(s1);
		chara.noc();
		System.out.println("Q16 Successfully Displayed");
	
		//Q17"
		Q17 money = new Q17();
		money.loan();
		System.out.println("Q17 Successfully Counted");
		
		//Q18
		Q18A wow = new Q18A();
		String str1 = "Wise";
		String str2 = "chilli";
		String str3 = "150";
		System.out.println(str1+" has an upper case character: "+wow.anyUp(str1));
		System.out.println(str2+" changed to all upper case is "+wow.toUpper(str2));
		wow.toInt(str3); // input string should be number for method to work
		System.out.println("Q18 Succesfully Abstracted");
		
		//Q19
		Q19 Array19 = new Q19();
		int lim = 10;
		Array19.display(lim);
		System.out.println("Q19 Succesfully Listed");
		
		//Q20
		Q20 last = new Q20();
		last.reader();
		System.out.println("Longest debug ever but Q20 is outputted");
	}
}
