package com.revature.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.revature.validation.Validate;

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
		assertFalse(Validate.validUsername("asdf"));
		assertFalse(Validate.validUsername("asdff"));
		assertFalse(Validate.validUsername("asdf "));
		assertFalse(Validate.validUsername(" asdf"));

		assertTrue(Validate.validUsername("asdfff"));
		assertTrue(Validate.validUsername("asdf11"));
		assertTrue(Validate.validUsername("asdfas dfsdf"));
	}

	@Test
	public void testValidPassword() {
		assertFalse(Validate.validPassword("asdf"));
		assertFalse(Validate.validPassword("asdff"));
		assertFalse(Validate.validPassword("asdf "));
		assertFalse(Validate.validPassword(" asdf"));

		assertTrue(Validate.validPassword("asdfff1"));
		assertTrue(Validate.validPassword("asdf11"));
		assertTrue(Validate.validPassword("a1sdfasdfsdf"));
	}

	@Test
	public void testValidSuperCode() {
		assertTrue(Validate.validSuperCode("iamsuper"));
		assertTrue(Validate.validSuperCode(" i am super"));
		assertTrue(Validate.validSuperCode(" IAMSUPER"));
		assertTrue(Validate.validSuperCode(" i am SUPER   "));

		assertFalse(Validate.validSuperCode(" i am SUPER2   "));
		assertFalse(Validate.validSuperCode(" i am SUPERs"));
		assertFalse(Validate.validSuperCode(" i am SUPE"));
		assertFalse(Validate.validSuperCode("am SUPER   "));
	}

	@Test
	public void testValidDate() {
		assertTrue(Validate.validDate("10/10/1010") != null);
		assertTrue(Validate.validDate("10/10") == null);
		assertTrue(Validate.validDate("010") == null);
		assertTrue(Validate.validDate("29/12/1010") != null);

	}

	@Test
	public void testValidBoolean() {
		assertTrue(Validate.validBoolean("true"));
		assertFalse(Validate.validBoolean("false"));
		assertFalse(Validate.validBoolean("t"));
		assertFalse(Validate.validBoolean("f"));
		assertFalse(Validate.validBoolean("yes"));
		assertFalse(Validate.validBoolean("no"));

	}

	@Test
	public void testValidAmount() {
		assertTrue(new Double(-1).equals(Validate.validAmount("900/")));
		assertTrue(new Double(-1).equals(Validate.validAmount("900$")));
		assertTrue(new Double(-1).equals(Validate.validAmount("asdf/")));
		assertTrue(new Double(-1).equals(Validate.validAmount("2.001a")));
		assertTrue(new Double(-1).equals(Validate.validAmount("&200.01")));
		assertTrue(new Double(50).equals(Validate.validAmount("50.00")));
		assertTrue(new Double(-3).equals(Validate.validAmount("-3")));
		assertTrue(new Double(-3.5).equals(Validate.validAmount("-3.5")));

	}

}
