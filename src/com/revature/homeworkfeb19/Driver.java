package com.revature.homeworkfeb19;


public class Driver {

	public static void main(String[] args) {
		
		//Q01
		System.out.println("Question 01:");
		int n01[] = {1,0,5,6,3,2,3,7,9,8,4};
		Question01 Q01 = new Question01(n01);
		Q01.bubbleSort();
		System.out.println(Q01.toString());
		System.out.println("Question 01 END");
		System.out.println();
		
		//Q02
		System.out.println("Question 02:");
		Question02 Q02 =new Question02();
		Q02.fib();
		System.out.println("Question 02 END");
		System.out.println();
		
		//Q03
		System.out.println("Question 03:");
		Question03 Q03 = new Question03();
		System.out.println("Question 03 END");
		System.out.println();
		
		//Q04
		System.out.println("Question 04:");
		int n04 = 3;
		Question04 Q04 = new Question04();
		n04 = Q04.factorial(n04);
		System.out.println(n04);
		System.out.println("Question 04 END");
		System.out.println();
		
		//Q05
		System.out.println("Question 05:");
		System.out.println(Question05.substr("SUbString", 4));
		System.out.println("Question 05 End");
		
		
		//Q06
		System.out.println("Question 06:");
		int n06 = -146;
		System.out.println(Question06.testEven(n06));
		System.out.println("Question 06 END");
		System.out.println();
		
		//Q07
		System.out.println("Question 07:");
		Question07 Dave1 = new Question07("Dave", "Accounting", "37");
		Question07 Dave2 = new Question07("Dave", "Accounting", "37");
		Question07 Sally = new Question07("Sally", "HR", "27");
		Question07 Sandy = new Question07("Sandy", "Accounting", "37");
		System.out.println(Dave1.compare(Dave1, Dave2));
		System.out.println(Dave1.compare(Dave1, Sally));
		System.out.println(Dave1.compare(Dave1, Sandy));
		System.out.println(Dave1.compare(Sally, Dave2));
		System.out.println(Dave1.compare(Sally, Sandy));
		System.out.println();
		
		//Q08
		System.out.println("Question 08:");
		Question08 Q08 = new Question08();
		System.out.println("Question 08 END");
		System.out.println();
		
		//Q09
		System.out.println("Question 09:");
		Question09 Q09 = new Question09();
		Q09.printPrimes();
		System.out.println("Question 09 END");
		System.out.println();
		
		
		//Q10
		System.out.println("Question 10:");
		int n10a = 130;
		int n10b = 131;
		Question10 Q10 = new Question10(n10a, n10b);
		System.out.println(Q10.minimum());
		System.out.println("Question 10 END");
		System.out.println();
		
		//Q11
		System.out.println("Question 11:");
		Question11 Q11 = new Question11();
		System.out.println("Question 11 END");
		System.out.println();
		
		//Q12
		System.out.println("Question 12:");
		Question12 Q12 = new Question12();
		Q12.print();
		System.out.println("Question 12 END");
		System.out.println();
		
		//Q13
		System.out.println("Question 13:");
		Question13.triangle(4);
		System.out.println("Question 13 END");
		System.out.println();
		
		//Q14
		System.out.println("Question 14:");
		Question14.pick1to3(3);
		System.out.println("Question 14 END");
		System.out.println();
		
		//Q15
		System.out.println("Question 15:");
		Question15 Q15 = new Question15();
		System.out.println(Q15.addition(5, 9));
		System.out.println(Q15.subtraction(5, 9));
		System.out.println(Q15.multiplication(5, 9));
		System.out.println(Q15.division(5, 9));
		System.out.println("Question 15 END");
		System.out.println();
		
		//Q16
		System.out.println("Question 16:");
		Question16.argsTest(args);
		System.out.println("Question 16 END");
		System.out.println();
		
		//Q17 
		System.out.println("Question 17:");
		Question17 Q17 = new Question17();
		Q17.findInterest();
		System.out.println("Question 17 END");
		System.out.println();
		
		//Q18
		System.out.println("Question 18:");
		Question18Sub Q18 = new Question18Sub();
		System.out.println(Q18.uppers("YES"));
		System.out.println(Q18.uppers("no"));
		System.out.println(Q18.toUp("YoUr StRiNgS sHoUlD bE LoWeR cAsE"));
		Q18.add10("AdD tEn To Me PlEaSe!!!!!!");
		System.out.println("Question 18 END");
		System.out.println();
		
		//Q19 doesnt print non primes
		System.out.println("Question 19:");
		Question19 Q19 = new Question19();
		Q19.display();
		System.out.println(Q19.addEvens());
		System.out.println(Q19.addOdds());
		Q19.printNonPrimes();
		System.out.println("Question 19 End");
		System.out.println();
		
		//Q20
		System.out.println("Question 20:");
		Question20 Q20 = new Question20();
		System.out.println("Question 20 END");
		
		
		
		
		
		
		
		
		
	}

}


