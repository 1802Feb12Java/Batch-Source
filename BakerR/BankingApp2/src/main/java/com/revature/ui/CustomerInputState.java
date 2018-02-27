package com.revature.ui;

import java.util.List;

import com.revature.model.Customer;
import com.revature.ui.command.Command;
import com.revature.ui.exception.IllegalCommandParameterException;

public class CustomerInputState extends SessionState {
	
	public CustomerInputState(Customer usr) {
		super(usr);
		
		// Register commands
		// mkacct
		Command mkacctCommand = new Command("mkacct", "mkacct", "Create an account.") {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}

			@Override
			public List<String> getParams() {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void setParams(String[] args) throws IllegalCommandParameterException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void setParams(String[] args, int begin, int end) throws IllegalCommandParameterException {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
		};
		// rmacct
		
		// deposit
		
		// withdraw
	
		// transfer
		
	}
}
