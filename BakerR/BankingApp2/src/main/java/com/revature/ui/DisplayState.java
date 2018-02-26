package com.revature.ui;

import java.io.Closeable;
import java.io.PrintStream;

public abstract class DisplayState implements Closeable {
	private DisplayState nextState;
	protected final PrintStream displayStream;
	
	/**
	 * Creates a display state with next state as null and the default display output.
	 */
	protected DisplayState() {
		this(null);
	}
	
	/**
	 * Creates a display state with the given next state and the display output
	 * defaulted to System.out.
	 * @param next	The initial value of the next state.
	 */
	protected DisplayState(DisplayState next) {
		this(System.out, next);
	}
	
	/**
	 * Create a display state using the given display output and next state.
	 * @param disp	The PrintStream to display output to
	 * @param next	The initial value of the next state.
	 */
	protected DisplayState(PrintStream disp, DisplayState next) {
		nextState = next;
		displayStream = disp;
	}
	
	/**
	 * Sets the next display state.
	 * @param next	The next display state.
	 */
	protected void setNextState(DisplayState next) {
		nextState = next;
	}
	
	/**
	 * Gets the next display state.
	 * @return	The next state.
	 */
	public DisplayState getNextState() {
		return nextState;
	}
	
	/**
	 * Closes the print stream.
	 */
	public void close() {
		displayStream.close();
	}
	
	/**
	 * Specific execution procedure of implementing states.
	 */
	public abstract void execute();
}
