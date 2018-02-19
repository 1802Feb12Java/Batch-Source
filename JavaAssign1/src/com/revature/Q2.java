package com.revature;

public class Q2 {  //prints out first 25 numbers of fibonacci sequence

	public void fibonacci() {
		int f1 = 1;
		int f2 = 1;
		int fib = 0;
		System.out.print(""+ fib + " " + f1 + " " + f2 + " ");
		
		for(int i = 3; i<25; i++) { //length of fib sequence decided here
			fib = f1 + f2;
			f1 = f2;
			f2 = fib;
			System.out.print("" + fib + " ");
		}
	}
	
}
