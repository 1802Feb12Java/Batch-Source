package com.revature;

public class FibonacciGenerator {

	public static void main(String[] args) {
		
//		fibonacciList(1);
//		fibonacciList(2);
//		fibonacciList(5);
		fibonacciList(25);

	}

	public static void fibonacciList(int a) {
		//argument specifies how many fibonacci numbers are to be returned
		if(a == 1) {
			System.out.println(0);
		} else if (a == 2) {
			System.out.println(0 + ", "+ 1);
		} else if (a > 2) {
			int i = 0;
			int f1 = 0;
			int f2 = 1;
			System.out.print(0 + ", "+ 1);
			while(i <= a) {
				System.out.print(", " + (f1 + f2));
				int f3 = f1 + f2;
				f1 = f2;
				f2 = f3;
				i++;
			}//end while
		} else {
			System.out.println("");
		}
		
	}
	
}
