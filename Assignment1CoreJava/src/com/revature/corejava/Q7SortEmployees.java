package com.revature.corejava;

import java.util.Comparator;

//Question 7: Sort two employees based on their name, department, and age using the Comparator interface.

class Employee {
	private String name;
	private String department;
	private Integer age;
	
	//constructor
	Employee(String name, String department, int age){
		this.name = name;
		this.department = department;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
}

public class Q7SortEmployees implements Comparator<Employee> {
	public int compare(Employee emp1, Employee emp2) {
		//get integer values for all three fields using compareTo
		int compareName = emp1.getName().compareTo(emp2.getName());
		int compareDepartment = emp1.getDepartment().compareTo(emp2.getDepartment());
		int compareAge = emp1.getAge().compareTo(emp2.getAge());;

		//compare customer names first
        if(compareName == 0) {
        	//if customer names are the same, compare departments
        	//if departments are the same, use age for sorting
            return ((compareDepartment == 0) ? compareAge : compareDepartment);
        }
        //if customer names are different, sort based off name
        else {
            return compareName;
        }		
	}		
}