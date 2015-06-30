package edu.gatech.seclass.assignment8;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BuggyClassTestBC3 {

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
		
		assertEquals(myBuggyClass.method3(1, "test"), 4);
	}
	
	@Test
	public void test2() {
		
		assertEquals(myBuggyClass.method3(-1,"test"), 4);
	}
	
	@Test
	public void test3() {
		
		assertEquals(myBuggyClass.method3(0,"test"), 4);
	}

}
