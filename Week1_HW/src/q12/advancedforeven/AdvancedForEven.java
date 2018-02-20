package q12.advancedforeven;
import java.util.ArrayList;

public class AdvancedForEven {
	public void advancedForEven()
	{
		ArrayList<Integer> listOfNums = new ArrayList<>();
		ArrayList<Integer> evenNums = new ArrayList<>();
		for(int i = 0; i < 100; i++)
		{
			listOfNums.add(i+1);
		}
		for(int i = 0; i < 100; i++)
		{
			int isEven = (int) listOfNums.get(i);
			if(isEven%2 == 0)
			{
				evenNums.add(isEven);
			}
		}
		//Enhanced for loop used to print all of the even numbers
		for(Integer evenInt: evenNums)
		{
			System.out.println(evenInt);
		}
	}
}
