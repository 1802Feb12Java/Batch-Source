package com.revature.answers;

import java.util.Comparator;

public class Question7 implements Comparator<Employee> {

	public void run() {

		Employee bill = new Employee("Bill", "HR", 32);
		Employee matt = new Employee("Matt", "Trainer", 40);
		
		if(compare(bill, matt) < 0) {
			System.out.println(bill.toString() + " is before " + matt.toString());
		} else if(compare(bill, matt) > 0){
			System.out.println(matt.toString() + " is before " + bill.toString());
		} else {
			System.out.println("Both employees are the same.");
		}
	}

	@Override
	public int compare(Employee o1, Employee o2) {
		
		if(o1.getAge() < o2.getAge()) {						//I start the comparison with age
			return -1;										
		} else if(o1.getAge() > o2.getAge()) {
			return 1;
		} else {											//if they are the same age, move on to alphabetical names
			if(o1.getName().compareTo(o2.getName()) < 0) {
				return -1;
			}else if(o1.getName().compareTo(o2.getName()) > 0) {
				return 1;
			} else {										//if same name, move on to department
				if(o1.getDepartment().compareTo(o2.getDepartment()) < 0) {
					return -1;
				}else if(o1.getDepartment().compareTo(o2.getDepartment()) > 0) {
					return 1;
				}
				return 0;									//this case means that they have the exact same info.
			}
		}
		
	}

	
	

} 