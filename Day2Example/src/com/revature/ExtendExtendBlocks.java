package com.revature;

public class ExtendExtendBlocks extends ExtendCodeBlocks {

	{
		System.out.println("extend2: instance CB");
	}
	static {
		System.out.println("extend2: static CB");
	}

	public static void main(String[] args) {
		System.out.println("extend2: main");
		new ExtendCodeBlocks();
	}

	public ExtendExtendBlocks() {
	}
}
