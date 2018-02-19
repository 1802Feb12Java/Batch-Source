package com.revature;

import java.util.Comparator;

public class EmployeeAgeCompare implements Comparator<Employee>
{

	@Override
	public int compare(Employee e1, Employee e2) 
	{
		if(e1.getAge() > e2.getAge())
			return 1;
		else if(e1.getAge() < e2.getAge())
			return -1;
		else
			return 0;
	}

}