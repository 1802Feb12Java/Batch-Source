package com.revature.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {

	private static final String inFile = "in.txt";
	private static final String outFile = "output.txt";
	
	public void writeOutputStreamContents(String contents) {
		OutputStream os = null;
		File file = new File(outFile);
		try {
			
			os = new FileOutputStream(file, true);
			os.write(contents.getBytes());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//FileNotFoundException catch block must go first, because it is a subclass
		//of IOException, so IOException would catch the error first and FileNotFound
		// will not catch the error that could occur in our second line in the try block
		
		//Exceptions should always go from MOST detailed to LEAST detailed
		
		if(os != null) {
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}//end writeOutputStreamContents method
	
	
	public String readInputStreamContents() {
		File file = new File(inFile);
		InputStream is = null;
		StringBuilder s = new StringBuilder();
		
		try {
			is = new FileInputStream(file);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		int b = 0;
		try {
			while ((b = is.read())!= -1) {
				char c = (char) b;
				s.append(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if(is != null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return s.toString();
		
	}//end readInputStreamContents method
	
	
	
	
}//end class
