package q19.addremoveprime;

import java.util.ArrayList;

public class AddRemovePrime {
	public void addRemovePrime()
	{
		
		ArrayList<Integer> myList = new ArrayList<>();
		ArrayList<Integer> even = new ArrayList<>();
		ArrayList<Integer> odd = new ArrayList<>();
		for(int i = 0; i < 10; i++)
		{
			Integer inc = i+1;
			myList.add(inc);
		}
		System.out.println(myList);
		for(Integer anInt: myList)
		{
			if(anInt.intValue()%2 == 0)
			{
				even.add(anInt);
			}
			else
			{
				odd.add(anInt);
			}
		}
		int resultEven = 0;
		int resultOdd = 0;
		for(Integer anEven: even)
		{
			resultEven += anEven.intValue();
		}
		for(Integer anOdd: odd)
		{
			resultOdd += anOdd.intValue();
		}
		System.out.println("Even "+ resultEven);
		System.out.println("Odd "+ resultOdd);
		for(int i = 0; i < myList.size(); i++)
		{
	        boolean isPrime = true;

	
	        for (int j=2; j<i; j++)
	        {

	            if(i%j==0)
	            {
	                isPrime = false;
	                break;
	            }
	        }
	     
	
	        if(isPrime)
	        {
	        	myList.remove(i);
	        }

	            
	    }
		System.out.println("notPrime " + myList.toString());
		
	}
}
