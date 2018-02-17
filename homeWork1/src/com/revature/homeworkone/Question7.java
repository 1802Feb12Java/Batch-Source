package com.revature.homeworkone;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Question7 implements Comparator<Employee>{

	
	//Question7 ans = new Question7();
	
	public List<Employee> answer() {
		Employee gabe = new Employee("gabe", "CS", 29);
		Employee john = new Employee("john", "CE", 27);
		
		int i = this.compare(gabe, john);
		List<Employee> out = new ArrayList<>();
		if (i >= 0) {
			out.add(gabe);
			out.add(john);
		} else {
			out.add(john);
			out.add(gabe);
		}
		
		return out;
	}
	
	@Override
	public int compare(Employee o1, Employee o2) {
		//System.out.println((o1.getName().compareTo(o2.getName())));
		//System.out.println(o1.getDepartment().compareTo(o2.getDepartment()));
		//System.out.println(o1.getAge() > o2.getAge());
		if (o1.getName().compareTo(o2.getName()) < 0) {
			return 1;
		} else if ((o1.getName().compareTo(o2.getName()) > 0)) {
			return -1;
		}
		
		if (o1.getDepartment().compareTo(o2.getDepartment()) < 0){
			return 1;
		} else if (o1.getDepartment().compareTo(o2.getDepartment()) > 0){
			return -1;
		}
		
		if (o1.getAge() > o2.getAge()){
			return 1;
		} else if(o1.getAge() < o2.getAge()) {
			return -1;
		}
		
		return 0;
	}

}

class Employee{
	
	private String name;
	private String department;
	private int age;
	
	
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employee(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
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

	@Override
	public String toString() {
		return "Employee [name=" + name + ", department=" + department + ", age=" + age + "]";
	}
	
	
	
	
	
	
}