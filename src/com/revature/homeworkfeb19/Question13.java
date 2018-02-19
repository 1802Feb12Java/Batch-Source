package com.revature.homeworkfeb19;

//Q13. Display the triangle on the console as follows using 
//any type of loop.  Do NOT use a simple group of print 
//statements to accomplish this.
//0
//1 0
//1 0 1
//0 1 0 1


public class Question13 {

//r0
//l1, r0
//l1, 0, r1
//l0, 1, 0 , r1


//str1 == str{empty}(concat 0)	line is odd, concat number
//str2 == 1 (concat str1)		line is even,concat prev str
//str3 == str2 (concat 1)		line is odd, concat number
//str4 == 0 (concat str3)		line is even,concat prev str
//str5 == str4 (concat 0)		line is odd, concat number
//str6 == 1 (concat str5)		line is even,concat prev str
	
//number starts at 0, then two '1', then two '0', etc. 

static void triangle(int height) {
	int line = 1;
	String num = "0";
	int timesNumPrinted = 1;
	String prevStr="";
	boolean left =false;
	
	if (height<1) {
		height=1;
		System.out.println("height set to one. Stop entering bad data!");
	}
	
	while(line<=height) {
		if (left)
		{
			left = false;
			prevStr = num + prevStr;
			timesNumPrinted += 1;
			if(timesNumPrinted == 2) {
				timesNumPrinted = 0;
				if (num == "0")
					num = "1";
				else
					num = "0";
			}
			line++;
			System.out.println(prevStr);
		}
		else
		{
			left = true;
			
			prevStr = prevStr + num;
			timesNumPrinted += 1;
			if(timesNumPrinted == 2) {
				timesNumPrinted = 0;
				if (num == "0")
					num = "1";
				else
					num = "0";
			}
			line++;
			System.out.println(prevStr);
		}
		
	}
	
}
	
	
}
