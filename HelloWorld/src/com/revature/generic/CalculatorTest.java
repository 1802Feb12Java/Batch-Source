package com.revature.generic;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class CalculatorTest {
	
	private static Calculator<Double> dc;
	
	@Before
	public void setUp(){
		
		System.out.println("Before");
		
	}
	
	@AfterClass
	public static void tearDownAfterClass(){
		
		System.out.println("AfterClass");
		
	}
	
	@BeforeClass
	public static void setUpBeforeClass(){
		
		dc = new Calculator<Double>();
		
	}
	
	@After
	public void tearDown(){
		
		System.out.println("After");
		
	}

	@Test
	public void testAdd() {

		assertEquals("2+2 = 4", new Double(4.0), dc.add(2.0, 2.0));
	}
	
	@Test
	public void testSubtract(){
		
		assertEquals("2-2 = 0", new Double(0.0), dc.subtract(2.0, 2.0));
		
	}

}
