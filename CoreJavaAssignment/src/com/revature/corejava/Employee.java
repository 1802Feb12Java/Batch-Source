package com.revature.corejava;

public class Employee {
		String name;
		int age;
		String department;
		
		Employee() {
			super();
		}
		Employee(String name, int age, String department) {
			this.name = name;
			this.age = age;
			this.department = department;
		}
		@Override
		public String toString() {
		return "Name: " + this.name + "Age: " + this.age + "Department: " + this.department;
		}
}
