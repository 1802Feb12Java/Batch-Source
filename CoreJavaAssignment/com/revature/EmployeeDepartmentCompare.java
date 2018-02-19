package com.revature;

import java.util.Comparator;

public class EmployeeDepartmentCompare implements Comparator<Employee>
{

	@Override
	public int compare(Employee emp1, Employee emp2) 
	{
		return emp1.getDepartment().compareTo( emp2.getDepartment() );
	}

}