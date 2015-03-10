package org.app.test;

import org.app.NumberGeneratorApp;
import org.junit.Assert;
import org.junit.Test;
 
public class NumberGeneratorTest {
 
	@Test
	public void testLengthOfTheUniqueKey() {
		System.out.println("Running Test 2 ... ... ...");
		NumberGeneratorApp obj = new NumberGeneratorApp();
		Assert.assertEquals(36, obj.generateUniqueKey().length());
		System.out.println("Result: obj.generateUniqueKey() = " + obj.generateUniqueKey());
		Assert.assertNotNull(obj.generateUniqueKey2());
		System.out.println("Result: obj.generateUniqueKey2() = " + obj.generateUniqueKey2());
		System.out.println("End Running Test 2 ... ... ...");
	}
}