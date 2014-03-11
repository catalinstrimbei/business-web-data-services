package org.app;

import org.junit.Assert;
import org.junit.Test;
import org.tests.App;
 
public class AppTest {
 
	@Test
	public void testLengthOfTheUniqueKey() {
		System.out.println("Running Test ... ... ...");
		App obj = new App();
		Assert.assertEquals(36, obj.generateUniqueKey().length());
 
	}
}
