package com.revature;

import java.util.Comparator;

public class Q7DepartmentComparator implements Comparator<Q7Employee> {

	@Override
	public int compare(Q7Employee e1, Q7Employee e2) {
		
		String e1Department = e1.getDepartment();
		String e2Department = e2.getDepartment();
		
		return e1Department.compareTo(e2Department);
	}

}
