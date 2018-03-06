package com.revature.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.revature.garbage.SecretDocuments;

public class Reflection {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		
		Class<SecretDocuments> secretClass = SecretDocuments.class;
		System.out.println(secretClass.getName());
		System.out.println("\tMethods");
		Method[] methods = secretClass.getMethods();
		for(Method m : methods)
		{
			System.out.println("\t\t"+m);
		}
		System.out.println("\tConstructors");
		Constructor<SecretDocuments>[] constructors = (Constructor<SecretDocuments>[]) secretClass.getConstructors();
		for(Constructor c : constructors)
		{
			System.out.println("\t\t"+c);
		}
		System.out.println("\tFields");
		Field[] fields = secretClass.getDeclaredFields();
		for(Field f : fields)
		{
			System.out.println("\t\t"+f);
		}
		SecretDocuments s = new SecretDocuments();
		
		s.setOwner("Richard");
		
		System.out.println(s);
		Field owner = s.getClass().getDeclaredField("owner");
		owner.setAccessible(true);
		owner.set(s, "All your base");
		System.out.println(s);
		
		String foo = "foo";
		System.out.println("______________________________________________");
		System.out.println("______________________________________________");
		System.out.println("______________________________________________");
		System.out.println("______________________________________________");
		System.out.println("Initial value: " + foo);
		
		Field val  = foo.getClass().getDeclaredField("value");
		val.setAccessible(true);
		val.set(foo, "newValue".toCharArray());
		System.out.println("Changed value: " + foo);
		String newFoo = "foo";
		System.out.println(newFoo);
		
		System.out.println(new String("foo")+" is this really happening");
		
	}
}
