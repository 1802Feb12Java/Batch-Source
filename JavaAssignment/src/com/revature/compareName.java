package com.revature;

import java.util.Comparator;

public class compareName implements Comparator<Q7>//compares name
{

	@Override
	public int compare(Q7 o1, Q7 o2) {
		 return o1.name.compareTo(o2.name);		
	}
	
}