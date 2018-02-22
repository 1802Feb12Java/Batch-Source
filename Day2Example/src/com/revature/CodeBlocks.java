package com.revature;

public class CodeBlocks {

	/**
	 * Instance Code Block, execute before the constructor when obj is instantiated,
	 * can't reference the obj using "this" bc object isn't instantiated yet
	 */
	{
		System.out.println("instance CB");
	}

	/*
	 * Static Code Block are similar. These execute once, when the class is loaded
	 * in JVM. Give a window while creating these objects. Can use the code blocks
	 * to inject behavior when we instantiate objects
	 */
	static {
		System.out.println("static CB");
	}

	public CodeBlocks() {
		System.out.println("construct");
	}

	public int dummy() {
		return 0;
	}

	protected String dummyVar;

}// Class End
