package com.revature;

import java.util.Comparator;

public class Q7DptComparator implements Comparator<Q7Employee>{
	
	@Override
	public int compare(Q7Employee e1, Q7Employee e2) {
		String dpt1 = e1.getDepartment();
		String dpt2 = e2.getDepartment();
		
		return dpt1.compareTo(dpt2); //same compare method but with departments
	}

}
