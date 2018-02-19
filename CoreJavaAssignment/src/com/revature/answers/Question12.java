package com.revature.answers;

public class Question12 {

	public static void main(String[] args) {
		
		printEven();
		
	}

	public static void printEven() {
		int numArr[] = new int[100];
		for(int i = 0; i < 100; i++) {						//filling the Array with 1-100
			numArr[i] = i+1;
		}
		for(int i : numArr) {						
			/*if(i == 100) {								//makes sure I don't hit a null pointer
				return;
			}*/
			if(i%2 == 0) {							//even test.
				System.out.println(i);
			}
		}
	}
	
}
