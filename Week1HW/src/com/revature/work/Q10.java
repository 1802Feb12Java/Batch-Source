package com.revature.work;

public class Q10 {
	public void findMin (int x, int y) {
		// ternary statement: variable = condition?true:false
		int min = (x < y)? x : y;
		System.out.println("Between "+x+" and "+y+" , the minimum is "+min);
	}
}
