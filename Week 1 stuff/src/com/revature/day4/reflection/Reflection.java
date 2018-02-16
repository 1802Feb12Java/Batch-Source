package com.revature.day4.reflection;

import java.lang.reflect.Method;

public class Reflection {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			SecretDocuments s = new SecretDocuments(1,"I am a secret", "Me");
			Class<SecretDocuments> secretClass = SecretDocuments.class;
			System.out.println(secretClass.getName());
			
			
			Method[] methods = secretClass.getMethods();
			for(Method m : methods) {
				System.out.println("\t\t"+m);
			}
			
			
			
	}

}
