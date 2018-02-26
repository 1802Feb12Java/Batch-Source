package com.revature.ui.exception;

import com.revature.ui.command.Command;

public class IllegalCommandParameterException extends Exception {
	private static final long serialVersionUID = 4722708750965739065L;
	private Command command;
	
	/**
	 * Creates an exception with a reference to the command throwing it.
	 * @param cmd	The command throwing the exception.
	 * @param message The exception message.
	 */
	public IllegalCommandParameterException(Command cmd, String message) {
		super(message);
		this.command = cmd;
	}
	
	public IllegalCommandParameterException(Command cmd) {
		super();
		this.command = cmd;
	}
	
	
	public Command getCommand() {
		return command;
	}
}
