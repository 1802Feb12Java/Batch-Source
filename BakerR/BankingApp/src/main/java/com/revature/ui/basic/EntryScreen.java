package com.revature.ui.basic;

import java.util.Scanner;

public class EntryScreen extends DisplayState {

	@Override
	public void execute() {
		Scanner userIn = new Scanner(new UnclosableInputStream(System.in));
		String cmd;
		String[] splitCmd;
		
		System.out.println(
			"\n" + 
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
			" "
		);
		System.out.println("Welcome to the Bank of Tux");
		
		do {
			System.out.print(">>> ");
			cmd = userIn.nextLine();
			splitCmd = cmd.split("\\s+");
			
			if(splitCmd.length == 0) {
				continue;
			} else if(splitCmd[0].equals("register")) {
				setNextState(new RegistrationState());
				break;
			} else if(splitCmd[0].equals("login")) {
				setNextState(new LoginState());
				break;
			} else if(splitCmd[0].equals("exit")) {
				setNextState(null);
				break;
			} else if(splitCmd[0].equals("lscmd")) {
				lscmd(splitCmd);
			} else {
				System.out.println("Invalid command.");
			}
		} while(true);
		
		userIn.close();
	}
	
	public void lscmd(String[] args) {
		System.out.println("login, register, exit");
	}

}
