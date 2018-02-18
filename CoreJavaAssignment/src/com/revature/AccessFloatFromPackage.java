package com.revature;

//import the class with flats
import com.revature.q11Floats.Q11Floats;

//Reads the float values of another class in a different package
public class AccessFloatFromPackage {
	static Q11Floats f = new Q11Floats();
	
	//print the floats
	public static void printFloats() {
		System.out.println(f.f1 + " " + f.f2);
	}
}
