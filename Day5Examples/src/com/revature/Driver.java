package com.revature;

import java.util.ArrayList;

import com.revature.beans.Guitar;
import com.revature.io.copy.SerializeDemo;

public class Driver {

	
	public static void main(String[] args) {
		
		Guitar gibson = new Guitar(1, "Les Paul", "Red", 1500.35);
		Guitar lucille = new Guitar(3, "es-335", "Black", 5161335150.35);
		ArrayList<Guitar> axes = new ArrayList();
		axes.add(gibson);
		axes.add(lucille);
		System.out.println(gibson.toString());
		SerializeDemo.writeObject(axes, "out.txt");
		//SerializeDemo.writeObject(lucille, "out.txt");
		//Guitar gib = (Guitar) SerializeDemo.readObject("out.txt");
		//System.out.println(gib.getColor());
		
		ArrayList<Guitar> shred = (ArrayList<Guitar>) SerializeDemo.readObject("out.txt");
		System.out.println(shred.get(0).getCost());
		System.out.println(shred.get(1).getCost());

		
	}
}
