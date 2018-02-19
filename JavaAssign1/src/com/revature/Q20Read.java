package com.revature;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Q20Read {
	private static final String inFile = "Data.txt"; //reads the file to be manipulated by another class
	
	public String readISContents() {
		File file = new File(inFile);
		InputStream is = null;
		StringBuilder s = new StringBuilder();
		try {
			is = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int b = 0;
		try {
			while((b=is.read())!=-1) {
				char c = (char) b;
				s.append(c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(is!=null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return s.toString();
	}
	
}
