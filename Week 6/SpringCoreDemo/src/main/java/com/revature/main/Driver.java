package com.revature.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.*;

public class Driver {
<<<<<<< HEAD
	public static void main(String[] args){
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		/*Bear b1 = (BearWithSetter) ac.getBean("bearWithSetter");
		b1.methodInBear();
		Bear b2 = (BearWithConstructor) ac.getBean("bearWithConstructor");
=======
	public static void main(String[] args) throws InterruptedException{
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		//Bear b1 = (BearWithSetter) ac.getBean("bearWithSetter");
		//b1.methodInBear();
	/*	Bear b2 = (BearWithConstructor) ac.getBean("bearWithConstructor");
>>>>>>> 992ab3519e437ae6bab742af2bfb1006d3ed9b43
		b2.methodInBear();
		//this will be a different bear, same cave. Wiring method doesn't matter. 
		Bear b3 = (BearWithConstructor) ac.getBean("bearWithConstructor");
		b3.methodInBear();
		Bear b4 = (BearWithAutowiringByType) ac.getBean("bearWithAutowiringByType");
		b4.methodInBear();
		Bear b5 = (BearWithAutowiringByName) ac.getBean("bearWithAutowiringByName");
		b5.methodInBear();*/
		Bear b6 = (BearWithAutomagic) ac.getBean("bearWithAutomagic"); //singleton scoped
<<<<<<< HEAD
		/*b6.methodInBear();
		Cave c1 = b6.getCave();
		System.out.println(c1.toString());*/
		
		((AbstractApplicationContext) ac).close();
		
		//b6.methodInBear();
=======
		b6.methodInBear();
		Cave c1 = b6.getCave();
		System.out.println(c1.toString());
		
		((AbstractApplicationContext) ac).close();
		System.gc();
			Thread.sleep(100000);
		
		b6.methodInBear();
>>>>>>> 992ab3519e437ae6bab742af2bfb1006d3ed9b43
		
		
	}
}
