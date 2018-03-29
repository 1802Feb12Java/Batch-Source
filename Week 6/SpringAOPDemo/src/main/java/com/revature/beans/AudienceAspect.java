package com.revature.beans;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AudienceAspect {
	//@Before("execution(* com.revature.beans.Theatre.watchMovie(..))")
	@Before("bean(theatre)")
	public void buyTicket() {
		System.out.println("Buying ticket...");
	}
	
	@Before("execution(* com.revature.beans.Theatre.watchMovie(..))")
	public void findSeat() {
		System.out.println("Finding seat...");
	}
	
	@AfterReturning("bean(theatre)")
	public void clap() {
		System.out.println("Clapping...");
	}
	
	@AfterThrowing("bean(theatre)")
	public void boo() {
		System.out.println("Booing...");
	}
	
	@Around("bean(theatre)")
	public void useRestroom(ProceedingJoinPoint jp) throws Throwable{
		System.out.println("Using restroom before movie...");
		
	
			jp.proceed();
		
		
		System.out.println("Using restroom after movie...");
	}
}
