package com.revature;

import java.util.Comparator;

public class Q7NameComparator implements Comparator<Q7Employee> {

	@Override
	public int compare(Q7Employee e1, Q7Employee e2) {
		
		String e1Name = e1.getName();
		String e2Name = e2.getName();
		
		return e1Name.compareTo(e2Name);
	}
	
}
