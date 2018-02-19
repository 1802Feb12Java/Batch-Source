package com.revature;

public class Q13 {
	/* 13. Display the triangle on the console as follows using any type of loop. Do NOT use a
	 simple group of print statements to accomplish this.
	0
	1 0
	1 0 1
	0 1 0 1
	*/
	
	public static void print(int max)
	{
		 boolean zero = true;//boolean to print 0
         for (int i = 0; i < max; i++)
         {
             for (int j = -1; j < i; j++)
             {
                 if (zero)
                     System.out.print("0 ");
                 else
                     System.out.print("1 ");
                 zero = !zero;
             }
             System.out.println(); //prints line break
         }
	}
}
