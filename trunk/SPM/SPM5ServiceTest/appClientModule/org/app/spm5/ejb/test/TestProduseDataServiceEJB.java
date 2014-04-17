package org.app.spm5.ejb.test;

import java.util.logging.Logger;

import org.app.service.ejb.DataService;
import org.app.service.ejb.ProduseDataService;
import org.app.service.entities.Produse;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestProduseDataServiceEJB {
	private static Logger logger = Logger.getLogger(TestProduseDataServiceEJB.class.getName());
	private static ProduseDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = ProduseDataServiceEJBFactory.getScrumProjectRepositoryService();
	}
	
//	@Test
	public void test() {
		logger.info("DEBUG: Junit TESTING ...");

		logger.info("DEBUG: EJB Response ..." + service.test());
	}
	
	@Test
	public void testSave() {
		logger.info("DEBUG: Junit TESTING ...");
		Produse p = new Produse();
		p.setIdProdus(1001);
		p.setDenumireProdus("Produs Test");
		p = service.save(p);
		logger.info("DEBUG: EJB Response ..." + p);
	}	

}
