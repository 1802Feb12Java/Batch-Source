package com.revature;

import java.util.Arrays;

public class BubbleSort 
{
	String bubbleSort(int[] passedArr) //accept an array of elements
	{
	    boolean willSwap = true;
	    for(int i = passedArr.length - 1; (i > 0) && willSwap; i--) //in our program, we only need to swap and  decrement i
	    															//when the largest element in the unsorted array isn't on the right
	    															//So, we add a conditional check to our for loop decrementor
	    {
	    	willSwap = false; //stops the loop when the array is finished being sorted
	    	
	    	// for the unsorted part of the array, bubble the largest element to the right.
	        for (int b = 0; b < i; b++) //here is where the actual swap occurs, in our nested loop
	        {
	            if (passedArr[b] > passedArr[b+1]) //we check one element ahead of b to see if it's greater than b
	            {
	            // swap
	                int temp = passedArr[b];  //hold the value of arr[b] in a temporary variable
	                passedArr[b] = passedArr[b+1]; //reassign arr[b] to arr[b+1] 's value
	                passedArr[b+1] = temp;  //assign arr[b+1] to arr[b]'s original value, held in a temporary holder
	                willSwap = true; //continue to swap
	            }
	        }
	    }
	    
	    return Arrays.toString(passedArr);
	}	
}
