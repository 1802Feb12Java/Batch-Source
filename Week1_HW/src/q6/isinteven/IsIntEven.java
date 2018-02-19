package q6.isinteven;

import java.util.Scanner;

public class IsIntEven 
{
	public void isIntEven()
	{
		final int positiveDivisor = 2;
		final int negativeDivisor = -2;
		boolean flag = true;
		boolean isEven = false;
		double dividend = 0;
		Scanner sc = new Scanner(System.in);
		boolean ifNotInt;
		do
		{
			ifNotInt = false;
			try
			{
				System.out.println("Enter a number:");
				dividend = sc.nextInt();
			}
			catch(Exception E)
			{
				ifNotInt = true;
				System.out.println("you have entered the wrong input nerd");
			}
		}while(ifNotInt);
		
		
		if(dividend < 0)
		{
			while(flag)
			{
				dividend /= negativeDivisor;
				if(dividend == negativeDivisor)
				{
					flag = !flag;
					isEven = true;
				}
				else if(dividend < 1)
				{
					flag = !flag;
				}
			}
		}
		else
		{
			while(flag)
			{
				dividend /= positiveDivisor;
				if(dividend == 1)
				{
					flag = !flag;
					isEven = true;
				}
				else if(dividend < 1)
				{
					flag = !flag;
				}
			}
		}
		System.out.println(isEven);
	}
}
