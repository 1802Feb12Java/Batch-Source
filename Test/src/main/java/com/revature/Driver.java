package com.revature;

import java.util.ArrayList;

import com.revature.beans.Guitar;
import com.revature.io.*;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Guitar gibson= new Guitar(1,"Les Paul", "Red",1500.35);
		Guitar lucille = new Guitar(1, "es-335", "black", 1656161500.35);
		
		System.out.println(gibson.toString());
		//SerializeDemo.writeObject(gibson, "out.txt");
		//Guitar gib=(Guitar) SerializeDemo.readObject("out.txt");
		//System.out.println(gib.getName());
		//System.out.println(gib.getCost());
		//System.out.println(gib.hashCode());
		
		ArrayList<Guitar> guitarList = new ArrayList<Guitar>();
		guitarList.add(gibson);
		guitarList.add(lucille);
		
		SerializeDemo.writeObject(guitarList, "out.txt");
		@SuppressWarnings("unchecked")
		ArrayList<Guitar> newGuitarList =(ArrayList<Guitar>) SerializeDemo.readObject("out.txt");
		System.out.println(newGuitarList.get(0).getCost());
		System.out.println(newGuitarList.get(1).getCost());
		
	}

}
