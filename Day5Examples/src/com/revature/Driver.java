package com.revature;

import java.io.Serializable;
import java.util.ArrayList;

import com.revature.beans.Guitar;
import com.revature.io.SerializedDemo;

public class Driver {

	public static void main(String[] args) {

		// make a guitar list
		ArrayList<Guitar> guitarList = new ArrayList<Guitar>();

		Guitar gibson = new Guitar(1, "Les Paul", "Red", 1500.35);
		Guitar lucille = new Guitar(1, "asdf", "Blue", 122.2);

		// add guitars to list
		guitarList.add(gibson);
		guitarList.add(lucille);

		// System.out.println(gibson.toString());
		// write objects to file
		SerializedDemo.writeObject(guitarList, "out.txt");

		// read serialized object
		Serializable gib = SerializedDemo.readObject("out.txt");

		// type casting
		if (gib instanceof Guitar)
			System.out.println("It's a Guitar obj! " + (Guitar) gib);

		if (gib instanceof ArrayList<?>) {
			ArrayList<?> list = (ArrayList<?>) gib;
			System.out.println("It's an ArrayList! " + list);
			if (list.get(0) instanceof Guitar)
				System.out.println("It's an ArrayList<Guitar>!" + ((Guitar) list.get(0)).getColor());
		}

		// print out the unserialized file
		System.out.println(gib);
	}

}
