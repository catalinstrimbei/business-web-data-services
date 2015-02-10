package org.app.scrum.ejb.test;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.app.service.project.Activitate;
import org.app.service.project.Proiect;
import org.app.service.services.ProjectSprintDataService;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestScrumProjectDataService {
	private static Logger logger = Logger.getLogger(TestScrumProjectDataService.class.getName());
	private static ProjectSprintDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = ProjectSprintDataServiceEJBFactory.getScrumProjectRepositoryService();
	}
	
	/* CHECK EJB live test*/
	@Test
	public void testGetMessage(){
		String message = service.getMessage();
		assertNotNull("Message not returned from service!", message);
		logger.info("DEBUG testGetMessage: " + message);
	}

	/* CREATE Test 1 (create-read)*/
//	@Test
	public void testCreareProiect(){
		Proiect proiect = new Proiect(7001, "Proiect Test 7001", 2000.00); // !!!
		proiect = service.add(proiect);
		logger.info("DEBUG CreareProiect: salvare proiect: " + proiect);
		assertNotNull("Fail to create new project in repository!", proiect);
		proiect = service.getById(7001); // !!!
		assertNotNull("Fail to find new project in repository!", proiect);
		logger.info("DEBUG CreareProiect: queried project" + proiect);
	}
	
	/* CREATE Test 2: create aggregate*/
//	@Test
	public void testCreareProiectNou(){
		Proiect proiect = service.createNewProject(7002);
		assertNotNull("Fail to create new project in repository!", proiect);
		// update project
		proiect.setNume(proiect.getNume() + " - changed by test client");		
		List<Activitate> activitati = proiect.getActivitati();
		// update project components
		for(Activitate r: activitati)
			r.setDescriere(r.getDescriere() + " - changed by test client");
		proiect = service.add(proiect);
		assertNotNull("Fail to save new project in repository!", proiect);
		logger.info("DEBUG createNewProject: project changed: " + proiect);
		// check read
		proiect = service.getById(7002);  // !!!
		assertNotNull("Fail to find changed project in repository!", proiect);
		logger.info("DEBUG createNewProject: queried project" + proiect);
	}	
	
	/* READ Test (read-collection) */
//	@Test
	public void testToCollection(){
		Collection<Proiect> projects = service.toCollection();
		assertTrue("Fail to read projects from repository!", projects != null);
		logger.info("DEBUG testToCollection:" + projects.size());
		assertTrue("Fail to read any project from repository!", projects.size() > 0);
	}
	
	/* UPDATE Test (read-instance&update)*/
//	@Test
	public void testAdd(){
		Proiect proiect = service.getById(7001);
		assertNotNull("Fail to get project from repository!", proiect);
		proiect.setNume(proiect.getNume() + " - updated by test client");
		proiect = service.add(proiect);
		assertNotNull("Fail to save updated project in repository!", proiect);
		// check read
		proiect = service.getById(7001);
		assertNotNull("Fail to find updated project in repository!", proiect);
		logger.info("DEBUG testAdd: project updated: " + proiect);		
	}
	
	/* REMOVE Test */
	@Test
	public void testRemove(){
		Integer IdProiect = 7001;
		Proiect proiect = service.getById(IdProiect);
		assertNotNull("Fail to get project from repository!", proiect);
		service.remove(proiect);
		// check read
		proiect = service.getById(IdProiect);
		logger.info("DEBUG testAdd: project removed: " + proiect);
		assertNull("Fail to remove project in repository!", proiect);
	}

	
	/* READ Test (read-collection-aggregate-components) */
//	@Test
	public void testGetActivitateById(){
		Activitate activitate = service.getActivitateById(305);
		assertTrue("Fail to read releases from repository!", activitate != null);
		logger.info("DEBUG testGetActivitateById:" + activitate);
	}
}




