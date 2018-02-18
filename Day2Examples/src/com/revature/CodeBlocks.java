package com.revature;

public class CodeBlocks extends Joe {
	//instance code block that executes before constructor
	//when object is instantiated.  Note that we can't reference
	//the object using "this," since the object isn't instantiated yet.
	
//	{...}
	public static void main(String[] args) {
		System.out.println("init");
		new CodeBlocks();
		System.out.println("hawk");
	}
	//Static code blocks are similar.
	//These execute once, when the class is loaded in JVM.
	//These code blocks give us a window upon the creation of these objects.
	//We can use these code blocks to inject behavior when we instantiate objects.
	
//	static {...}
}

class Joe extends JoeSuper {
	static {System.out.println("r1");}
	Joe(){
		System.out.println("r2");
	}
	{System.out.println("r3");}
	static {System.out.println("r4");}
}

class JoeSuper {
	JoeSuper(){
		System.out.println("b2");
	}
	{System.out.println("b1");}
	static {System.out.println("b3");}
}