package com.revature.main;

import com.revature.beans.Manager;

public class Driver {

	public static void main(String[] args) {
		
		Manager man = new Manager(1, "uname", "pass", "Josh", "Boudreau", "jb@email.com", 1);
		System.out.println(man.toString());
		
	}

}
