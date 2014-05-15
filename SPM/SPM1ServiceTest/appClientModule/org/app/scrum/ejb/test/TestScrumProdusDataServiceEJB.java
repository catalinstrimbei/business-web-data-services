package org.app.scrum.ejb.test;

import java.util.List;
import java.util.logging.Logger;

import org.app.service.ejb.DataService;
import org.app.service.ejb.ProdusSprintDataService;
import org.app.service.entities.Editie;
import org.app.service.entities.Produs;
import org.app.service.entities.Versiune;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestScrumProdusDataServiceEJB {
	private static Logger logger = Logger.getLogger(TestScrumProdusDataServiceEJB.class.getName());
	private static ProdusSprintDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = ProdusSprintDataServiceEJBFactory.getScrumProjectRepositoryService();
	}
	
	/*check ejb live test*/
	
	@Test
	public void testGetMessage() {
		
		String message = service.getMessage();
		assertNotNull("Message not returned from service!", message);
		logger.info("DEBUG:testGetMessage:" + message);
	}


	/* create test1 create-read*/
	 @Test
	public void testCreateProdus() {
		 Produs produs= new Produs(1,"Produs Test 101","categ test 1");
		 produs= service.add(produs);
		logger.info("DEBUG:  create Produs: Saved Produs: "+produs);
		assertNotNull("Fail to Create new Produs in repository!", produs);
		 produs= service.getById(1);
		assertNotNull("Fail to find new Produs in repository!", produs);
		logger.info("DEBUG: create Produs: queried Produs: "+produs);
	}
	 /* create test 2- create aggregate*/
	 @Test
		public void testCreateNewProdus() {
			 Produs produs= service.createNewProdus(2);
			 assertNotNull("Fail to Create new Produs in repository!", produs);
			 //update produs
			 produs.setDenProdus(produs.getDenProdus()+" - changed by test client");
			 List<Versiune>versiuni=produs.getVersiuni();
			 List<Editie>editii=produs.getEditii();
			 //update produs components
			 for (Versiune v: versiuni)
				 v.setDescriere(v.getDescriere()+ " - changed by test client");
			 produs= service.add(produs);
			assertNotNull("Fail to save new Produs in repository!", produs);
			logger.info("DEBUG:  createNewProdus:  Produs changed: "+produs);
			//check read
			 produs= service.getById(1);
			assertNotNull("Fail to find changed Produs in repository!", produs);
			logger.info("DEBUG: createNewProdus: queried Produs: "+produs);
		}
	 
	 //read test read collection
	 @Test
		public void testToCollection() {
			 //?????????????????????????????????????(List<Produs>) trebuie???????????????
			 List<Produs> produse=(List<Produs>) service.toCollection();
			 
			assertTrue("Fail to read produse from repository!", produse!=null);
			logger.info("DEBUG: testToCollection: "+produse.size());
			assertTrue("Fail to read any produs from repository!", produse.size()>0);
			
		}
	 
	 //update test - read instances&update
	 @Test
		public void testAdd() {
			Produs produs= service.getById(1);
			 assertNotNull("Fail to get Produs from repository!", produs);
			
			 produs.setDenProdus(produs.getDenProdus()+" - updated by test client");	
			 produs= service.add(produs);
			assertNotNull("Fail to save updated Produs in repository!", produs);
			//check read
			 produs= service.getById(1);
			assertNotNull("Fail to find updated Produs in repository!", produs);
			logger.info("DEBUG: testAdd: Produs updated: "+produs);
		}
	 
	 //remove test 

	 @Test
		public void testRemove() {
			Produs produs= service.getById(1);
			 assertNotNull("Fail to get Produs from repository!", produs);
			service.remove(produs);
			//check read
			 produs= service.getById(1);
			 logger.info("DEBUG: testAdd: Produs removed: "+produs);
			 assertNotNull("Fail to remove Produs in repository!", produs);
			
		}
}
