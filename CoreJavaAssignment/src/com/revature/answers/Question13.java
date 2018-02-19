package com.revature.answers;

public class Question13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		printTriangle(4);

	}

	public static void printTriangle(int num) {
		String cero = "0 ";
		String uno = "1 ";
		String blank = "";
		for(int i = 0; i < num; i++) {				//this loop checks for what I found to be the pattern.
			if(i%3 == 0) {							//I know there is probably an easier way to do this, but
				blank = cero + blank;				//I wasn't sure if hardcoding values into an array and then
			} else {								//printing them out was what you asked for. Plus, if this
				if(i == 1)							//is the patter, it could print for as long as you want it.
					blank = uno + blank;
				else
					blank += uno;
			}
			System.out.println(blank.trim());
		}
	}
	
}
