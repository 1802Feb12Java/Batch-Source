package com.revature.work;

import java.util.Date;

// import java.lang.Math;

public class Q14 {
	Integer num;
	String word;
	
	public void Work (int x ) {
	
		switch (x) {
		case 1: //square rooting num
			System.out.println(Math.sqrt(num)+" is the square root of "+num);
;			break;
		case 2: //display date
			Date date = new Date();
			System.out.println("Today is "+date.toString());
			break;
		case 3: // store string in array
			String[] book = new String[word.length()];
			book = word.split("");
			break;
		default:
			System.out.println("Hey!! Its only 3 choices");
			break;
		}
	}

	public Q14(Integer num, String word) {
		this.num = num;
		this.word = word;
	}
}
