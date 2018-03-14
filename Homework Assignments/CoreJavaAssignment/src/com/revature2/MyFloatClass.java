package com.revature2;

public class MyFloatClass {
	/*
	 * The purpose of this class is to demonstrate accessing variables from
	 * a class in a different package. This is the class that holds the 
	 * variables that another class in another package will access.
	 * This is a POJO.
	 */
	private static float tau = new Float(6.2832);
	private static float pi = new Float(3.1415);
	private float lameFloat;
	
	public MyFloatClass(float lameFloat) {
		super();
		this.lameFloat = lameFloat;
	}

	public static float getTau() {
		return tau;
	}

	public static void setTau(float tau) {
		MyFloatClass.tau = tau;
	}

	public static float getPi() {
		return pi;
	}

	public static void setPi(float pi) {
		MyFloatClass.pi = pi;
	}

	public float getLameFloat() {
		return lameFloat;
	}

	public void setLameFloat(float lameFloat) {
		this.lameFloat = lameFloat;
	}

	@Override
	public String toString() {
		return "MyFloatClass [lameFloat=" + lameFloat + "]";
	}
	
	
	
	
}//end class
