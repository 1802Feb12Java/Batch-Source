package com.revature.ui;

import com.revature.model.UserType;

public final class SetupState extends PromptState {

	public SetupState() {
		super();
	}
	
	@Override
	public void execute() {
		// Ignore 'resource' leak, state is defaulted to system in/out.
		@SuppressWarnings("resource")
		RegistrationState adminRegistrationState = new RegistrationState(UserType.ADMIN);
		
		// Perform an admin registration.
		adminRegistrationState.execute();
		
		// Inherit the next state.
		setNextState(adminRegistrationState.getNextState());
	}

}
