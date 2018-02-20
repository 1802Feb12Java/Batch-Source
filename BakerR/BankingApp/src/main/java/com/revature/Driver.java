package com.revature;

import com.revature.ui.basic.StateManager;

public final class Driver {

	public static void main(String[] args) {
		StateManager stateMachine = StateManager.getInstance();
		stateMachine.run();
	}

}
