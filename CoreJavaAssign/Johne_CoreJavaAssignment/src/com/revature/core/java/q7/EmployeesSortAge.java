package com.revature.core.java.q7;

import java.util.Comparator;

/**
 * 
 * @author johne
 *
 */
public class EmployeesSortAge implements Comparator<Employees>{

	/**
	 * compare employee's age 
	 */
	@Override
	public int compare(Employees e1, Employees e2) {
		return e1.getAge() - e2.getAge();
	}

}
