package com.revature.corejava;

public class Question13 {
	public static void printTriangle() {
		for (int i = 0; i < 4; i++) {
			int lineIterator = i + 1;
			String line = "";
			while (lineIterator != 0) {
				int numberToAppend =  (lineIterator % 2 == 0) ? 1 : 0;
				line = line + " " + numberToAppend;
				lineIterator--;
			}
			System.out.println(line);
		}
	}
}
