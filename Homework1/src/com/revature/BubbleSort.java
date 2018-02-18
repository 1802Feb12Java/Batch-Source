package com.revature;

public class BubbleSort {
	
	public static int[] Sort(){
		
		int[] toBeSorted= {1,0,5,6,3,2,3,7,9,8,4};
		int i = 0;
		
		while(true) {
			
			if(i == toBeSorted.length - 1) {	// stop if pivot is last element
				
				return toBeSorted;
			}
			
			int temp = 0;
			int j = i+1;	// value compared to pivot
			
			while(j < toBeSorted.length) {	// checks the increment of j
				
				if(toBeSorted[i] > toBeSorted[j]) {		//swap if in descending order
					
					temp = toBeSorted[j];
					toBeSorted[j] = toBeSorted[i];
					toBeSorted[i] = temp;
				}
			
				j++;
			}
			
			i++;
		}
		
		
	}
}
