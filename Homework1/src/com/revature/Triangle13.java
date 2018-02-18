package com.revature;

public class Triangle13 {

	public static void triangle() {
		
		String s = "";

		for(int i = 0 ; i < 4 ; ++i) {	// ensures a triangle of length 4
		    
			if(i % 2 == 0) {	// even cols get appended a 0
				
				s = "0 " + s;
			}else {				// else a 1
				
				s = "1 " + s;
			}
			
		    System.out.println(s); 
		}
	}
}
