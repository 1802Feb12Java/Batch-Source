package com.revature.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflecton {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
	
		SecretDocuments s = new SecretDocuments(1, "I am a secret", "Me");
		
		Class<SecretDocuments> secretClass = SecretDocuments.class;
		System.out.println("Fully qualified class name: " + secretClass.getName());
		
		System.out.println("\tMethods");
		Method[] methods = secretClass.getMethods();
		for(Method m : methods) {
			System.out.println("\t\t" + m);
		}
		
		System.out.println("\tConstructors");
		@SuppressWarnings("unchecked")
		Constructor<SecretDocuments>[] constructors = (Constructor<SecretDocuments>[])
				secretClass.getConstructors();
		for(Constructor<SecretDocuments> c : constructors) {
			System.out.println("\t\t" + c);
		}

		System.out.println("\tFields");
		Field[] fields = secretClass.getDeclaredFields();
		for(Field f : fields) {
			System.out.println("\t\t" + f);
		}
		
		s.setOwner("Matt");
		//System.out.println(s.toString());
		System.out.println(s);
		Field owner = s.getClass().getDeclaredField("owner");
		owner.setAccessible(true);
		owner.set(s, "All ur basssssse");
		System.out.println(s);
		
		
		
	}//end main

	
	
	
}//end class
