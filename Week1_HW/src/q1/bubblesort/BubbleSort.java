package q1.bubblesort;

import java.util.Arrays;

public class BubbleSort {
	private int [] numsToSort = {1,0,5,6,3,2,3,7,9,8,4};
	public void bubbleSort() 
	{
		boolean swap;
		do {
			swap = false;
			for(int i = 0; i < 10; i++) 
			{
				if(this.numsToSort[i] > this.numsToSort[i+1]) 
				{
					int temp;
					temp = this.numsToSort[i];
					this.numsToSort[i] = this.numsToSort[i+1];
					this.numsToSort[i+1] = temp;
					swap = true;
				}
			}
		}while(swap);
	System.out.println(Arrays.toString(numsToSort));
	
	}
}
