package com.revature;

import java.util.Scanner;
import java.util.regex.Pattern;

// Simple Interest calculation
// I = P*R*T
class FinancialUtils {
	public static double simpleInterest(double principal, double rate, double yrs) {
		return principal * rate * yrs;
	}
}

class InvalidInputException extends Exception {
	private static final long serialVersionUID = 8243461703997615897L;
	private final String input;
	
	public InvalidInputException(String message, String input) {
		super(message);
		this.input = input;
	}

	public String getInput() {
		return input;
	}
}

@FunctionalInterface
interface InputProcessor<T> {
	T process(String input) throws InvalidInputException;
}

public class Q17 implements Runnable {
	private static final String TAB = "    ";
	
	@Override
	public void run() {
		Double principal, rate, years, interest;
		
		principal = requestFromUser("Enter principal:", (String in) -> {
			// EBNF: (Skipped whitespace)
			// CURRENCYSYM (DIGIT+ ('.' DIGIT+)?
			//    | '.' DIGIT+)
			//     
			Pattern p = Pattern.compile("\\p{Sc}\\s*(?<value>\d+)");
			Double p;
			
			
			
			return p;
		}, true);
	}
	
	/**
	 * Prints the request string, waits for user input, and processes
	 * the input using the given callback. If repeatOnFail is false
	 * and the input fails to process, null will be returned.
	 * @param request			The request string to print to the user.
	 * @param inputProcessor	The callback to process the input string.
	 * @param repeatOnFail		Repeats requests if the callback throws.
	 * @return	The return value of the callback if successful. If repeatOnFail
	 * 			is false and the callback throws, null is returned.
	 */
	public static <T> T requestFromUser(
			String request, InputProcessor<T> inputProcessor, 
			boolean repeatOnFail) {
		T inputResult = null;
		boolean failed;
		Scanner scan = new Scanner(System.in);
		
		do {
			System.out.print(TAB + request + " ");
			String input = scan.nextLine();
			
			try {
				inputResult = inputProcessor.process(input);
				failed = false;
			} catch(InvalidInputException ex) {
				System.out.println(TAB + "Given input [" + ex.getInput() + "] is not valid.");
				failed = true;
			}
		} while(repeatOnFail && failed);
		
		return inputResult;
	}

}
