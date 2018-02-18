package com.revature;

import java.util.Scanner;

public class SimpleInterest17 {

	public static void interest() 
    {
        float p, r, t;
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the Principal : ");
        p = sc.nextFloat();
        
        System.out.print("Enter the Rate of interest : ");
        r = sc.nextFloat();
        
        System.out.print("Enter the Time period : ");
        t = sc.nextFloat();
        
        float si;
        si = (p * r * t) / 100;
        
        System.out.print("The Simple Interest is : " + si);
    }
}
