package q9.primenumbers;
import java.util.ArrayList;

public class PrimeNumbers {
	public void primeNumbers()
	{
		ArrayList<Integer> nums = new ArrayList<>();
		for(int i = 0; i < 100; i++)
		{
			nums.add(i+1);
		}
		for(int i=0; i<100; i++)
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

	            System.out.println(i);
	    }
	}
	
}

