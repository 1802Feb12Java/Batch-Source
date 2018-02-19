package q18.stringmanipulation;

import java.util.Scanner;

public class CodifyString extends StringManipulation{

	@Override
	public boolean checkUppercase() {
		String str;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter a string with lowercase or uppercase letters");
		str = sc.nextLine();
		for(int i = 0; i < str.length(); i++)
		{
			if(str.charAt(i) < 'a')
			{
				System.out.println("The String has uppercase letters");
				return true;
			}
			else System.out.println("The string has all lowercase letters");
			
		}
		return false;
		
	}

	@Override
	public void convertLowercaseToUppercase() {
		String str;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter lowercase letters to convert to uppercase letters");
		str = sc.nextLine();
		String str2 = str.toUpperCase();
		System.out.println(str2);
	}

	@Override
	public void convertStringToInt() {
		String str;
		Scanner sc = new Scanner(System.in);
		System.out.println("Convert String to number and add 10 to get result");
		str = sc.nextLine();
		int result = Integer.parseInt(str);
		result += 10;
		System.out.println(result);
		
	}
	
}
