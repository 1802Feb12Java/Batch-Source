package com.revature.io;

import java.io.*;

public class IO {

	private static final String IN_FILE = "in.txt";
	private static final String OUT_FILE = "output.txt";

	public void writeOutputStreamContents(String contents) {
		OutputStream os = null;
		File file = new File(OUT_FILE);

		try {
			os = new FileOutputStream(file, true);
			os.write(contents.getBytes());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public String readInputStreamContents() {
		File file = new File(IN_FILE);
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

}