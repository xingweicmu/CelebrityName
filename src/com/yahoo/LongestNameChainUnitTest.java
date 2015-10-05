package com.yahoo;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * This class is the unit test class for the method findLongestNameChain().
 * It covers the following test cases: 
 * 
 * 1. the input array is empty;
 * 2. the input array containing one name chain;
 * 3. the input array containing a circle name chain;
 * 4. the input array containing two name chain;
 * 5. the input array containing duplicated names;
 * 6. the input array containing no name chain;		//TODO
 * 
 * @author xingwei
 * @version 1.0
 *
 */
public class LongestNameChainUnitTest extends TestCase {

	@Test
	public void testFindLongestNameChainWithEmptyArray() {
		CelebrityName test = new CelebrityName();
		String[] testSet = new String[] {};
		ArrayList<String> result = test.findLongestNameChain(testSet);
		assertEquals(0, result.size());
	}

	@Test
	public void testFindLongestNameChainWithArray() {
		CelebrityName test = new CelebrityName();
		String[] testSet = new String[] { "Elton John", "John Lennon", "Lennon James" };
		ArrayList<String> result = test.findLongestNameChain(testSet);
		assertEquals("Elton John Lennon James", result.get(0));
	}

	@Test
	public void testFindLongestNameChainWithCircleArray() {
		CelebrityName test = new CelebrityName();
		String[] testSet = new String[] { "Elton John", "John Lennon", "Lennon Elton" };
		ArrayList<String> result = test.findLongestNameChain(testSet);
		assertEquals("Elton John Lennon", result.get(0));
	}

	@Test
	public void testFindLongestNameWithTwoChainArray() {
		CelebrityName test = new CelebrityName();
		String[] testSet = new String[] { "Elton John", "John Lennon", "Lennon James", "Lennon Taylor" };
		ArrayList<String> result = test.findLongestNameChain(testSet);
		// Found the error here
		assertEquals("Elton John Lennon James", result.get(0));
		assertEquals("Elton John Lennon Taylor", result.get(1));

	}

	@Test
	public void testFindLongestNameWithDuplicatedString() {
		CelebrityName test = new CelebrityName();
		String[] testSet = new String[] { "Lennon James", "Lennon James" };
		ArrayList<String> result = test.findLongestNameChain(testSet);
		assertEquals("Lennon James", result.get(0));
		assertEquals("Lennon James", result.get(1));
	}

}
