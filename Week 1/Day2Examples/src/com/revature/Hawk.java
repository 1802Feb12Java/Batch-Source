package com.revature;



class Bird{
  { System.out.println("b1"); }   	 //Code Block executes upon instantiation

  Bird() {
 	 //super();
 	 System.out.println("b2");
  }

  static {   			 //Static Code Block executes only once when class is loaded
 	 System.out.println("b3");
  }
 	 
}



class Raptor extends Bird {
  static { System.out.println("r1"); }
  
  public Raptor() {
 	 //super();
 	 System.out.println("r2");
  }

  { System.out.println("r3"); }
  
  static { System.out.println("r4"); }
}


public class Hawk extends Raptor {

  public static void main(String[] args) {
 	 System.out.println("init");
 	 new Hawk();
 	 System.out.println("hawk");
 	 //new Hawk();
  }

  public Hawk() {
 	 //super();
  }
}


/*b3
r1
r4
init
b1   	 //Classes higher in the hierarchy will execute code blocks
b2   	 //then constructors first, then their children will execute their
r3   	 //code blocks and constructors next.
r2
hawk*/
