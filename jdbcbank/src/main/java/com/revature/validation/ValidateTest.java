package com.revature.validation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class ValidateTest {

	@Test
	public void testSafeParse() {
		// test empty
		assertEquals(Validate.safeParse(""), -1);
		// test spaces
		assertEquals(Validate.safeParse(" 3 "), 3);
		assertEquals(Validate.safeParse(" 3 3"), -1);
		// test non numbers
		assertEquals(Validate.safeParse("a"), -1);
		// test mix
		assertEquals(Validate.safeParse(" 3a"), -1);
		// test long
		assertEquals(Validate.safeParse("333333333"), 333333333);
	}

	@Test
	public void testValidUsername() {
		// test empty
	}

	@Test
	public void testValidPassword() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidSuperCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidBoolean() {
		fail("Not yet implemented");
	}

	@Test
	public void testValidAmount() {
		fail("Not yet implemented");
	}

}
