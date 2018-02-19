package com.revature;

import java.util.Comparator;

public class QuestionSeven implements Comparator<QuestionSeven> {

	private String name;
	private String department;
	private int age;

	public QuestionSeven(String name, String department, int age) {
		super();
		this.name = name;
		this.department = department;
		this.age = age;
	}

	public int compare(QuestionSeven o1, QuestionSeven o2) {
		//combines name , department, and age
		String o1Value = o1.name + o1.department + o1.age;
		String o2Value = o2.name + o2.department + o2.age;

		//compares both strings
		if (o1Value.compareTo(o2Value) > 0) {
			return 1;
		} else if (o1Value.compareTo(o2Value) == 0) {
			return 0;
		} else {
			return -1;
		}
	}

}