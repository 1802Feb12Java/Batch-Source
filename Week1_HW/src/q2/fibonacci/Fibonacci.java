package q2.fibonacci;

public class Fibonacci {
	public void fibonacci() 
	{
		
		int num = 0;
		int num2 = num + 1;
		for (int i = 0; i < 25; i++)
		{
			
			System.out.print(num + " ");
		    int fibonacci = num + num2;
		    num = num2;
		    num2 = fibonacci;
		    
		    
		}
		
		
	}
}

