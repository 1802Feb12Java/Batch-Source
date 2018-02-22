package com.revature.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.function.Consumer;

/**
 * Class to show off Reflection in Java
 * 
 * @param <T>
 */
public class Reflection<T> {

	public Consumer<T> printClass = (T t) -> System.out.println(t.getClass());

	public Consumer<T> printMethods = (T t) -> {
		for (Method m : t.getClass().getMethods())
			System.out.println(m.getName());
	};

	public Consumer<T> printConstructors = (T t) -> {
		System.out.println("Constructors:");
		for (Constructor<?> c : t.getClass().getConstructors())
			System.out.println("\t" + c);
	};
	public Consumer<T> printFields = (T t) -> {
		System.out.println("Fields:");
		for (Field f : t.getClass().getDeclaredFields())
			System.out.println("\t" + f);
	};

	public static void main(String[] args) {

		SecretDocument sd = new SecretDocument(1, "I am secret", "Root");

		// get the class's name
		// SecretDocuments is Generic type for Class
		Class<?> secretClass = SecretDocument.class;
		System.out.println("Class:");
		System.out.println("\t" + secretClass.getName());

		// use reflection to see all of the classes's methods
		System.out.println("Methods:");
		Method[] methods = secretClass.getMethods();
		for (Method m : methods)
			System.out.println("\t" + m.getName());

		//
		Reflection<SecretDocument> r = new Reflection<SecretDocument>();
		r.printConstructors.accept(sd);
		r.printFields.accept(sd);

		// manipulate obj
		sd.setOwner("Jen");
		System.out.println(sd);

		// using field class API in refleciton
		Field owner;
		try {
			owner = sd.getClass().getDeclaredField("owner");
			// change access modifier
			owner.setAccessible(true);
			// change private value outside of class
			owner.set(sd, "Me");
			System.out.println(sd);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
