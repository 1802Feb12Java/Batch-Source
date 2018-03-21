package com.revature.something;

import java.util.Arrays;

//Overloading
//The first thing we look for is:
		//Exact Match
		//Conversion -> Primitives/Objects cast themselves to other types.
		//Boxing -> Primitives/Objects will undergo Auto Boxing/UnBoxing
		//Varargs -> arguments wrapped into arrays.
/*			Method that will take a variable number of  arguments
			Vararg must be the last argument
			Can be of any type*/

public class Wrapper {
	 
	public static void main( String [] args) {
		//conversion 
		int a=4;
		//Boxing 
		 Double f=6.6;
		 System.out.println(addThis(a));
		 System.out.println(addThis(f));
		 
		// method("ROLL TIDE",a, f.intValue(),(int)f.doubleValue(),12);
		 method("ROLL TIDE");
		 method(5,6,7,3);
		 comparingPrimitivesAndWrappers();
		 Integer x= new Integer(5);
		 Integer y=5;
	}
 public static double addThis(double d) {
	 double answer=d+5;
	 return answer;
 }
 //Varargs
 public static void method(int...x)
	{
		System.out.println(Arrays.toString(x));
	}
 public static void method(String a,int...x)
	{
		System.out.println(a+Arrays.toString(x));
	}
 public static void comparingPrimitivesAndWrappers(){
		int intPrimitive = 5;
		Integer intObject = 5;
		short shortPrimitive = 5;
		Short shortObject = 5;
		long longPrimitive = 5L;
		Long longObject = 5l;
		float floatPrimitive = 5.0F;
		Float floatObject = 5.0f;
		double doublePrimitive = 5.0;
		Double doubleObject = 5.0;

		//unwraps the object to match the primitive to the object's value
		System.out.println("intPrimitive==intObject: "
					+(intPrimitive == intObject));
		//Compare memory locations of two objects
		System.out.println("intObject==new Integer(5): "
				+(intObject == new Integer(5)));
		//compare values of two objects
		System.out.println("intObject.equals(new Integer(5)): "
				+(intObject.equals(new Integer(5))));
		//comparing two primitives compares the value, regardless of order
		System.out.println("intPrimitive==shortPrimitive: "
				+(intPrimitive == shortPrimitive));
		System.out.println("shortPrimitive==intPrimitive: "
				+(shortPrimitive == intPrimitive));
		
		//cannot compare two different object types
		System.out.println("longObject.equals(intObject): "
				+(longObject.equals(intObject)));
		//retrieve the value from the wrapper.
		System.out.println(longObject.longValue()==intObject.intValue());
		
		Integer i= 5;
		Integer s=5;
		Integer q= new Integer(5);
		System.out.println("TEST "+ (i==s ));
		System.out.println("TEST2 "+ (i==q ));
		
	}
 
}
