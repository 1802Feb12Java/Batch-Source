package com.revature;

//import java.lang.Object.Bird;

public class Raptor extends Bird {
	static { System.out.println("r1");}
	public Raptor() {
		//super()
		System.out.println("r2");
	}
	{System.out.println("r3");}
	static{System.out.println("r4");}
}
