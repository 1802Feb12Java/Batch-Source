package com.revature.hello;

import com.revature.io.IO;

public class IOMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		IO io = new IO();
		
		System.out.println(io.readInputStreamContents());
		
		io.writeOutputStreamContents("whatever we want");

	}

}
