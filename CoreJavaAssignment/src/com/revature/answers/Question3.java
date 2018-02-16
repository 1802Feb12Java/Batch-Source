package com.revature.answers;

public class Question3 {

	public static void main(String[] args) {
		
		String s = "Ferdinand";
		System.out.println(s);
		System.out.println(reversal(s));

		
	}
	
	public static String reversal(String gold) {
		int l = gold.length();

		for(int i = 0; i < l-1; i++) {						//in this loop, I add the letters of the string backwards
			gold += gold.substring(l-(i+1), l - (i));		//to the end of the string. This creates a string
		}													//that's double the original length and has the string
		gold += gold.substring(0, 1);						//forwards to backwards. Then I make a substring starting
															//where the last string ended to get a backwards string.
		gold = gold.substring(l);
		
		//System.out.println(gold);
		return gold;
	}

}
