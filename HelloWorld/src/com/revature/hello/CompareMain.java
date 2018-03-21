package com.revature.hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.revature.beans.Student;
import com.revature.beans.StudentComparator;

public class CompareMain {

	public static void main(String[] args) {
		List<Student> studentList = new ArrayList<Student>();

		studentList.addAll(Arrays.asList(
				new Student[] { new Student(4, "Billy", 2.4), 
						new Student(7, "Jason", 4.0),
						new Student(3, "Richard", 0.6), 
						new Student(5, "Jennifer", 5.0) }));

		System.out.println(studentList);
		
		for(Student s : studentList){
			
			System.out.println(s);
			
		}
		
		System.out.println("=================================");
		
		Collections.sort(studentList);
		
		for(Student s : studentList){
			
			System.out.println(s);
			
		}
		
		System.out.println("=================================");
		
		Collections.sort(studentList, new StudentComparator());
		
		for(Student s : studentList){
			
			System.out.println(s);
			
		}
		
		System.out.println("=================================");
		
		Collections.sort(studentList, (arg0, arg1) -> {return arg0.getName().compareTo(arg1.getName());});
		
		for(Student s : studentList){
			
			System.out.println(s);
			
		}

	}

}
