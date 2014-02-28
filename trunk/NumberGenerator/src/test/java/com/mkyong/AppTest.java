package com.mkyong;

import org.junit.Assert;
import org.junit.Test;
 
public class AppTest {
 
	@Test
	public void testLengthOfTheUniqueKey() {
		System.out.println("Running Test ... ... ...");
		App obj = new App();
		Assert.assertEquals(36, obj.generateUniqueKey().length());
 
	}
}
