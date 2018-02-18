package com.revature.io;

public class SerializeDemo {

	public static void main(String[] args) {
		IO io = new IO();
		io.writeOutputStreamContents("Waluigi is the best character in the Mario series.\n");
		System.out.println(io.readInputStreamContents());
	}
}
