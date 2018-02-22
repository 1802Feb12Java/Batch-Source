package com.revature;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import com.revature.bean.Jelly;

public class Basics {

	public static void main(String[] args) {

		
		
		/** Naming conventions:
		 *
		 * Classes and Projects - Pascal Case: capitalize the first letters of each word
		 *		Ex) FirstSecondThird
		 * Fields and Methods - Camel Case: 
		 *		Ex) firstSecondThird
 		 * Packages - lower case seperated by periods
		 *		Ex) first.second.third
		 * Constants - All Caps deliminated by underscores
		 * 		Ex) FIRST.SECOND.THIRD
		*/
		
		for(Constructor<?> x: Jelly.class.getConstructors())
			System.out.println(x);
		
			System.out.println("\n\n"+Jelly.class.getSuperclass()+"\n\n");
		
		
		for(Method x: Jelly.class.getMethods()) {
			x.setAccessible(true);
			System.out.println(x.isAccessible());
		}
		
	}

}
