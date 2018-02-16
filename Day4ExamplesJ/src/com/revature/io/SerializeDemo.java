package com.revature.io;

public class SerializeDemo {

	public static void main(String[] args) {
		IO io = new IO();
		io.writeOutputStreamContents("Roll Tide!");
		System.out.println(io.readInputStreamContents());
		

	}

}
