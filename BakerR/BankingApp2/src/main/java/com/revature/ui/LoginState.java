package com.revature.ui;

public class LoginState extends PromptState {

	@Override
	public void execute() {
		
		int tryCount = 5;
		for(;tryCount-->0;) {
			// Prompt: username
			// Prompt: Password
			
			// Verify password: successful -> break loop.
		}
		
		// Failed password verification -> back to EntryState
		if(tryCount <= 0) {
			setNextState(new EntryState());
		} else {
			// Password verification successful.
			// Get account type and set next state according to user type.
			// Default case -> output error message and set next state to EntryState.
		}
	}

}
