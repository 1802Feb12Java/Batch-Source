package com.revature.ui;

import com.revature.model.UserType;

public final class RegistrationState extends PromptState {
	private UserType type;
	
	public RegistrationState(UserType type) {
		super();
		this.type = type;
	}
	
	@Override
	public void execute() {
		// Prompt: FName
		// Prompt: LName
		// Prompt: Username
		// Prompt: Password 
		
		// Create a user accordingly & use DAO to log it into DB.
		switch(type) {
		case ADMIN:
			
			break;
		case EMPLOYEE:
			
			break;
		case CUSTOMER:
			
			break;
		default:
			// Unknown account type.
			return;
		}
		
		
		// Next state: EntryState
	}
}
