package com.revature;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

// Simple Interest calculation
// I = P*R*T
class FinancialUtils {
	public static double simpleInterest(double principal, double rate, int yrs) {
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
		Double principal, rate;
		Integer years;
		InputProcessor<Double> dblProcessor = (String in) -> {
			Double d;
			try {
				d = Double.parseDouble(in);
			} catch(NumberFormatException ex) {
				throw new InvalidInputException("Input could not be parsed into a Double.", in);
			}
			return d;
		};
		
		System.out.println("Question 17: Simple Interest Calculation with User-Input");
		
		principal = requestFromUser("Enter principal:", dblProcessor, true);
		rate = requestFromUser("Enter interest rate (decimal format):", dblProcessor, true);
		years = requestFromUser("Enter elapsed time (years):", (String in) -> {
			Integer yrs;
			
			try {
				yrs = Integer.parseInt(in); 
			} catch(NumberFormatException ex) {
				throw new InvalidInputException("Input could not be parased into an Integer.", in);
			}
			return yrs;
		}, true);
		
		System.out.println(TAB + "Total Interest: " + FinancialUtils.simpleInterest(principal, rate, years));
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
		try(Scanner scan = new Scanner(new InputStream() {
			private final InputStream input = System.in;
			
			@Override
			public int available() throws IOException {
				return input.available();
			}
			
			@Override
			public void close() {
				// Do nothing; keep System.in open.
			}
			
			@Override
			public void mark(int readlimit) {
				input.mark(readlimit);
			}
			
			@Override
			public boolean markSupported() {
				return input.markSupported();
			}
			
			@Override
			public int read() throws IOException {
				return input.read();
			}
			

			@Override
			public int read(byte[] b) throws IOException {
				return input.read(b);
			}
			
			@Override
			public int read(byte[] b, int off, int len) throws IOException {
				return input.read(b, off, len);
			}
			
			@Override
			public void reset() throws IOException {
				input.reset();
			}
			
			@Override
			public long skip(long n) throws IOException {
				return input.skip(n);
			}
			
		})) {
			do {
				System.out.print(TAB + request + " ");
				String input = scan.nextLine();
				
				try {
					inputResult = inputProcessor.process(input);
					failed = false;
				} catch(Exception ex) {
					if(ex instanceof InvalidInputException) {
						System.out.println(TAB + "Given input [" + ((InvalidInputException)ex).getInput() 
								+ "] is not valid.");
					} else {
						System.out.println(TAB + "Unspecified exception has occurred:");
					}
					
					System.out.println(TAB + ex.getMessage());
					failed = true;
				}
			} while(repeatOnFail && failed);
		} // end try
		return inputResult;
	}

}
