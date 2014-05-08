package org.app.spm5.ejb.test;

import java.util.Collection;

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
//READ test
	@Test
	public void testTocollection(){
		Collection<Produse> produse = service.toCollection();
		assertTrue("Nu s-a putut citi produsele din repository", produse !=null);
		logger.info("DEBUG testToCollection:" + produse.size());
		assertTrue("Nu s-a putut citi nici un proiect din repository", produse.size() > 0);
	}
//Update test
		@Test
	public void testAdd(){
		Produse produse = service.getById(1001);
		assertNotNull("Imposibil de incarcat", produse);
		produse.setDenumireProdus(produse.getDenumireProdus() + "- updated by test client");
		produse = service.add(produse);
		assertNotNull("Imposibil de salvat produsele in repository", produse);
		logger.info("DEBUG testAdd: produse updated: "+produse);
		
	
	}
	//REMOVE
	public void testRemove(){
		Produse produse = service.getById(1001);
		assertNotNull("Imposibil de incarcat produsele din repository", produse);
		service.remove(produse);
		//check read
		produse = service.getById(1001);
		logger.info("DEBUG testAdd: produse sterse: " + produse);
		assertNull("Imposibil de sters produsul din repository", produse);
	}
	
}
