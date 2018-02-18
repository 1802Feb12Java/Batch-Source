package com.revature.corejava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Question19 {
	
	public static void arrayListFun() {
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		System.out.println("Filling out initial array list");
		for (int i = 1; i < 11; i++) {
			arrayList.add(i);
		}
		System.out.println("Contents of array list is:");
		System.out.println(Arrays.toString(arrayList.toArray()));
		Iterator<Integer> i = arrayList.iterator();
		int sumOfEvens = 0;
		while (i.hasNext()) {
		   Integer number = i.next();
		   if (number % 2 == 0) {
				sumOfEvens += number;
			}
		   if (PrimeFinder.isPrime(number)) {
			   i.remove();   
		   }
		}
		System.out.println("Sum of Evens is: " + sumOfEvens);
		System.out.println("After primes removed, contents of array list is:");
		System.out.println(Arrays.toString(arrayList.toArray()));
	}

}
