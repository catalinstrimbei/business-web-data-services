package org.app.spm2.ejb.test;

import java.util.logging.Logger;

import org.app.service.ejb.ClientDataService;
import org.app.service.ejb.DataService;
import org.app.service.entities.Client;
import org.app.service.entities.PersoanaFizica;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestClientDataServiceEJB {
	private static Logger logger = Logger.getLogger(TestClientDataServiceEJB.class.getName());
	private static ClientDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = ClientDataServiceEJBFactory.getScrumProjectRepositoryService();
	}
	
//	@Test
	public void test() {
		logger.info("DEBUG: Junit TESTING ...");
		logger.info("DEBUG: EJB Response ..." + service.test());
	}

	@Test
	public void testSave() {
		logger.info("DEBUG: Junit TESTING ...");
		PersoanaFizica client = new PersoanaFizica();
		client.setIdClient(1001);
		client.setNume("N 1001");
		client.setPrenume("P 1001");
		client = (PersoanaFizica) service.save(client);
		logger.info("DEBUG: EJB Response ..." + client);
	}	
}
