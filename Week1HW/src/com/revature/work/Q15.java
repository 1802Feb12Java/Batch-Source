package com.revature.work;

public class Q15 implements In15{
	// overridding methods declared in interface
	@Override
	public void addition(int a, int b) {
		int sum = a + b;
		System.out.println(a+" + "+b+" results in "+sum);
	}

	@Override
	public void substraction(int a, int b) {
		int left = a - b;
		System.out.println(a+" - "+b+" results in "+left);
	}

	@Override
	public void multiplication(int a, int b) {
		int tot = a * b;
		System.out.println(a+" * "+b+" results in "+tot);
	}

	@Override
	public void division(int a, int b) {
		int rest = a / b;
		System.out.println(a+" / "+b+" results in "+rest);	
	}

}
