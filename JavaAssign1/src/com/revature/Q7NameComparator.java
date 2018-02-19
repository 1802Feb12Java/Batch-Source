package com.revature;

import java.util.Comparator;

public class Q7NameComparator implements Comparator<Q7Employee>{

	@Override
	public int compare(Q7Employee e1, Q7Employee e2) {
		String name1 = e1.getName();
		String name2 = e2.getName();
		
		return name1.compareTo(name2); //compares names and puts in alphabetical order
	}
}
