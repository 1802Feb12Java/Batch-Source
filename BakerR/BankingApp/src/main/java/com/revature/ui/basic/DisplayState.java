package com.revature.ui.basic;


public abstract class DisplayState {
	private DisplayState nextState;
	
	public abstract void execute();
	
	public DisplayState getNextState() {
		return nextState;
	}
	
	protected void setNextState(DisplayState next) {
		nextState = next;
	}
}
