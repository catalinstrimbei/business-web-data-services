/**
 * 
 */
package org.app.spm2.ejb.test;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.logging.Logger;

import org.app.service.ejb.ClientDataService;
import org.app.service.ejb.ContractDataService;
import org.app.service.entities.Contract;
import org.app.service.entities.ContractSuport;
import org.app.service.entities.PersoanaFizica;
import org.app.service.entities.PersoanaJuridica;
import org.app.service.entities.Produs;
import org.junit.BeforeClass;
import org.junit.Test;
/**
 * @author Alina
 *
 */
public class TestContractDataServiceEJB {
	private static Logger logger = Logger.getLogger(TestContractDataServiceEJB.class.getName());
	private static ContractDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = ContractDataServiceEJBFactory.getScrumProjectRepositoryService();
	}
	@Test
	public void testGetMessage(){
		String message=service.getMessage();
		assertNotNull("Message not returnet from service!", message);
		logger.info("DEBUG testGetMessage:"+ message);
	}
	
	//create aggregate
	@Test
	public void testCreateNewClient(){
		ContractSuport  contract= service.createNewContract(102);
		assertNotNull("Fail to create new contract in repository!:",contract);
		//update client
		contract.setActivitateSuport(contract.getActivitateSuport()+"changed by test");
		List<Produs> produse = (List<Produs>) contract.getProdus();
		//update contract components 
		for (Produs c:produse)
			c.setNume(c.getNume()+"changed by test");
		contract=service.add(contract);
		assertNotNull("Fail to save new contract in repository", contract);
		logger.info("DEBUG createNewContract: client changed"+contract );
		//check read
		contract= service.getById(101);
		assertNotNull("Fail to find changed contract in repository!", contract);
		logger.info("DEBUG createNewContract: queried contract: "+contract);
	}
	//update test
	@Test
	public void testAdd(){
		ContractSuport  contract = service.getByID(1001);
		assertNotNull("Fail to get new client in repository!", contract);
		contract.setActivitateSuport(contract.getActivitateSuport()+"-updated by test");
		contract= service.add(contract);
		//check read
		contract =service.getByID(101);
		assertNotNull("Fail to find update contract in repository! ", contract);
		logger.info("DEBUG testAdd: contract update: "+contract);
		
	}
	//remove test
	@Test
	public void testRemove(){
		ContractSuport contract = service.getByID(101);
		assertNotNull("Fail to create new contract in repository!" + contract);
		service.remove(contract);
		//check read
		contract =service.getByID(1001);
		logger.info("DEBUG testAdd: contract remove: queried contract: "+contract);
		assertNotNull("Fail to remove contract in repository!", contract);
		
		
	}
	
//	@Test
	public void test() {
		logger.info("DEBUG: Junit TESTING ...");
		logger.info("DEBUG: EJB Response ..." + service.test());
	}

	@Test
	public void testSave() {
		logger.info("DEBUG: Junit TESTING ...");
		ContractSuport  contract = new ContractSuport ();
		contract.setNrContract(1001);
		contract.setActivitateSuport("A 1001");
		contract = (ContractSuport ) service.save(contract);
		assertNotNull ("Client not returned ",contract);
		logger.info("DEBUG: EJB Response ..." + contract);
	}	
}
