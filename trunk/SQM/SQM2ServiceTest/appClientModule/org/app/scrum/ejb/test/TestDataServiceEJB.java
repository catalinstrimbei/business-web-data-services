package org.app.scrum.ejb.test;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.app.service.ejb.DataService;
import org.app.service.entities.Task;
import org.hibernate.mapping.Collection;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestDataServiceEJB {
	private static Logger logger = Logger.getLogger(TestDataServiceEJB.class.getName());
	private static DataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = DataServiceEJBFactory.getScrumProjectRepositoryService();
	}
	
	@Test
	public void test() {
		logger.info("DEBUG: Junit TESTING ...");
		String response = service.sayRest();
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	/* CREATE Test 1 (create-read)*/
   	@Test
	public void testCreateTask(){
		Task task = new Task(7001, "Project Test 7001", new Date()); // !!!
		task = service.add(task);
		logger.info("DEBUG createTest: saved test: " + task);
		assertNotNull("Fail to create new task in repository!", task);
		task = service.getById(7001); // !!!
		assertNotNull("Fail to find new task in repository!", task);
		logger.info("DEBUG createtask: queried task" + task);
	}
	
	/* CREATE Test 2: create aggregate*/
	@Test
	public void testCreateNewTask(){
		Task task = service.createNewTask(7002);
		assertNotNull("Fail to create new task in repository!", task);
		// update task
		task.setName(task.getName() + " - changed by test client");		
		List<org.app.service.entities.Test> tests = task.getTests();
		// update task components
		for(org.app.service.entities.Test t: tests)
			t.setDescription(t.getDescription() + " - changed by test client");
		task = service.add(task);
		assertNotNull("Fail to save new task in repository!", task);
		logger.info("DEBUG createNewTask: task changed: " + task);
		// check read
		task = service.getById(7002);  // !!!
		assertNotNull("Fail to find changed task in repository!", task);
		logger.info("DEBUG createNewTask: queried task" + task);
	}	
	
	/* READ Test (read-collection) */
	@Test
	public void testToCollection(){
		Collection tasks = (Collection) service.toCollection();
		assertTrue("Fail to read tasks from repository!", tasks != null);
	}
	
	/* UPDATE Test (read-instance&update)*/
	@Test
	public void testAdd(){
		Task task = service.getById(7001);
		assertNotNull("Fail to get task from repository!", task);
		task.setName(task.getName() + " - updated by test client");
		task = service.add(task);
		assertNotNull("Fail to save updated task in repository!", task);
		// check read
		task = service.getById(7001);
		assertNotNull("Fail to find updated task in repository!", task);
		logger.info("DEBUG testAdd: task updated: " + task);		
	}
	
	/* REMOVE Test */
	@Test
	public void testRemove(){
		Integer taskNo = 7001;
		Task task = service.getById(taskNo);
		assertNotNull("Fail to get project from repository!", task);
		service.remove(task);
		// check read
		task = service.getById(taskNo);
		logger.info("DEBUG testAdd: project removed: " + task);
		assertNull("Fail to remove project in repository!", task);
	}

	

}
