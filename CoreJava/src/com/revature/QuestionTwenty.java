package com.revature;

import java.io.*;

public class QuestionTwenty {

	// read from file
	public String readInputStreamContents() {
		File file = new File("Data.txt");
		InputStream is = null;
		StringBuilder s = new StringBuilder();

		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		int b = 0;

		try {
			while ((b = is.read()) != -1) {
				char c = (char) b;
				s.append(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return s.toString();
	}

	// split into individual words
	public String[] splitInput() {
		String[] individualElements = this.readInputStreamContents().trim().split("[\\r\\n:]");
		return individualElements;
	}

	public void printFormattedArray() {
		String[] formatThis = this.splitInput();

		// 5 elements per person including space
		int rows = formatThis.length / 5;

		for (int i = 0; i < rows; i++) {
			System.out.println("Name: " + formatThis[i * 5] + " " + formatThis[i * 5 + 1]);
			System.out.println("Age: " + formatThis[i * 5 + 2] + " years");
			System.out.println("State: " + formatThis[i * 5 + 3] + " state\n");
		}
	}
}