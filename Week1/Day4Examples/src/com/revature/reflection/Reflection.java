package com.revature.reflection;

import java.lang.reflect.*;

public class Reflection {

	public static void main(String[] args)
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		SecretDocuments s = new SecretDocuments(1, "I am a secret", "Me");
		System.out.println(s); // no need for s.toString()

		System.out.println("\n\tClass:");
		Class<SecretDocuments> secretClass = SecretDocuments.class;
		System.out.println("\t\t" + secretClass.getName());

		System.out.println("\n\tMethods:");
		Method[] methods = secretClass.getMethods();
		for (Method m : methods) {
			System.out.println("\t\t" + m);
		}

		System.out.println("\n\tConstructors:");
		Constructor<SecretDocuments>[] constructors = (Constructor<SecretDocuments>[]) secretClass.getConstructors();
		for (Constructor c : constructors) {
			System.out.println("\t\t" + c);
		}

		System.out.println("\n\tFields:");
		Field[] fields = secretClass.getDeclaredFields();
		for (Field f : fields) {
			System.out.println("\t\t" + f);
		}

		// setting through method
		s.setOwner("Matt");
		System.out.println("\n" + s);

		// setting through reflection
		Field owner = s.getClass().getDeclaredField("owner");
		owner.setAccessible(true);
		owner.set(s, "All ur base");
		System.out.println(s);
	}

}