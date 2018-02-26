package com.revature.ui;

public final class StateController implements Runnable {
	private static StateController instance;
	
	private DisplayState startState;
	private DisplayState exitState;
	
	/**
	 * Creates the StateManager with the initial
	 * start-checkup state and exit state.
	 */
	private StateController() {
		startState = new DisplayState() {

			@Override
			public void execute() { // TODO start state.
				final String TUX = "\n" + 
						"             .888888:.\n" + 
						"             88888.888.\n" + 
						"            .8888888888\n" + 
						"            8' `88' `888\n" + 
						"            8 8 88 8 888\n" + 
						"            8:.,::,.:888\n" + 
						"           .8`::::::'888\n" + 
						"           88  `::'  888\n" + 
						"          .88        `888.\n" + 
						"        .88'   .::.  .:8888.\n" + 
						"        888.'   :'    `'88:88.\n" + 
						"      .8888'    '        88:88.\n" + 
						"     .8888'     .        88:888\n" + 
						"     `88888     :        8:888'\n" + 
						"      `.:.88    .       .::888'\n" + 
						"     .:::::88   `      .:::::::.\n" + 
						"    .::::::.8         .:::::::::\n" + 
						"    :::::::::..     .:::::::::'\n" + 
						"     `:::::::::88888:::::::'\n" + 
						"        rs`:::'       `:'\n" + 
						" ";
				System.out.println(TUX);
				System.out.println("Welcome to the Bank of Tux!");
				
				// Get number of admins
				
				// If admins < 0: goto SetupState
				// Else: goto EntryState
				
				
				
			}
			
		};
		
		exitState = new DisplayState() {
			@Override
			public void execute() {
				System.out.println("Thank you for visiting Bank of Tux!");
				System.exit(0);
			}
			
		};
		
	}
	
	/**
	 * Gets an instance of the State Controller
	 * @return	An instance of the state controller.
	 */
	public static StateController getInstance() {
		if(instance == null)
			instance = new StateController();
		return instance;
	}
	
	/**
	 * Gets the exit state.
	 * @return	The exit state.
	 */
	public DisplayState getExitState() {
		return exitState;
	}
	
	/**
	 * Executes the state controller.
	 */
	@Override
	public void run() {
		DisplayState currentState = startState;
		do {
			currentState.execute();
			currentState = currentState.getNextState();
		} while(currentState != null);
		
		exitState.execute();
	}
	
	
	/////////////////////////////////////////////////////////////////
	// Main
	/////////////////////////////////////////////////////////////////
	public static void main(String[] args) {
		StateController stateMachine = StateController.getInstance();
		stateMachine.run();
	}
}
