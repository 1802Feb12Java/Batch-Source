package q14.myswitch;
import java.lang.Math;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.Date;

public class MySwitch {
	public void mySwitch()
	{
		int input;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a value between 1 and 3");
		input = sc.nextInt();
		Date date = new Date();
		final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String learning = "I am learning Core Java";
		
		switch(input)
		{
			case 1:
			{
				System.out.println(Math.sqrt(2));
				break;
			}
			case 2:
			{
				System.out.println(sdf.format(date));
				break;
			}
			case 3:
			{
				String [] strArray = learning.split(" ");
				break;
			}
			default: System.out.println("Wrong Value!");
		}
	}
}
