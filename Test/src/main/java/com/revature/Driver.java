package com.revature;

import java.util.ArrayList;
import com.revature.beans.Guitar;
import com.revature.io.*;

public class Driver {

	public static void main(String[] args) {
		
		ArrayList<Guitar> guitarList = new ArrayList<Guitar>();
		Guitar lucille = new Guitar(1,"es-335", "black", 1656161500.35);
		Guitar gibson= new Guitar(1,"Les Paul", "Red",1500.35);
		guitarList.add(lucille);
		guitarList.add(gibson);
		
		SerializeDemo.writeObject(guitarList, "out.txt");
		
		ArrayList<Guitar> git = (ArrayList<Guitar>)SerializeDemo.readObject("out.txt");
		System.out.println(git.get(1).getCost());
//		System.out.println(gibson.toString());
//		SerializeDemo.writeObject(gibson, "out.txt");
//		Guitar gib=(Guitar) SerializeDemo.readObject("out.txt");
//		System.out.println(gib.getName());
	}

}
