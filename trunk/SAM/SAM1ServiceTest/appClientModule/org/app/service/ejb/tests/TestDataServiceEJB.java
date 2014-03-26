package org.app.service.ejb.tests;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.app.service.ejb.DataService;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestDataServiceEJB {
	private static Logger logger = Logger.getLogger(TestDataServiceEJB.class.getName());
	private static DataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = DataServiceEJBFactory.getDataService();
	}
	
	@Test
	public void testSayMessage() {
		String message = service.sayMessage("Test EJB Data Service!");
		logger.info("DEBUG: Test message: " + message);
	}

}
