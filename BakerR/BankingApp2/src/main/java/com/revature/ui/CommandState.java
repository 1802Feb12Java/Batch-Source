package com.revature.ui;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.revature.ui.command.Command;
import com.revature.ui.exception.IllegalCommandParameterException;
import com.revature.util.UnclosableInputStream;

public class CommandState extends DisplayState {
	private Map<String, Command> commandMap;
	protected final InputStream userInStream;
	
	/**
	 * Creates a CommandState with the given input/output streams and
	 * the initial next state.
	 * @param in	Input stream for user input.
	 * @param disp	PrintStream to print messages to.
	 * @param nextState	Initial value of the next state.
	 */
	protected CommandState(InputStream in, PrintStream disp, DisplayState nextState) {
		super(disp, nextState);
		userInStream = in;
		commandMap = new HashMap<>();
		
		// exit command.
		Command exitCommand = new Command("exit", "exit", "Exits the program.") {
			@Override
			public void run() {
				setNextState(null);
			}

			@Override
			public List<String> getParams() {
				return Collections.emptyList();
			}

			
			@Override
			public void setParams(String[] args) {
				// Do nothing with arguments.
			}
			

			@Override
			public void setParams(String[] args, int begin, int end) {
				// Do nothing with arguments.
			}

			
			public void reset() {
				// No state to reset.
			}
		};
		
		commandMap.put(exitCommand.getName(), exitCommand);
		
		// lscmd command
		Command lscmdCommand = new Command("lscmd", "lscmd", "Lists all available commands.") {

			@Override
			public void run() {
				// Get a list of all commands
				List<String> cmdList = new ArrayList<>(commandMap.keySet());
				
				// Sort commands.
				cmdList.sort((String s1, String s2) -> s1.compareTo(s2));
				
				// Print the list of commands.
				cmdList.forEach((String cmd) -> {
					displayStream.println(cmd);
				});
				
			}

			@Override
			public List<String> getParams() {
				// No parameters to keep.
				return Collections.emptyList();
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				 // Nothing to do with arguments.
				
			}

			@Override
			public void setParams(String[] args, int begin, int end) throws IllegalCommandParameterException {
				// Nothing to do with arguments.
			}

			@Override
			public void reset() {
				// Nothing to reset.
			}
			
		};
		
		commandMap.put(lscmdCommand.getName(), lscmdCommand);
		
		// help command
		Command manCommand = new Command("man", "man [command]", 
				"This command prints out the detailed information about [command].") {
					private String targetCmd;
					
					{
						targetCmd = null;
					}
					
					@Override
					public void run() {
						// Set it to print its own help info.
						if(targetCmd == null) targetCmd = getName();
						
						// Get the target command.
						Command cmd = commandMap.get(targetCmd);
						
						// Display manual info.
						if(cmd == null) {
							// Command not found.
							displayStream.println("Command <" + targetCmd + "> not found.");
						} else {
							// Display detailed command info.
							displayStream.println("Command: " + cmd.getName());
							displayStream.println("Use template: " + cmd.getTemplate());
							displayStream.println("Details:\n" + cmd.getManual());
						}
						
						// Reset into default state.
						reset();
					}
					
					
					@Override
					public List<String> getParams() {
						Collections.singletonList(targetCmd);
						return null;
					}
					
					
					@Override
					public void setParams(String[] args) {
						if(args == null) {
							throw new IllegalArgumentException("args cannot be null.");
						}
						
						if(args.length == 0) {
							targetCmd = null;
						} else {
							targetCmd = args[0];
						}
					}

					@Override
					public void setParams(String[] args, int begin, int end) {
						if(args == null) {
							throw new IllegalArgumentException("args cannot be null");
						}
						
						if(begin < 0 || begin >= args.length || end <= 0 || end > args.length) {
							throw new IllegalArgumentException("begin and/or end are out of bounds.");
						}
						if(begin >= end) {
							throw new IllegalArgumentException("begin cannot be larger than end.");
						}
						
						targetCmd = args[begin].isEmpty() ? getName() : args[begin];
					}

					@Override
					public void reset() {
						targetCmd = null;
					}
			
		};
		
		commandMap.put(manCommand.getName(), manCommand);
	}
	
	/**
	 * Creates a CommandState with the given input and initial next state value.
	 * Display output is set to System.out as the default.
	 * @param in	InputStream to get command input from.
	 * @param nextState	Initial nextState value.
	 */
	protected CommandState(InputStream in, DisplayState nextState) {
		this(in, System.out, nextState);
	}
	
	/**
	 * Creates a command state with its input defaulted to System.in
	 * and its initial value of the next state set to null. Display stream
	 * is also set to its default.
	 */
	protected CommandState() {
		this(new UnclosableInputStream(System.in), null);
	}
	
	/**
	 * Waits for a command to be input and returns a run-ready command if valid.
	 * @return	Returns the command with its state set and ready to be run.
	 * 			If the provided command could not be found or had invalid parameters,
	 * 				null is returned.
	 * @throws IllegalCommandParamterException thrown if input parameters are invalid for the command
	 */
	public Command waitForCommand() throws IllegalCommandParameterException {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(userInStream);
		
		String cmdStr = scan.nextLine();
		String[] cmdSplit = cmdStr.split("\\s+");
		Command cmd = commandMap.get(cmdSplit[0]);
		
		if(cmd != null) {
			cmd.setParams(cmdSplit, 1, cmdSplit.length);
		}
		
		return cmd;
	}
	
	/**
	 * Gets the command with the given name.
	 * @param name	The command name.
	 * @return	The registered command associated with the name.
	 */
	public Command getCommand(String name) {
		return commandMap.get(name);
	}
	
	/**
	 * Registers a command to this state. Does nothing if cmd is null.
	 * @param cmd	The command to register.
	 * @return	null if cmd is null; if a command with same name was present, 
	 * 			the previous command is returned; if no command is previously
	 * 			registered, cmd is returned.
	 */
	public Command registerCommand(Command cmd) {
		if(cmd == null) {
			return null;
		}
		
		Command prevCmd = commandMap.put(cmd.getName(), cmd);
		
		if(prevCmd == null) {
			prevCmd = cmd;
		}
		
		return prevCmd;
	}

	/**
	 * Closes streams.
	 */
	public void close() {
		super.close();
		try {
			userInStream.close();
		} catch(IOException ex) {}
	}

	@Override
	public void execute() {
		setNextState(this); // Return to this state if command does not set a next state.
		Command cmd = null;
		do {
			System.out.print(">>> ");
			
			try {
				cmd = waitForCommand();
				
				if(cmd == null) {
					System.out.println("Invalid input.");
				} else {
					cmd.run();
					cmd.reset();
				}
			} catch (IllegalCommandParameterException e) {
				System.out.println("Invalid arguments for: " + e.getCommand().getName());
				System.out.println("Expected template: " + e.getCommand().getTemplate());
				cmd = null;
			}
		} while(cmd == null);
	}

	
}
