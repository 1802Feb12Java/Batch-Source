package com.revature;

import java.util.Arrays;
import java.util.Comparator;

class Sorter {
	// Bubble sort O(n^2)
	public static void bubbleSort(int[] ary, Comparator<? super Integer> cmp) {
		// Bubble through the list i times.
		for(int i = ary.length; i-->0;) {
			// Bubble larger values to the end of the array.
			for(int j = 0; j < i; ++j) {
				// Swap item j with j+1 if larger.
				if(cmp.compare(new Integer(ary[j]), new Integer(ary[j+1])) > 0) {
					int tmp = ary[j];
					ary[j] = ary[j+1];
					ary[j+1] = tmp;
				}
			}
		}
	}
	
	public static void bubbleSort(int[] ary) {
		// Redirect a default comparator to bubbleSort(int[], Comparator)
		bubbleSort(ary, (Integer a, Integer b) -> a.compareTo(b));
	}
}

public class Q1 {

	public static void main(String[] args) {
		int[] entry = {1,0,5,6,3,2,3,7,9,8,4};
		
		System.out.print("Default: ");
		Sorter.bubbleSort(entry);
		System.out.println(Arrays.toString(entry));
		
		System.out.print("Reversed comparison: ");
		Sorter.bubbleSort(entry, (Integer a, Integer b) -> b.compareTo(a));
		System.out.println(Arrays.toString(entry));
	}

	
	
}
