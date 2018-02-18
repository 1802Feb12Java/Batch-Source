package com.revature;

import java.math.BigInteger;
import java.util.Random;
import java.util.Stack;
import java.util.concurrent.Callable;

class FibonacciGenerator {
	private static class FibData<T extends Number> {
		public T n;
		public T k;
		public T fibK;
		public T fibK$1;
		
		public FibData() {
			n = null;
			k = null;
			fibK = null;
			fibK$1 = null;
		}
		
		public boolean isEven() {
			return (n.longValue() & 1) == 0;
		}
	}
	
	public static long fibonacci_nonrecursive(long n) {
		Stack<FibData<Long>> dataStack = new Stack<>();
		FibData<Long> data;
		
		// Traverse until n reaches a base case.
		Long prevK = n;
		do {// Unrolled recursion
			// New stack data.
			data = new FibData<>();
			data.n = prevK;
			
			if(data.n.equals(-1L)) {
				data.k = null;
				data.fibK = 1L;
				data.fibK$1 = 0L;
			} else if(data.n.equals(0L) || data.n.equals(1L)) {
				data.k = null;
				data.fibK = 0L;
				data.fibK$1 = 1L;
			} else if(data.n.equals(2L)) {
				data.k = null;
				data.fibK = 1L;
				data.fibK$1 = 1L;
			} else {
				data.k = data.n >> 1;
				data.fibK = null;
				data.fibK$1 = null;
				
			}
			
			// Push data onto the stack.
			dataStack.push(data);
			prevK = data.k;
		} while(data.k != null);
	}
	
	// fast doubling algorithm (Time complexity: theta(log(n))):
	// F(2k) = F(k)[2*F(k+1)-F(k)]
	// F(2k+1) = F(k+1)^2+F(k)^2
	public static long fibonacci(int n) {
		// n = 2k
		
		
		/////////////////////////////////////////////////////////////////////////
		// Base cases: These cases recurse themselves when using the equations.
		switch(n) {
//		case -2: // F(2*[k=-1]) = F(-1)[2*F(0)-F(-1)]
//			return -1;
		case -1: // F(2*[k=-1]+1) = F(0)^2 + F(-1)^2
			return 1;
		case 0: // F(2*[k=0]) = F(0)*[2*F(1)-F(0)]
			return 0;
		case 1: // F(2*[k=0]+1) = F(1)^2 + F(0)^2
			return 1;
		case 2: // F(2*[k=1]) = F(1)*[2*F(2)-F(1)]
			return 1;
//		case 3: // F(2*[k=1]+1) = F(2)^2 + F(1)^2
//			return 2;
		}
		
		boolean isEven = (n & 1) == 0;
		
		// n >> 1 is a fast version of integer n/2.
		// For odd numbers, n >> 1 is (n-1)/2.
		// >> used instead of >>> for sign-bit extension of negative numbers.
		int k = n >> 1;
		long fibK = fibonacci(k);
		
		long fibK$1 = fibonacci(k+1);
		
		
		long fib = isEven ? 
				fibK * ((fibK$1 << 1) - fibK) : // F(2k) = F(k)*[2*F(k+1) - F(k)]
				fibK$1*fibK$1 + fibK*fibK; // F(2k+1) = F(k+1)^2 + F(k)^2
		
		return fib;
	}
	
	private static final BigInteger NEG_ONE = BigInteger.valueOf(-1);
	private static final BigInteger TWO = BigInteger.valueOf(2);
	
	public static BigInteger fibonacci(BigInteger n) {
		BigInteger b = new BigInteger("0");
		
		if(n.equals(NEG_ONE)) {
			return BigInteger.ONE;
		} else if(n.equals(BigInteger.ZERO)) {
			return BigInteger.ZERO;
		} else if(n.equals(BigInteger.ONE)) {
			return BigInteger.ONE;
		} else if(n.equals(TWO)) {
			return BigInteger.ONE;
		}
		
		boolean isEven = !n.testBit(0);
		
		BigInteger k = n.shiftRight(1);
		
		
		return b;
	}
	
	// Simple iterative calculation of fibonacci sequence.
	public static long fibonacciSimple(int n) {
		boolean isEven = (n & 1) == 0;
		if(n < 0) {
			long fib = fibonacciSimple(-n);
			return isEven ? -fib : fib;
		}
		
		switch(n) {
//		case -1:
//			return 1;
		case 0:
			return 0;
		case 1:
			return 1;
		}
		
		long k$2Val = 0; // k_-2 value
		long k$1Val = 1; // k_-1 value
		long kVal = 1;
		for(int k = 2; k <= n; ++k, k$2Val = k$1Val, k$1Val = kVal) { // k: index/counter
			kVal = k$2Val + k$1Val;
		}
		
		return kVal;
	}
}

class TimingResult<T> {
	public long nanos;
	public T value; 
}

public class Q2 {

	public static void main(String[] args) {
		int[] input = new int[10];
		Random rand = new Random();
		for(int i = 0; i < 10; i++) {
			input[i] = rand.nextInt()/1000;
		}
		
		
		for(int i : input) {
			TimingResult<Long> fastDblTime, simpleTime;
			final Integer I = i;
			System.out.println("Test: [" + i + "]:");
			fastDblTime = measureNanoTiming(() -> {
				return new Long(FibonacciGenerator.fibonacci(I));
			});
			
			simpleTime = measureNanoTiming(() -> {
				return new Long(FibonacciGenerator.fibonacciSimple(I));
			});
			
			System.out.println("FastDouble: " + (fastDblTime.nanos/1.0e9) + "s");
			System.out.println("Simple: " + (simpleTime.nanos/1.0e9) + "s");
			if(!fastDblTime.value.equals(simpleTime.value)) {
				System.out.println("    Error: Fast [" + fastDblTime.value 
						+ "], Simple [" + simpleTime.value + "]");
			}
			System.out.println();
		}
		

	}
	
	public static <T> TimingResult<T> measureNanoTiming(Callable<T> r) {
		TimingResult<T> result = new TimingResult<>();
		long start, finish;
		
		start = System.nanoTime();
		try {
			result.value = r.call();
		} catch (Exception e) {}
		finish = System.nanoTime();
		result.nanos = finish - start;
		
		
		return result;
	}

}
