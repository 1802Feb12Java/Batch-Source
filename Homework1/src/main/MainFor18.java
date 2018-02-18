package main;

import java.util.Scanner;
import com.revature.Inherit18;

public class MainFor18 {

	public static void main(String[] args) {
		
		Inherit18 a = new Inherit18();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the string you want to check for upper case char: ");
		String execute1 = sc.nextLine();
		boolean sol = a.checkUpper(execute1);		// returns true if uppercase char is found, false otherwise
		
		System.out.println("checkUpper() returned: "+sol);
		
		System.out.println("Enter the string you want to convert to all upper: ");
		String execute2 = sc.nextLine();
		String sol2 = a.toUpper(execute2);	// returns string with all caps
		
		System.out.println("toUpper() returned: "+sol2);
		
		System.out.println("Enter the string you want to convert to int: ");
		String execute3 = sc.nextLine();
		a.toIntPlus(execute3);	// prints string as int + 10
	}

}
