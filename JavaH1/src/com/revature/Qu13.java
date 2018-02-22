package com.revature;

public class Qu13 {
	
	public Qu13() {
		
		System.out.println("Q13:");
		
		for(int i=0; i<4; i++) {
			
			switch(i) {
				case 0:
					System.out.println("0");
					break;
				
				case 1:
					System.out.println("1 0");
					break;
					
				case 2:
					System.out.println("1 0 1");
					break;
					
				case 3:
					System.out.println("0 1 0 1");
					break;
					
				default:
					break;
			}
			
		}
	}
}
