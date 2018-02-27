package com.revature.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import com.revature.model.User;
import com.revature.ui.command.Command;
import com.revature.ui.exception.IllegalCommandParameterException;
import com.revature.util.UnclosableInputStream;

public class SessionState extends CommandState {
	private User user;
	
	/**
	 * Create a new SessionState for the given user, 
	 * using in for user input and disp to output messages to the user,
	 * and sets the initial value of the next state.  
	 * @param currentUser	The user the session belongs to.
	 * @param in	InputStream to get user input from
	 * @param disp	PrintStream to display messages to user.
	 * @param nextState	The initial value of the next state.
	 * @throws IllegalArgumentException thrown when currentUser is null.
	 */
	protected SessionState(User currentUser, 
			InputStream in, PrintStream disp, DisplayState nextState) {
		super(in, disp, nextState);
		
		if(currentUser == null) {
			throw new IllegalArgumentException("User must not be null.");
		}
		
		user = currentUser;
		
		// logout command
		Command logoutCommand = new Command("logout", "logout", "Log out of current session.") {

			@Override
			public void run() {
				// Set next state to entry state
				setNextState(new EntryState());
			}

			@Override
			public List<String> getParams() {
				return Collections.emptyList();
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				// Do nothing.
			}

			@Override
			public void setParams(String[] args, int begin, int end) throws IllegalCommandParameterException {
				// Do nothing.
			}

			@Override
			public void reset() {
				// Nothing to do.
			}
			
		};
		
		registerCommand(logoutCommand);
	}
	
	/**
	 * Same as full constructor with next state defaulted to null.
	 * @param currentUser	The user the session belongs to.
	 * @param in	InputStream to get user input from
	 * @param disp	PrintStream to display messages to user.
	 */
	protected SessionState(User currentUser, InputStream in, PrintStream disp) {
		this(currentUser, in, disp, null);
	}
	
	/**
	 * Same as full constructor with next state set to its default,
	 * user input defaulted to System.in, and display stream defaulted
	 * to System.out.
	 * @param currentUser	The user the session belongs to.
	 */
	protected SessionState(User currentUser) {
		this(currentUser, new UnclosableInputStream(System.in), System.out);
	}
	
	/**
	 * Gets the user that owns the session.
	 * @return	The user that owns the session.
	 */
	public User getUser() {
		return user;
	}
	
}
