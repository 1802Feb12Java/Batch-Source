package com.revature;

public class Driver {

	public static void main(String[] args) {
		System.out.println("Q1");	//pretty much does these same 2 things for each class
		Q1.run();			//except 15, because that specified to do something in the main function
		
		System.out.println("\nQ2");
		Q2.run();

		System.out.println("\nQ3");
		Q3.run();

		System.out.println("\nQ4");
		Q4.run();

		System.out.println("\nQ5");
		Q5.run();

		System.out.println("\nQ6");
		Q6.run();

		System.out.println("\nQ7");	
		Q7 q7 = new Q7();	//need to instantiate an object because it implements the Comparator interface and needs it
		q7.run();

		System.out.println("\nQ8");
		Q8.run();

		System.out.println("\nQ9");
		Q9.run();

		System.out.println("\nQ10");
		Q10.run();

		System.out.println("\nQ11");
		Q11.run();

		System.out.println("\nQ12");
		Q12.run();

		System.out.println("\nQ13");
		Q13.run();

		System.out.println("\nQ14");
		Q14.run();

		System.out.println("\nQ15");
		Q15 q15 = new Q15();
		double a = 1.5;		//this question apparently wants us to hard code values in the main class, so here you go
		double b = 4.2;
		System.out.println(a+" + "+b+" = " + q15.add(a, b));
		System.out.println(a+" - "+b+" = " + q15.sub(a, b));
		System.out.println(a+" * "+b+" = " + q15.mult(a, b));
		System.out.println(a+" / "+b+" = " + q15.div(a, b));

		System.out.println("\nQ16");
		Q16.run(args);	//it wants command-line input, so I'm passing args to q16.run() so it can work with them

		System.out.println("\nQ17");
		Q17.run();

		System.out.println("\nQ18");
		Q18 q18 = new Q18(); 	//need to instantiate to extend the abstract class, abstract methods can't be static apparently
		q18.run();

		System.out.println("\nQ19");
		Q19.run();

		System.out.println("\nQ20");
		Q20.run();
	}

}
