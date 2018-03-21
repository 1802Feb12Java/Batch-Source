package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Theatre {
	public void watchMovie() throws Exception {
		System.out.println("Watching Dr. Strange...");
		//throw new Exception();
	}
}
