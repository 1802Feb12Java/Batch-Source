package com.revature.classTypes;

import java.util.function.Consumer;

public interface InterfaceFun {

	// automatically public static final
	int NUMBER_FISH = 5;
	Consumer<Object> print = (Object o) -> System.out.println(o);

	public void typeOFish(String[] f);

}
