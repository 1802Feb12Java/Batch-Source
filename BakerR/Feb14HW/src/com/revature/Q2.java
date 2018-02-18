package com.revature;

import java.util.Stack;

class FibonacciGenerator {
    // Simple iterative calculation of fibonacci sequence; O(n)
    public static long fibonacci(int n) {
        boolean isEven = (n & 1) == 0;
        
        // Fibonacci for n < 0.
        if(n < 0) {
            long fib = fibonacci(-n);
            return isEven ? -fib : fib;
        }
        
        // Base cases.
        switch(n) {
        case 0:
            return 0;
        case 1:
            return 1;
        }
        
        // General case: F(k) = F(k-1) + F(k-2)
        long k$2Val = 0; // k_-2 value
        long k$1Val = 1; // k_-1 value
        long kVal = 1;
        for(int k = 2; k <= n; ++k, k$2Val = k$1Val, k$1Val = kVal) // k: index/counter
            kVal = k$2Val + k$1Val;
        
        return kVal;
    }
}

public class Q2 implements Runnable {
	private final int START, END;
	
	public Q2(int start, int end) {
		// Range: [start, end)
		START = start;
		END = end;
	}
	
	@Override
	public void run() {
		System.out.println("Question 2: Fibonacci Sequence");
		
		// Calculate the desired values.
		Stack<Long> fibVals = new Stack<>();
		for(int i = END; i-->START;) {
			fibVals.push(FibonacciGenerator.fibonacci(i));
		}
		
		System.out.print("    Fibonacci: ");
		if(!fibVals.isEmpty()) {
			System.out.print(fibVals.pop());
		}
		while(!fibVals.isEmpty()) {
			System.out.print(", " + fibVals.pop());
		}
		System.out.println();
	}
	
    
}
