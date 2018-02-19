package com.revature.io;

public class SerializeDemo {
	public static void main(String[] args) {
		IO io= new IO();
		io.writeOutputStreamContents(io.readInputStreamContents());
		System.out.println(io.readInputStreamContents());
	}
}
