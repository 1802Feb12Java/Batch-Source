package com.revature.ui.basic;

import com.revature.managers.UserManager;

public final class StateManager implements Runnable {
	private static StateManager instance;
	
	private DisplayState startState;
	private DisplayState exitState;
	
	private StateManager() {
		startState = new DisplayState() {
			private DisplayState nextState;
			
			@Override
			public void execute() {
				UserManager usrMng = UserManager.getInstance();
				
				if(usrMng.getUsers().isEmpty()) {
					// TODO nextState -> AdminAcctSetup 
				} else {
					// TODO nextState -> EntryScreen
				}
			}

			@Override
			public DisplayState getNextState() {
				return nextState;
			}
		};
		
		exitState = new DisplayState() {

			@Override
			public void execute() {
				System.exit(0);
			}

			@Override
			public DisplayState getNextState() {
				return null;
			}

		};
		
	}
	
	
	public static StateManager getInstance() {
		if(instance == null)
			instance = new StateManager();
		return instance;
	}
	
	
	public DisplayState getExitState() {
		return exitState;
	}
	
	@Override
	public void run() {
		DisplayState currentState = startState;
		
		do {
			currentState.execute();
			currentState = currentState.getNextState();
		} while(currentState != null);
		
		exitState.execute();
	}
	
	
	
}
