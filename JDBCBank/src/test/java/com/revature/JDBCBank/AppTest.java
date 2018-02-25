package com.revature.JDBCBank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class AppTest {
	
	Testprinter testp;
	
    @Before
    public void setup() {
    	testp = new Testprinter();
    }

    @Test
    public void testHi() {
    	assertTrue(testp.printHi().equals("hi"));
    }
}
