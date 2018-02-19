package com.revature;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public  class Q7  {

	//7. Sort two employees based on their name, department, and age using the
	//Comparator interface.
	
	String name;
	String department;
	 int age ;
	
	
	
	
	@Override
	public String toString() {
		return "Q7 [name=" + name + ", department=" + department + ", age=" + age + "]";
	}
	
	public Q7(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
	}
	// Collections.sort(list,new RevatureHW1.Comparison.Sort_Employee_Name());
	public static void displaySorted()
	{
		Q7 john = new Q7("John Smith", "Accountant", 47);
		Q7 jane = new Q7("Jane Doe", "Manager", 34);
		//create arraylist to store and sort
		ArrayList<Q7> employee = new ArrayList<>();
		employee.add(john);
		employee.add(jane);
        Collections.sort(employee, new compareDepartment());
        for(Q7 i : employee)
        {
        	System.out.println(i);
        }
        Collections.sort(employee, new compareName());
        for(Q7 i : employee)
        {
        	System.out.println(i);
        }
        Collections.sort(employee, new compareAge());
        for(Q7 i : employee)
        {
        	System.out.println(i);
        }
       
	}
}
