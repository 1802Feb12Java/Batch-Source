package com.revature.arrays;

import java.util.Arrays;

import com.revature.hello.HelloWorld;

public class ArraysFun {
	public static void main(String[] args)
	{
		int[] arr = new int[3];
		HelloWorld hwarr[] = new HelloWorld[10];
		int[] arr2= {1,2,3,4,5,6,7,8,9,10};
		
		arr2[7]=5;
		
		System.out.println(arr2);
		System.out.println(arr2.length);

		
		foo(arr2);
		System.out.print("[");
		for(int a = 0; a<arr2.length; a++)
		{
			System.out.print(arr2[a]);
			if(a!=arr2.length-1)
				System.out.print(", ");
		}
		System.out.println("]");
		
		System.out.print("[");
		for(int a : arr2)
		{
			System.out.print(a+",");
		}
		System.out.println("]");
		
		System.out.println(Arrays.toString(arr2));
		Arrays.sort(arr2);
		System.out.println(Arrays.toString(arr2));
		
	}
	static void foo(int[] a)
	{
		a[4]=6;
	}
}
