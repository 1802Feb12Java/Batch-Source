package com.revature.assignments;

import java.util.Comparator;

/**
 * Employee class uses nested, private comparator interface classes to sort by
 * field
 */
public class Employee {
	private String name;
	private String department;
	private int age;

	public Employee() {
		super();
		this.name = "";
		this.department = "";
		this.age = -1;
	}

	public Employee(String name, String department, int age) {
		super();
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static NameCompare getNameCompare() {
		return new NameCompare();
	}

	public static DepartmentCompare getDepartmentCompare() {
		return new DepartmentCompare();
	}

	public static AgeCompare getAgeCompare() {
		return new AgeCompare();
	}

	/**
	 * Comparator Class to compare employees by name
	 */
	private static class NameCompare implements Comparator<Employee> {

		@Override
		public int compare(Employee o1, Employee o2) {
			return o1.getName().compareTo(o2.getName());
		}

	}

	/**
	 * Comparator Class to compare employees by department
	 */
	private static class DepartmentCompare implements Comparator<Employee> {

		@Override
		public int compare(Employee o1, Employee o2) {
			return o1.getDepartment().compareTo(o2.getDepartment());
		}

	}

	/**
	 * Comparator Class to compare employees by age
	 */
	private static class AgeCompare implements Comparator<Employee> {

		@Override
		public int compare(Employee o1, Employee o2) {
			return o1.getAge() - o2.getAge();
		}

	}

}
