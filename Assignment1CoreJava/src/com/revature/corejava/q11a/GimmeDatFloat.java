package com.revature.corejava.q11a;

import com.revature.corejava.q11b.*;

public class GimmeDatFloat {
	private float receivedFloat1;
	private float receivedFloat2;
	
	public void getFloats() {
		TakeMyFloatPlease floatTest = new TakeMyFloatPlease(0.123f, 1.224f);
		receivedFloat1 = TakeMyFloatPlease.getFloat1();
		receivedFloat2 = TakeMyFloatPlease.getFloat2();
	}
	
	public void showReceivedFloats() {
		System.out.println(receivedFloat1);
		System.out.println(receivedFloat2);
	}
	
}
