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
	private static final String outFile = "out.txt";
	
	public String readInputStreamContents(){
		
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
			while((b = is.read()) != -1){
				
				char c = (char) b;
				
				s.append(c);
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (is !=null){
			
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return s.toString();
		
	}
	
	public void writeOutputStreamContents(String contents){
		
		OutputStream os = null;
		
		File file = new File(outFile);
		
		try{
			
			os = new FileOutputStream(file, true);
			
		}
		
		catch (FileNotFoundException e){
			
			e.printStackTrace();
			
		}
		
		try{
			
			os.write(contents.getBytes());
			
		} catch (IOException e){
			
			e.printStackTrace();
			
		}
		
		if(os !=null){
			
			try{
				
				os.close();
				
			} catch(IOException e){
				
				e.printStackTrace();
				
			}
			
		}
		
	}

}
