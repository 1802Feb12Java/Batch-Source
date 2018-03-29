package com.revature.beans;

import org.springframework.stereotype.Component;

@Component
public class Theatre {
	public void watchMovie() throws Exception {
		System.out.println("Watching 50 shades the 1st one JD...");
		throw new Exception();
	}
}
