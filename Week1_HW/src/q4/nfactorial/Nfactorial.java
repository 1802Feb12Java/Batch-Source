package q4.nfactorial;

public class Nfactorial {
	public int nFactorial(int n)
	{
		if (n <= 1)
            return 1;
      else
            return n * nFactorial(n - 1); 
	}
}
