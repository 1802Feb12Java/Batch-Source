package com.revature;

import java.util.Comparator;

public class Q7AgeComparator implements Comparator<Q7Employee>{
	@Override
	public int compare(Q7Employee e1, Q7Employee e2) {

		return e1.getAge() - e2.getAge(); //compares ages and puts younger employee first
	}

}