/*CORE JAVA ASSIGNMENT:
Due Monday, February 19, at 9am.
In STS, create one Java project for the homework with a separate class for each question, and use comments liberally in your code:Z
Push to your own branch
CREATE A DRIVER CLASS

Q1. Perform a bubble sort on the following integer array:  1,0,5,6,3,2,3,7,9,8,4
Q2. Write a program to display the first 25 Fibonacci numbers beginning at 0.
Q3. Reverse a string without using a temporary variable.  Do NOT use reverse() in the StringBuffer or the StringBuilder APIs.
Q4. Write a program to compute N factorial.
Q5. Write a substring method that accepts a string str and an integer idx and returns the substring contained between 0 and idx-1 inclusive.  Do NOT use any of the existing substring methods in the String, StringBuilder, or StringBuffer APIs.
Q6. Write a program to determine if an integer is even without using the modulus operator (%)
Q7. Sort two employees based on their name, department, and age using the Comparator interface.
Q8. Write a program that stores the following strings in an ArrayList and saves all the palindromes in another ArrayList.
“karan”, “madam”, ”tom”, “civic”, “radar”, “jimmy”, “kayak”, “john”,  “refer”, “billy”, “did”
Q9. Create an ArrayList which stores numbers from 1 to 100 and prints out all the prime numbers to the console.
==================================================================
Q10. Find the minimum of two numbers using ternary operators.
Q11. Write a program that would access two float-variables from a class that exists in another package. Note, you will need to create two packages to demonstrate the solution.
Q12. Write a program to store numbers from 1 to 100 in an array. Print out all the even numbers from the array. Use the enhanced FOR loop for printing out the numbers.
Q13. Display the triangle on the console as follows using any type of loop.  Do NOT use a simple group of print statements to accomplish this.
    0
    1 0
    1 0 1
    0 1 0 1
Q14. Write a program that demonstrates the switch case. Implement the following functionalities in the cases:java 
Case 1: Find the square root of a number using the Math class method.
Case 2: Display today’s date.
Case 3: Split the following string and store it in a string array.
               “I am learning Core Java”
Q15. Write a program that defines an interface having the following methods: addition, subtraction, multiplication, and division.  Create a class that implements this interface and provides appropriate functionality to carry out the required operations. Hard code two operands in a test class having a main method that calls the implementing class.
Q16. Write a program to display the number of characters for a string input. The string should be entered as a command line argument using (String [ ] args).
Q17. Write a program that calculates the simple interest on the principal, rate of interest and number of years provided by the user. Enter principal, rate and time through the console using the Scanner class.
Interest = Principal* Rate* Time
Q18. Write a program having a concrete ;subclass that inherits three abstract methods from a superclass.  Provide the following three implementations in the subclass corresponding to the abstract methods in the superclass:
 
1.     Check for uppercase characters in a string, and return ‘true’ or ‘false’ depending if any are found.
2.     Convert all of the lower case characters to uppercase in the input string, and return the result.
3.     Convert the input string to integer and add 10, output the result to the console.
Create an appropriate class having a main method to test the above setup.
Q19. Create an ArrayList and insert integers 1 through 10. Display the ArrayList. Add all the even numbers up and display the result. Add all the odd numbers up and display the result. Remove the prime numbers from the ArrayList and print out the remaining ArrayList.
Q20. Create a notepad file called Data.txt and enter the following:
Mickey:Mouse:35:Arizona
Hulk:Hogan:50:Virginia
Roger:Rabbit:22:California
Wonder:Woman:18:Montana
 
Write a program that would read from the file and print it out to the screen in the following format:
 
Name: Mickey Mouse
Age: 35 years
State: Arizona State
*/