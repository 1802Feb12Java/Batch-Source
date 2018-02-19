package com.revature.homeworkfeb19;

import java.util.Comparator;

//Q7. Sort two employees based on their name, department, 
//and age using the Comparator interface.


public class Question07 implements Comparator<Question07> {
	
		private String name;
		private String dept;
		private String age;
		private int order = 0;
		
		
		
		public Question07(String name, String dept, String age) {
			super();
			this.name = name;
			this.dept = dept;
			this.age = age;
		}



		public int getOrder() {
			return order;
		}



		public void setOrder(int order) {
			this.order = order;
		}



		public String getName() {
			return name;
		}



		public void setName(String name) {
			this.name = name;
		}



		public String getDept() {
			return dept;
		}



		public void setDept(String dept) {
			this.dept = dept;
		}



		public String getAge() {
			return age;
		}



		public void setAge(String age) {
			this.age = age;
		}



		@Override
		public int compare(Question07 o1, Question07 o2) {

			if(o1.getName().compareTo(o2.getName()) == 1)
				return 1;
			else if (o1.getName().compareTo(o2.getName()) == -1)
				return -1;
			else if (o1.getDept().compareTo(o2.getDept()) == 1)
				return 1;
			else if(o1.getDept().compareTo(o2.getDept()) == -1)
				return -1;
			else if(o1.getAge().compareTo(o2.getAge()) == 1)
				return 1;
			else if(o1.getAge().compareTo(o2.getAge()) == -1)
				return -1;
			
			return 0;
		}



		
		

}
