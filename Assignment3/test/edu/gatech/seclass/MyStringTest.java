package edu.gatech.seclass;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyStringTest {

	private MyString mystring;

	@Before
	public void setUp() throws Exception {
		mystring = new MyString();
	}

	@After
	public void tearDown() throws Exception {
		mystring = null;
	}

	@Test
	public void testGetVowels1() {
		mystring.setString("This is my string. It includes three numbers: 1 2 3");
		assertEquals("iiiIiueeeue", mystring.getVowels());
	}
	
	//Test for no vowels
	@Test
	public void testGetVowels2() {
		mystring.setString("n0 v0w3ls h3r3.");
		assertEquals("", mystring.getVowels());
	}
	//Test for E, U, a, A, O, o
	@Test
	public void testGetVowels3() {
		mystring.setString("E, U, a, A, O, o : 6 v0w3ls.)");
		assertEquals("EUaAOo", mystring.getVowels());
	}

	//Test for e and o.
	@Test
	public void testNumberOfVowels1() {
		mystring.setString("yello");
		assertEquals(2, mystring.numberOfVowels());
	}
	
	//Test for no vowels
	@Test
	public void testNumberOfVowels2() {
		mystring.setString("n0 v0w3ls h3r3.");
		assertEquals(0, mystring.numberOfVowels());

	}
	// Test for A, a, E, I, i, O, U, u. Includes spaces, punctuation, and numbers.
	@Test
	public void testNumberOfVowels3() {
		mystring.setString("This is my string. It includes three numbers: 1 2 3. E, U, a, A, O.");
		assertEquals(16, mystring.numberOfVowels());
	}
	// Test basic functionality of getCharacter.
	@Test
	public void testGetCharacter1() {
		mystring.setString("This is my string. It includes three numbers: 1 2 3");
		assertTrue('n' == mystring.getCharacter(16));
	}
	//Test IllegalArgumentException by passing in 0.
	@Test(expected = IllegalArgumentException.class)
	public void testGetCharacter2() {
		
		mystring.setString("This is a string to test Illegal Argument Exception in getChracter method.");
		char theChar = mystring.getCharacter(0);
	}
	//Test IllegalIndexException by passing in the string length plus one.
	@Test(expected = IllegalIndexException.class)
	public void testGetCharacter3() {
		mystring.setString("This a test string.");
		char theChar = mystring.getCharacter(mystring.getString().length()+1);
	}

	@Test(expected = IllegalIndexException.class)
	public void testFlipCaseInSubstring1() {
		mystring.setString("abcde");
		mystring.flipCaseInSubstring(1, 10);
	}
	//This tests a string that has spaces, letters (upper and lower case), punctuation, and numbers.
	// The test also runs flipCaseInSubstring on the entire string (0 .. mystring.length())
	@Test
	public void testFlipCaseInSubstring2() {
		mystring.setString("This is a test with numbers and punctuation: 3!");
		mystring.flipCaseInSubstring(1,  47);
		assertEquals("tHIS IS A TEST WITH NUMBERS AND PUNCTUATION: 3!", mystring.getString());
	}
	
	// This test runs flipCaseInSubstring on the beginning portion of a string. 
	@Test
	public void testFlipCaseInSubstring3() {
		mystring.setString("This is test number 3, and it tests flipping case on only part of the string.");
		mystring.flipCaseInSubstring(1, 23);
		assertEquals("tHIS IS TEST NUMBER 3, and it tests flipping case on only part of the string.", mystring.getString());
	}
	
	//This test runs flipCaseInSubstring on a substring in the middle of the original string 
	@Test
	public void testFlipCaseInSubstring4() {
		mystring.setString("This is test number 4, and it tests flipping case on only a middle part of the string.");
		mystring.flipCaseInSubstring(16, 26);
		assertEquals("This is test nuMBER 4, AND it tests flipping case on only a middle part of the string.", mystring.getString());
	}
	
	//This test runs flipCaseInSubstring on a substring at the end of the original string 
	@Test
	public void testFlipCaseInSubstring5() {
		mystring.setString("This is test number 5. It tests flipping case on -ONLY- the end part of the string.");
		mystring.flipCaseInSubstring(50, 83);
		assertEquals("This is test number 5. It tests flipping case on -only- THE END PART OF THE STRING.", mystring.getString());
	}
	// Test the IllegalArgumentException by passing in a startPosition that is greater than the endPosition.
	@Test(expected = IllegalArgumentException.class)
	public void testFlipCaseInSubstring6() {
		mystring.setString("abcde");
		mystring.flipCaseInSubstring(5, 1);
	}
}
