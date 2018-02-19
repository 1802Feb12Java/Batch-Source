package com.revature.core.java.q7;

import java.util.Comparator;

/**
 * 
 * @author johne
 *	This class compares two employee's name
 */
public class EmployeesSortName implements Comparator<Employees>{

	@Override
	public int compare(Employees e1, Employees e2) {
		return e1.getName().compareTo(e2.getName());
	}

}
