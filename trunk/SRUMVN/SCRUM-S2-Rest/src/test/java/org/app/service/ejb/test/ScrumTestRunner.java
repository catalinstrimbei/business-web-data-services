package org.app.service.ejb.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class ScrumTestRunner {
	public static void main(String... args){
		Result result = JUnitCore.runClasses(TestDataServiceEJB.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	}
}
