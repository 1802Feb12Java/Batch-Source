package com.revature.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflection {

	public static void main(String[] args) 
			throws NoSuchFieldException, SecurityException, 
			IllegalArgumentException, IllegalAccessException{
		// TODO Auto-generated method stub
	SecretDocuments	s=new SecretDocuments(1,"I am a secret", "Me");
	Class<SecretDocuments> secretClass = SecretDocuments.class;
	System.out.println(secretClass.getName());
	
	System.out.println("\tMethods");
	Method[] methods = secretClass.getMethods();
	/*for(Method m : methods)
	{
		System.out.println("\t\t"+m);
	}*/
	System.out.println(Arrays.toString(methods));
	
	System.out.println("\tConstructors");
	Constructor<SecretDocuments>[] constructors = 
			(Constructor<SecretDocuments>[])
			secretClass.getConstructors();
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
	
	s.setOwner("Matt");
	System.out.println(s);
	Field owner = s.getClass().getDeclaredField("owner");
	owner.setAccessible(true);
	owner.set(s,"All ur basssse" );
	System.out.println(s);
	}

}
