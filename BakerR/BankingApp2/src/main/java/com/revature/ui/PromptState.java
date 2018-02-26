package com.revature.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import com.revature.util.UnclosableInputStream;

public abstract class PromptState extends DisplayState {
	protected final InputStream userIn;
	
	/**
	 * Creates a prompt state with the given user input/output streams and initial
	 * next state.
	 * @param in	The stream to read user input from.
	 * @param dispStream	The stream to display output to.
	 * @param next	The initial next state.
	 */
	protected PromptState(InputStream in, PrintStream dispStream, DisplayState next) {
		super(dispStream, next);
		userIn = in;
	}
	
	/**
	 * Creates a prompt state with the given user input/output streams.
	 * The initial value of the next state is null. 
	 * @param in	Stream to read user input from.
	 * @param dispStream	Stream to display output to.
	 */
	protected PromptState(InputStream in, PrintStream dispStream) {
		this(in, dispStream, null);
	}
	
	/**
	 * Creates a prompt state in which the user input/output streams
	 * are defaulted to System.in and System.out. The initial next state
	 * is null.
	 */
	protected PromptState() {
		this(new UnclosableInputStream(System.in), System.out);
	}
	
	
	/**
	 * Prompts the user for input and returns it.
	 * @param message The message to display to the user.
	 * @return	The user's input.
	 */
	public String prompt(String message) {
		displayStream.print(message);
		displayStream.print(' ');
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(userIn);
		
		return scan.nextLine();
	}
	
	/**
	 * Closes streams.
	 */
	@Override
	public void close() {
		super.close();
		try {
			userIn.close();
		} catch(IOException ex) {}
	}
}
