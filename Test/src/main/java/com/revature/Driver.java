package com.revature;

import com.revature.beans.Guitar;
import com.revature.io.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Guitar gibson= new Guitar(1,"Les Paul", "Red",1500.35);
		//System.out.println(gibson.toString());
		//SerializeDemo.writeObject(gibson, "out.txt");
		Guitar gib=(Guitar) SerializeDemo.readObject("out.txt");
		System.out.println(gib.getName());
	}

}
