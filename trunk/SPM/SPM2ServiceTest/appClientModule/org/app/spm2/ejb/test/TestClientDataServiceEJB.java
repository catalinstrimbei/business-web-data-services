package org.app.spm2.ejb.test;

import java.util.List;
import java.util.logging.Logger;
import org.app.service.ejb.ClientDataService;
import org.app.service.ejb.DataService;
import org.app.service.entities.Client;
import org.app.service.entities.Contract;
import org.app.service.entities.PersoanaFizica;
import org.app.service.entities.PersoanaJuridica;
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
	@Test
	public void testGetMessage(){
		String message=service.getMessage();
		assertNotNull("Message not returnet from service!", message);
		logger.info("DEBUG testGetMessage:"+ message);
	}
	//create-read
	//@Test
	public void testCreateClient(){
		PersoanaFizica client = new PersoanaFizica("Str.Fericirii","0758004288", "radu@gmail.com","RO32SV65445BRDE5454544","Radu", "Ion");
		client = service.add(client);
		logger.info("DEBUG createClient: saved client: "+client);
		assertNotNull("Fail to create new client in repository!" + client);
		client = service.getById(101);
		assertNotNull("Fail to find new client in repository!" + client);
		logger.info("DEBUG createClient: queried client: "+client);
		
		PersoanaJuridica clientJ= new PersoanaJuridica("Str.Palatului","0753226988","ion@gmal.com","RO32SV36666BRDE2555444","IonSRL", 1988097, "J40/8118/2002");
		clientJ = service.add(clientJ);
		logger.info("DEBUG createClient: saved client: "+clientJ);
		assertNotNull("Fail to create new client in repository!" + clientJ);
		clientJ = service.getByIdJ(11);
		assertNotNull("Fail to find new client in repository!" + clientJ);
		logger.info("DEBUG createClient: queried client: "+clientJ);
	}
	//create aggregate
//@Test
	public void testCreateNewClient(){
		PersoanaFizica client= service.createNewClient(102);
		assertNotNull("Fail to create new client in repository!:",client);
		//update client
		client.setNume(client.getNume()+"changed by test");
		List<Contract> contracte = (List<Contract>) client.getContract();
		//update client components 
		for (Contract c:contracte)
			c.setObservatii(c.getObservatii()+"changed by test");
		client=service.add(client);
		assertNotNull("Fail to save new client in repository", client);
		logger.info("DEBUG createNewClient: client changed"+client );
		//check read
		client= service.getById(101);
		assertNotNull("Fail to find changed client in repository!", client);
		logger.info("DEBUG createNewClient: queried client: "+client);
	}
	//update test
//@Test
	public void testAdd(){
		PersoanaFizica client = service.getByID(101);
		assertNotNull("Fail to get new client in repository!", client);
		client.setNume(client.getNume()+"-updated by test");
		client= service.add(client);
		//check read
		client =service.getByID(101);
		assertNotNull("Fail to find update client in repository! ", client);
		logger.info("DEBUG testAdd: client update: "+client);
		
	}
	//remove test
//	@Test
	public void testRemove(){
		PersoanaFizica client = service.getByID(101);
		assertNotNull("Fail to create new client in repository!" + client);
		service.remove(client);
		//check read
		client =service.getByID(101);
		logger.info("DEBUG testAdd: client remove: queried client: "+client);
		assertNotNull("Fail to remove client in repository!", client);
		
		
	}
	
//	@Test
	public void test() {
		logger.info("DEBUG: Junit TESTING ...");
		logger.info("DEBUG: EJB Response ..." + service.test());
	}

//	@Test
	public void testSave() {
		logger.info("DEBUG: Junit TESTING ...");
		PersoanaFizica client = new PersoanaFizica();
		client.setIdClient(1001);
		client.setNume("N 1001");
		client.setPrenume("P 1001");
		client = (PersoanaFizica) service.save(client);
		assertNotNull ("Client not returned ",client);
		logger.info("DEBUG: EJB Response ..." + client);
	}	
}
