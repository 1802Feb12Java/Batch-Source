package com.revature.answers;

public class Question10 {

	public static void main(String[] args) {
		
		minVal(5, 10);
		minVal(200, 199);
		minVal(1, 100000);

	}
	
	public static void minVal(int x, int y) {
		int ans;
		ans = (x < y) ? x : y;					//checks the boolean of (x<y), if true it returns x, if false y
		System.out.println(ans);
	}

}
