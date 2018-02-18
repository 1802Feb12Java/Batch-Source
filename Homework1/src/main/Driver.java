package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.revature.ArrayList19;
import com.revature.ArrayList9;
import com.revature.BubbleSort;
import com.revature.CountChars16;
import com.revature.Fact4;
import com.revature.Fib2;
import com.revature.Float11;
import com.revature.IsEven6;
import com.revature.ListEvens12;
import com.revature.Min10;
import com.revature.Palindrome8;
import com.revature.ReadFile20;
import com.revature.SimpleInterest17;
import com.revature.SortEmp7;
import com.revature.StringReverse3;
import com.revature.Substring5;
import com.revature.Switch14;
import com.revature.Triangle13;

public class Driver {

	public static float trial1 = 1.35f;
	public static float trial2 = 3.14159f;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// call to problem 1
		
		int[] sorted = BubbleSort.Sort();
		System.out.println("Solution to Problem 1: ");
		
		for(int i = 0; i < sorted.length; i++) {
			
			System.out.print(sorted[i]);
			System.out.print("   ");
		}
		
		System.out.println();
		
		// call to problem 2
		
		System.out.println("Solution to Problem 2: "+'\t');
	    Fib2.Fibonacci(new int[25]);
	    
	    System.out.println();
	    
	    // call to problem 3
	    
		System.out.println("Solution to Problem 3: "+'\t');
	    System.out.println("What string do you want reversed?");
	    
	    String word = sc.nextLine();
	    System.out.println(StringReverse3.reverse(word));
	    
	    System.out.println();
	    
	    // call to problem 4
	    
	    System.out.println("Solution to Problem 4: "+'\t');
	    System.out.println("What number do you want a factorial of?");
	    
	    int num = sc.nextInt();
	    System.out.println(Fact4.fact(num));
	    
	    System.out.println();
	    
	    // call to problem 5
	    
	    System.out.println("Solution to Problem 5: "+'\t');	  
	    System.out.println("What string do you want a substring of?");
	    String line = sc.next();
	    
	    System.out.println("Up till what index do you want the substring?");
	    int stop = sc.nextInt();
	    System.out.println(Substring5.sub(line, stop));
	    
	    System.out.println();
	    
	    // call to problem 6
	    
	    System.out.println("Solution to Problem 6: "+'\t');
	    
	    System.out.println("What num do you want to test for evenness?");
	    int tryIt = sc.nextInt();
	    
	    IsEven6.even(tryIt);
	    
	    System.out.println();
	    
	    // call to problem 7
	    
	    System.out.println("Solution to Problem 7: "+'\t');
	    SortEmp7.main(null);
	    
	    System.out.println();
	    
	    // call to problem 8
	    
	    System.out.println("Solution to Problem 8: "+'\t');
	    ArrayList<String> arr = new ArrayList<String>
		( 
			Arrays.asList("karan","madam","tom","civic","radar", "sexes",
						  "jimmy","kayak", "john", "refer","billy", "did"
			)
		);
	    System.out.println(Palindrome8.palindromes(arr));
	    
	    System.out.println();
	    
	    // call to problem 9
	    
	    System.out.println("Solution to Problem 9: "+'\t');
	    ArrayList9.printPrimes();
	    
	    System.out.println();
	    
	    // call to problem 10
	    
	    System.out.println("Solution to Problem 10: "+'\t');
	    System.out.println("What 2 nums do you want the min of?");
	    
	    int num1 = sc.nextInt();
	    int num2 = sc.nextInt();
	    
	    Min10.min(num1, num2);
	    
	    System.out.println();
	    
	    // call to problem 11
	    
	    System.out.println("Solution to Problem 11: "+'\t');
	    Float11.floatVisit();		// the floats are in this class
	    
	    System.out.println();
	    
	    // call to problem 12

	    System.out.println("Solution to Problem 12: "+'\t');
	    ListEvens12.printEvens();
	    
	    System.out.println();
	    
	    // call to problem 13
	    
	    System.out.println("Solution to Problem 13: "+'\t');
	    Triangle13.triangle();
	    
	    System.out.println();
	    
	    // call to problem 14
	    
	    System.out.println("Solution to Problem 14: "+'\t');
	    Switch14.switchState(2);
	    
	    System.out.println();
	    
	    // call to problem 15
	    
	    System.out.println("Solution to Problem 15: "+'\t');
	    RunInterface15.main(args);;
	    
	    System.out.println();
	    
	    // call to problem 16
	    
	    System.out.println("Solution to Problem 16: "+'\t');
	    CountChars16.count();
	    
	    System.out.println();
	    
	    // call to problem 17
	    
	    System.out.println("Solution to Problem 17: "+'\t');
	    SimpleInterest17.interest();
	    
	    System.out.println();
	    
	    // call to problem 18
	    
	    System.out.println("Solution to Problem 18: "+'\t');
	    MainFor18.main(null);
	    
	    System.out.println();
	    
	    // call to problem 19
	    
	    System.out.println("Solution to Problem 19: "+'\t');
	    ArrayList19.main(null);;
	    
	    System.out.println();
	    
	    // call to problem 20
	    
	    System.out.println("Solution to Problem 20: "+'\t');
	    ReadFile20.main(null);;
	    
	    System.out.println();
	}
}