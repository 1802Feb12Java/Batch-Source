/*  Input and Output Streams
 * 
 *  Dominic Nguyen
 */

package com.revature.project1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class IO {
	
	private static final String inFile="Bank.txt";
	private static final String outFile="Bank.txt";
	
	public void writeOutputStreamContents(String[] contents, int i) {
		OutputStream os= null;
		File file = new File(outFile);
		
		try {
			os= new FileOutputStream(file,true);
			os.write(contents[i].getBytes());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(os!=null) {
			try {
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String readInputStreamContents() {
		File file =new File(inFile);
		InputStream is= null;
		StringBuilder s= new StringBuilder();
		
		try {
			is= new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		int b=0;
		
		try {
			while((b=is.read())!=-1) {
				char c = (char) b;
				s.append(c);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if( is!=null) {
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return s.toString();
	}
	
}