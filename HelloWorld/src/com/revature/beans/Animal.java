package com.revature.beans;

public class Animal extends Life
{
	public int age = 7;
	public static void main(String[] args) {
		method2();
		//method3();
	}

	public static void method2()
	{
		Animal a = new Animal();
		Life l = a;
		a.setAge(5);
		l.setAge(4);
		System.out.println("Life's age is: "+l.age);
		System.out.println("Animal's age is: "+a.age);
		System.out.println("Life's getAge(): "+l.getAge());
		System.out.println("Animal's getAge(): "+a.getAge());
		a.test();
		l.test();
		
	}
	public static void method1()
	{
		Life l = new Life();
		Animal a = new Animal();
		//l = a;
		l.setAge(5);
		a.setAge(4);
		System.out.println(l.getAge()+" "+a.getAge());
	}
	
	@Override
	public int getAge()
	{
		System.out.println(super.getAge());
		secret();
		return age;
	}

	@Override
	public void setAge(int a)
	{
		if(a<0)
			a=0;
		this.age=a;
	}

	private void secret()
	{
		System.out.println("This is an animal secret.");
	}
	
	@Override
	public void test()
	{
		System.out.println("Hello");
	}
}
