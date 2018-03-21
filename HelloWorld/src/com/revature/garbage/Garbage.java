package com.revature.garbage;

public class Garbage {
	public static void main(String[] args) {
		SecretDocuments s = null;
		System.out.println(SecretDocuments.deaths);
		for(long i = 0; i<100000000; i++)
		{
			s=new SecretDocuments(1,"I am a secret", "Me");
			
		}
		System.out.println(SecretDocuments.deaths);
		//System.gc();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(SecretDocuments.deaths);
		System.out.println(s);
	}
}
