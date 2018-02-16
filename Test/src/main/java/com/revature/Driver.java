package com.revature;

import java.util.ArrayList;

import com.revature.beans.Guitar;
import com.revature.io.SerializeDemo;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList <Guitar> guitarList= new ArrayList ();
		Guitar gibson= new Guitar(1,"Les Paul", "Red",1500.35);
<<<<<<< HEAD
		System.out.println(gibson.toString());
		SerializeDemo.writeObject(gibson, "out.txt");
		Guitar gib=(Guitar) SerializeDemo.readObject("out.txt");
		System.out.println(gib.getName());
=======
		Guitar lucille= new Guitar(1,"es-335", "black",16561500.35);
		guitarList.add(gibson);
		guitarList.add(lucille);
		//System.out.println(gibson.toString());
		SerializeDemo.writeObject(guitarList, "out.txt");
		//SerializeDemo.writeObject(lucille, "out.txt");
		//Guitar gib= (Guitar)SerializeDemo.readObject("out.txt");
		//System.out.println(gib.toString());
		@SuppressWarnings("unchecked")
		ArrayList<Guitar> git= (ArrayList<Guitar>)SerializeDemo.readObject("out.txt");
		System.out.println(git.get(1).getCost());
>>>>>>> 75e86c58ce6404bde7f4fa4ccb4e38a1ced1e338
	}

}
