package q10.minimum;

public class Minimum {
	public void minimum()
	{
		int a = 0;
		int b = 1;
		int min = !(a<b) ? 0 : 1;
		if(min == 0)
		{
			System.out.println("a is the minimum number");
		}
		else
		{
			System.out.println("b is the minimum number");
		}
	}
}
