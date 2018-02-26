package com.revature.ui.command;

import java.util.List;

import com.revature.ui.exception.IllegalCommandParameterException;

public abstract class Command implements Runnable {
	private String name; // command name
	private String template; // quick help: layout of command and arguments
	private String manual; // Detailed string containing details of the command.
	
	/**
	 * Creates a command with the given name and help strings.
	 * @param name	Name of the command. Must be non-empty and non-null.
	 * @param template	a quick line showing how the command should be called.
	 * @param manual	Details of the command.
	 * @throws IllegalArgumentException thrown if name is empty or null.
	 */
	protected Command(String name, String template, String manual) {
		if(name == null || name.isEmpty()) {
			throw new IllegalArgumentException("Command name cannot be empty or null.");
		}
		
		this.name = name;
		this.template = template == null ? "" : template;
		this.manual = manual == null ? "" : manual;
	}
	
	/**
	 * Gets the name of the command.
	 * @return	The name of the command.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Gets the quick help showing the template of how the command is called.
	 * @return	The quick help command template.
	 */
	public String getTemplate() {
		return template;
	}
	
	/**
	 * Gets the help string for the command.
	 * @return	Gets the detailed help manual string.
	 */
	public String getManual() {
		return manual;
	}
	
	public abstract List<String> getParams();
	
	/**
	 * Sets the parameters for the command with the given values in args.
	 * @param args	The arguments to use when running the command.
	 * @throws IllegalCommandParameterException	thrown if the given arguments are invalid.
	 */
	public abstract void setParams(String[] args) throws IllegalCommandParameterException;
	
	/**
	 * Sets the parameters for the command with the specified range of values [begin, end) in args.
	 * @param args	
	 * @param begin	
	 * @param end	The end, exclusive, of the range in args to use as arguments.
	 * @throws IllegalCommandParamterException	thrown if the given arguments are invalid.
	 */
	public abstract void setParams(String[] args, int begin, int end) throws IllegalCommandParameterException;
	
	/**
	 * Resets the state of the command to its default state.
	 */
	public abstract void reset();
}
