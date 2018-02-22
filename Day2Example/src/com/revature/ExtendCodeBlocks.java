package com.revature;

public class ExtendCodeBlocks extends CodeBlocks {

	{
		System.out.println("extend: instance CB");
	}
	static {
		System.out.println("extend: static CB");
	}

	public ExtendCodeBlocks() {
	}

	@Override
	public int dummy() {
		return 0;

	}

}
