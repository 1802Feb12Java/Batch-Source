package com.revature;

public class Wrapperz {
<<<<<<< HEAD

	//Wrappers allow primitive data types to be treated as objects
	//utility methods
	
	public static void main(String[] args) {
		funky();
	}
	public static Integer i = new Integer(10);
	public static Integer j=new Integer(10);
	
	static public void funky() {
		if(i.equals(j)) {
			System.out.println("Roll Tide!");
		} else {
		System.out.println("Well, dern!");
		}
	}
=======
 // wrappers allow primitives to be treated as object
	//utility methods
	public static void main(String[] args) {
		funky();
	}
	public static Integer i=new Integer(10);
	public  static Integer j=new Integer(10);
	static public void funky() {
		if(i.equals(j)) {
			System.out.println("Roll Tide!");
		}else {
		System.out.println("well, dern");
		}
	}

>>>>>>> origin/KnightenM
	
	
}
