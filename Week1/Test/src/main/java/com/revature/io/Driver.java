package com.revature.io;

import java.util.ArrayList;

import com.revature.beans.Guitar;

public class Driver {

	public static void main(String[] args) {
		ArrayList<Guitar> guitarList = new ArrayList();
		Guitar gibson = new Guitar(1, "Les Paul", "Red", 1500.35);
		Guitar lucille = new Guitar(1, "es-335", "Black", 99999);
		System.out.println("Original:\t" + gibson.toString());

		guitarList.add(gibson);
		guitarList.add(lucille);

		// take Guitar object and save as serializable object in file "out.txt"
		// SerializeDemo.writeObject(gibson, "out.txt");

		SerializeDemo.writeObject(guitarList, "out.txt");

		// read in serializable object and cast to guitar
		// Guitar gib = (Guitar) SerializeDemo.readObject("out.txt");
		// System.out.println("Read File:\t" + gib.toString());

		ArrayList<Guitar> importedList = (ArrayList<Guitar>) SerializeDemo.readObject("out.txt");
		System.out.println("\nImported ArrayList:\n\t" + importedList);
		System.out.println(importedList.get(1).getCost());
	}

}