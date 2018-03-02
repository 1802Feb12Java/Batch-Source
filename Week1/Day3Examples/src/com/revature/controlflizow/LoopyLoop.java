package com.revature.controlflizow;

import java.time.LocalTime;

public class LoopyLoop {

	// if
	public static void ifFun(boolean condition1, boolean condition2) {
		if (condition1 && condition2) {
			System.out.println("Both true.");
		} else if (condition1) {
			System.out.println("Condition 1 is true, but condition 2 is false.");
		} else if (condition2) {
			System.out.println("Condition 1 is false, but condition 2 is true.");
		} else {
			System.out.println("Both false.");
		}
	}

	// switch
	static String color = "blue";

	public static String whatColor() {
		String returnThis;

		switch (color) {
		case "green":
			returnThis = "not green";
			break;
		case "orange":
			returnThis = "not orange";
			break;
		default:
			returnThis = "any other color";
			break;
		}

		return returnThis;
	}

	public static void main(String[] args) {
		ifFun(false, true);
		System.out.println(whatColor());
	}

}