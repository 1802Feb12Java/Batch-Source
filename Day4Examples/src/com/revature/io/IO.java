package com.revature.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Writing to a file using an output stream example
 */
public class IO {

	// modularized file names
	public static final String inFile = "in.txt";
	public static final String outFile = "out.txt";

	public void writeOutputStreamContents(String contents) {

		File file = new File("src/com/revature/io/output.txt");
		try (OutputStream os = new FileOutputStream(file)) {

			os.write(contents.getBytes());
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String readInputStreamContents() {
		File file = new File("src/com/revature/io/input.txt");
		StringBuilder sb = new StringBuilder();

		try (InputStream is = new FileInputStream(file)) {
			int b = 0;
			while ((b = is.read()) != -1) {
				// cast byte
				char c = (char) b;
				// append into string builder to make our string
				sb.append(c);
			}

			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();

	}
}
