package com.revature.arrays;

import java.util.Arrays;

public class FunWithArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int s=7;
		// int array w/ 7 "buckets"
		int[] myArray= new int[s];
		//int array with hardcoded values
		int[] chaos= {7,6,5,8,7,255,52,2};
		
		//System.out.println(myArray);
		System.out.println(myArray[1]);
		
		for(int i=0;i<s;i++) {
			myArray[i]=i*5;
			//System.out.println(i+" element is "+ myArray[i]);
		}
		
		for(int i:myArray) {
			System.out.println(i);
		}
		System.out.println(Arrays.toString(chaos));
		Arrays.sort(chaos);
		System.out.println(Arrays.toString(chaos));
	}
}
