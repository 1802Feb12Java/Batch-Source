package com.revature.beans;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

	@Override
	public int compare(Student arg0, Student arg1) {
		// TODO Auto-generated method stub
		return (int) (arg0.getGpa() - arg1.getGpa());
	}

}
