package com.revature;

import java.math.BigInteger;

/**
 * Interface: addition, subtraction, multiplication, division
 * class implements interface
 * hard code 2 operands in test class
 */

interface BasicMathOperations {
	Number addition(Number a, Number b);
	Number subtraction(Number a, Number b);
	Number multiplication(Number a, Number b);
	Number division(Number a, Number b);
}

/**
 * Case Handling:
 *  a OR  b are Doubles
 *  	Convert both to Double and operate.
 *  a OR B are Floats
 *  	Convert both to Float and operate
 * 	a AND b are BigIntegers
 * 		convert Both to BigInteger and operate
 *  a is a BigInteger
 *  	convert b to BigInteger and operate
 *  b is a BigInteger
 *  	convert a to BigInteger and operate
 *  Otherwise, assume a AND b are primitives
 *  	convert both to Long and operate
 */
class MathOps implements BasicMathOperations {
	@Override
	public Number addition(Number a, Number b) {
		if(a instanceof Double || b instanceof Double) { 
			return a.doubleValue() + b.doubleValue();
		} else if(a instanceof Float || b instanceof Float ) {
			return a.floatValue() + b.floatValue();
		} else if(a instanceof BigInteger && b instanceof BigInteger) {
			return ((BigInteger) a).add((BigInteger)b);
		} else if(a instanceof BigInteger) {
			return ((BigInteger)a).add(BigInteger.valueOf(b.longValue()));
		} else if(b instanceof BigInteger) {
			return ((BigInteger)b).add(BigInteger.valueOf(a.longValue()));
		} else {
			return new Long((a.longValue() + b.longValue()));
		}
		
	}

	@Override
	public Number subtraction(Number a, Number b) {
		if(a instanceof Double || b instanceof Double) { 
			return a.doubleValue() - b.doubleValue();
		} else if(a instanceof Float || b instanceof Float ) {
			return a.floatValue() - b.floatValue();
		} else if(a instanceof BigInteger && b instanceof BigInteger) {
			return ((BigInteger) a).subtract((BigInteger)b);
		} else if(a instanceof BigInteger) {
			return ((BigInteger)a).subtract(BigInteger.valueOf(b.longValue()));
		} else if(b instanceof BigInteger) {
			return ((BigInteger)b).negate().add(BigInteger.valueOf(a.longValue()));
		} else {
			return new Long((a.longValue() - b.longValue()));
		}
	}

	@Override
	public Number multiplication(Number a, Number b) {
		if(a instanceof Double || b instanceof Double) { 
			return a.doubleValue()*b.doubleValue();
		} else if(a instanceof Float || b instanceof Float ) {
			return a.floatValue()*b.floatValue();
		} else if(a instanceof BigInteger && b instanceof BigInteger) {
			return ((BigInteger) a).multiply((BigInteger)b);
		} else if(a instanceof BigInteger) {
			return ((BigInteger)a).multiply(BigInteger.valueOf(b.longValue()));
		} else if(b instanceof BigInteger) {
			return ((BigInteger)b).multiply(BigInteger.valueOf(a.longValue()));
		} else {
			return new Long((a.longValue() * b.longValue()));
		}
	}

	@Override
	public Number division(Number a, Number b) {
		if(a instanceof Double || b instanceof Double) { 
			return a.doubleValue()/b.doubleValue();
		} else if(a instanceof Float || b instanceof Float ) {
			return a.floatValue()/b.floatValue();
		} else if(a instanceof BigInteger && b instanceof BigInteger) {
			return ((BigInteger) a).divide((BigInteger)b);
		} else if(a instanceof BigInteger) {
			return ((BigInteger)a).divide(BigInteger.valueOf(b.longValue()));
		} else if(b instanceof BigInteger) {
			return (BigInteger.valueOf(a.longValue()).divide((BigInteger)b));
		} else {
			return new Long((a.longValue() / b.longValue()));
		}
	}
}

public class Q15 implements Runnable {
	public static void main(String[] args) {
		final String TAB = "    ";
		System.out.println("Question 15: Math operations and Interfaces");
		
		// Test data
		final Number a = 10, b = 2.8;
		MathOps ops = new MathOps();
		
		// Value printout
		System.out.println(
				TAB + "Values: a[" + a.getClass().getName() + "] = " + a 
					+ "; b[" + b.getClass().getName() + "] = " +  b);
		// Tests
		System.out.println(TAB + "a + b = " + ops.addition(a, b));
		System.out.println(TAB + "a - b = " + ops.subtraction(a, b));
		System.out.println(TAB + "a * b = " + ops.multiplication(a, b));
		System.out.println(TAB + "a / b = " + ops.division(a, b));
	}
	
	@Override
	public void run() {
		Q15.main(null);
	}

}
