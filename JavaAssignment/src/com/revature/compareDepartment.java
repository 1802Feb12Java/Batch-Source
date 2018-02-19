package com.revature;

import java.util.Comparator;

public class compareDepartment implements Comparator<Q7> //compares department
{

	@Override
	public int compare(Q7 o1, Q7 o2) {
		 return o1.department.compareTo(o2.department);		
	}
	
}