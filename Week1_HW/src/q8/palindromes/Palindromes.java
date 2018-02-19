package q8.palindromes;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class Palindromes {
	public void palindromes()
	{
		String a = "karan" , b = "madam", c = "tom",
				d = "civic", e = "radar", f = "jimmy",
				g = "kayak", h = "john", i = "refer",
				j = "billy", k = "did";
		ArrayList<String> aList = new ArrayList<>();
		for(int inc = 0; inc < 11; inc++)
		{
			if(inc == 0)
			{
				aList.add(a);
			}
			else if(inc == 1)
			{
				aList.add(b);
			}
			else if(inc == 2)
			{
				aList.add(c);
			}
			else if(inc == 3)
			{
				aList.add(d);
			}
			else if(inc == 4)
			{
				aList.add(e);
			}
			else if(inc == 5)
			{
				aList.add(f);
			}
			else if(inc == 6)
			{
				aList.add(g);
			}
			else if(inc == 7)
			{
				aList.add(h);
			}
			else if(inc == 8)
			{
				aList.add(i);
			}
			else if(inc == 9)
			{
				aList.add(j);
			}
			else if(inc == 10)
			{
				aList.add(k);
			}
		}
		StringBuilder SB = new StringBuilder();
		ArrayList<String> listOfPalindromes = new ArrayList<>();
		
		for(int inc = 0; inc < aList.size(); inc++)
		{
			String strAppend = aList.get(inc);
			SB.append(strAppend);
			StringBuilder reverse = SB.reverse();
			if(strAppend.equals(reverse.toString()))
			{
				listOfPalindromes.add(strAppend);
			}
			SB.delete(0,strAppend.length());
		}
		System.out.println(listOfPalindromes.toString());
	}
}
