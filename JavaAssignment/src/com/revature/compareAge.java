package com.revature;

import java.util.Comparator;

public class compareAge implements Comparator<Q7> // compare age
{

	@Override
	public int compare(Q7 o1, Q7 o2) {
		if(o1.age < o2.age)
			return 1;
		else if (o1.age == o2.age)
			return 0;
		else
			return -1;	
		}
	
}