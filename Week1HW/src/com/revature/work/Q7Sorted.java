package com.revature.work;

import java.util.Comparator;

public class Q7Sorted implements Comparator<Q7Employee> {
	// sorting employees listed by name
	public int compare(Q7Employee e1, Q7Employee e2) {
		return e1.name.compareTo(e2.name);
	}
}
