package com.revature.main;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.*;

public class Driver {
	public static void main(String[] args){
		
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		/*Bear b1 = (BearWithSetter) ac.getBean("bearWithSetter");
		b1.methodInBear();
		Bear b2 = (BearWithConstructor) ac.getBean("bearWithConstructor");
		b2.methodInBear();
		//this will be a different bear, same cave. Wiring method doesn't matter. 
		Bear b3 = (BearWithConstructor) ac.getBean("bearWithConstructor");
		b3.methodInBear();
		Bear b4 = (BearWithAutowiringByType) ac.getBean("bearWithAutowiringByType");
		b4.methodInBear();
		Bear b5 = (BearWithAutowiringByName) ac.getBean("bearWithAutowiringByName");
		b5.methodInBear();*/
		Bear b6 = (BearWithAutomagic) ac.getBean("bearWithAutomagic"); //singleton scoped
		/*b6.methodInBear();
		Cave c1 = b6.getCave();
		System.out.println(c1.toString());*/
		
		((AbstractApplicationContext) ac).close();
		
		//b6.methodInBear();
		
		
	}
}
