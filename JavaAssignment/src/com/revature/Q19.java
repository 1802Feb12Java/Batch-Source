package com.revature;

import java.util.ArrayList;

public class Q19 {

	/*
	 * Create an ArrayList and insert integers 1 through 10. Display the ArrayList. Add all
the even numbers up and display the result. Add all the odd numbers up and display the
result. Remove the prime numbers from the ArrayList and print out the remaining
ArrayList.
	 */
	//function to initialize  and print integer arraylist
	public static ArrayList<Integer> initializeArrayList()
	{
		ArrayList<Integer> intArrayList = new ArrayList<>();
		//loop to initialize arraylist
		for (int i = 1; i <= 10; i++)
		{
			intArrayList.add(i);
		}
		//loop to print out arraylist
		for(int i : intArrayList)
		{
			System.out.print(i + " ");
			
		}
		System.out.println();
		return intArrayList;
	}
	public static void addSum(ArrayList<Integer> arrayList)
	{
		int evenSum = 0;
		int oddSum = 0;
		for (int i = 0; i < 10; i++)
		{
			if(arrayList.get(i) % 2 == 0)//check if value is even
			{
				evenSum += arrayList.get(i); //add up sum for even number
			}
			else// add odd number
			{
				oddSum += arrayList.get(i);//add odd numbers
			}
		}
		System.out.println("The sum of even numbers is " + evenSum); // prints even sum
		System.out.println("The sum of odd numbers is " + oddSum);//prints odd sum

	}
	//print prime numbers
	public static void primeNum(ArrayList<Integer> arrayList)
	{
		for(int i = 0; i < arrayList.size(); i++)
		{	
			if(isPrime(arrayList.get(i)))
				System.out.println("Non-prime numbers : " + i + " ");
			
	}
	}
	//function use to calculate prime numbers
	 public static boolean isPrime(int num)
       {
           if(num <= 3)
               return true;

           for(int i=2;i<=Math.sqrt(num);i++)
           {
               if(num%i==0)
                   return false;
           }

           return true;
       }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
