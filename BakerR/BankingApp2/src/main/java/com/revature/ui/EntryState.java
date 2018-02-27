package com.revature.ui;

import java.util.Collections;
import java.util.List;

import com.revature.model.UserType;
import com.revature.ui.command.Command;
import com.revature.ui.exception.IllegalCommandParameterException;

public final class EntryState extends CommandState {
	
	public EntryState() {
		super();
		
		Command	regCommand = new Command("register", "register <user type>", 
				"This command brings up the registration screen.\n" +
				"Valid values for <user types> are: 'admin', 'employee', and 'customer'.") {
			private UserType userType;
			
			{
				userType = null;
			}
			
			@Override
			public void run() {
				// Check for valid state.
				if(userType == null) {
					throw new IllegalStateException("A <user type> parameter must be specified.");
				}
				
				// Next state: RegistrationState
				setNextState(new RegistrationState(userType));
				
				// Reset to default state.
				reset();
			}

			@Override
			public List<String> getParams() {
				if(userType == null) {
					return Collections.emptyList();
				} else { 
					return Collections.singletonList(UserType.toString(userType));
				}
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				UserType t = null;
				
				if(args == null || args.length < 1) {
					throw new IllegalArgumentException("args must not be null and have a length of at least 1.");
				} else if((t = UserType.fromString(args[0])) == null) {
					throw new IllegalCommandParameterException(this, "args[0] must be a valid user type.");
				}
				
				userType = t;
			}

			@Override
			public void setParams(String[] args, int begin, int end) throws IllegalCommandParameterException {
				if(args == null) {
					throw new IllegalArgumentException("args cannot be null");
				}
				
				if(begin < 0 || begin >= args.length || end <= 0 || end > args.length) {
					throw new IllegalArgumentException("begin and/or end are out of bounds.");
				}
				if(begin >= end) {
					throw new IllegalArgumentException("begin cannot be larger than end.");
				}
				
				UserType t;
				
				if((t = UserType.fromString(args[begin])) == null) {
					throw new IllegalCommandParameterException(this, "args[" + begin + "] must be a valid user type.");
				}
				
				userType = t;
			}

			@Override
			public void reset() {
				userType = null;
			}
			
		};
	
		registerCommand(regCommand);
		
		
		Command loginCommand = new Command("login", "login", "Go to login screen.") {

			@Override
			public void run() {
				setNextState(new LoginState());
			}

			@Override
			public List<String> getParams() {
				return Collections.emptyList();
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				// Ignore parameters.
			}

			@Override
			public void setParams(String[] args, int begin, int end) throws IllegalCommandParameterException {
				// Ignore parameters.
			}

			@Override
			public void reset() {
				// Nothing to reset.
			}
			
		};
		
		registerCommand(loginCommand);
	}
}
