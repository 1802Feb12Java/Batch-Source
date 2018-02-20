package com.revature.ui.basic;

import java.util.Scanner;

public class EntryScreen extends DisplayState {

	@Override
	public void execute() {
		Scanner userIn = new Scanner(new UnclosableInputStream(System.in));
		String cmd;
		String[] splitCmd;
		
		do {
			System.out.print(">>> ");
			cmd = userIn.nextLine();
			splitCmd = cmd.split("\\s+");
			
			if(splitCmd.length == 0) {
				continue;
			} else if(splitCmd[0].equals("register")) {
				// TODO next state -> registration state
				break;
			} else if(splitCmd[0].equals("login")) {
				// TODO next state -> Login state
				break;
			} else if(splitCmd[0].equals("exit")) {
				setNextState(null);
				break;
			} else {
				System.out.println("Invalid command.");
			}
		} while(true);
		
		userIn.close();
	}

}
