package com.revature.hello;

import com.revature.beans.Bike;
import com.revature.beans.Delorean;
import com.revature.beans.TimeMachine;
import com.revature.generic.Calculator;

public class HelloWorld {
	public static void main(String[] args) {
		// System.out.println("Hello World");
		/*
		 * Start of comment end of comment
		 */
		HelloWorld hw = new HelloWorld();
		System.out.println(hw.message);
		
		/*HelloWorld hw2 = new HelloWorld("Hello World");
		System.out.println(hw2.message);*/
		hw = new HelloWorld("Hello World");
		//System.out.println(hw.message);
		hw.printMessage();
		hw.scope(5);
		//System.out.println(x);
		hw.message="Goodbye";
		hw.printMessage();
		
		System.out.println(HelloWorld.m);
		
		TimeMachine tm = new TimeMachine();
		
		System.out.println("Speed of time machine " + tm.getSpeed());
		
		tm.drive(65);
		
		System.out.println("Speed of time machine " + ((Delorean)tm).getSpeed());
		
		Delorean d = new TimeMachine();
		
		d.drive(60);
		
		((TimeMachine)d).travelThroughTime();
		
		System.out.println("Speed of delorean " + d.getSpeed());
		
		doStuffWithBike(d);
		doStuffWithBike(tm);
		
		
		Calculator<Float> cf = new Calculator<Float>();
		
		System.out.println(cf.add(5.5f, 6.3f));
		
		Calculator<Integer> ci = new Calculator<Integer>();
		
		System.out.println(ci.add(4, 12));
		
		/*Calculator<TimeMachine> ctm = new Calculator<TimeMachine>();
		
		System.out.println(ctm.add(tm, tm));*/
		
		
		
	}
	
	public static void doStuffWithBike(Bike b){
		
		b.changeGears(5);
		
		
		
	}

	public static String m = "m";
	public String message = "Hello World";

	public HelloWorld() {
		//super();
		this("No message");
	}

	public HelloWorld(String message) {
		/*
		 * The compiler automatically calls super();
		 *  if super() or this() are not called
		 */
		this.message = message;
	}
	
	public void printMessage()
	{
		System.out.println(message);
	}
	
	public void scope(int x)
	{
		//fun times
		System.out.println(x);
	}
}
