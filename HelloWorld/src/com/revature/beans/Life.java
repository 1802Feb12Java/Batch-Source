package com.revature.beans;

public class Life
{
	public int age = 0;
	
	public Life()
	{
		//super() is implicit
		secret();
	}
	
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age=age;
	}
	private void secret()
	{
		System.out.println("This is a secret.");
	}
	void test()
	{
		System.out.println("Test");
	}
}
