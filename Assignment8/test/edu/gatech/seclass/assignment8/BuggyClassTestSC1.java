package edu.gatech.seclass.assignment8;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class BuggyClassTestSC1 {

	private BuggyClass myBuggyClass;

	@Before
	public void setUp() throws Exception {
		myBuggyClass = new BuggyClass();
	}

	@After
	public void tearDown() throws Exception {
		myBuggyClass = null;
	}
	@Test
	public void test1() {
		
		assertEquals(myBuggyClass.method1(1), 7);
	}
	@Test
	public void test2() {
		assertEquals(myBuggyClass.method1(-1), 4);
	}
	

}
