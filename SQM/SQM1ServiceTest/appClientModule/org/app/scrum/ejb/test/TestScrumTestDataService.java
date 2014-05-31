package org.app.scrum.ejb.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.app.service.entities.ExecutieTest;
import org.app.service.entities.Test1;
import org.app.service.entities.TestSprintDataService;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestScrumTestDataService {

	private  static Logger logger = Logger.getLogger(TestScrumTestDataService.class.getName());
	private static TestSprintDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = TestSprintDataServiceEJBFactory.getScrumTestRepositoryService();
	}
	
	
	@Test
	public void testGetMessage() {
		String message = service.getMessage();
		assertNotNull("Message not returned from service!", message);
		logger.info("DEBUG testGetMessage: " + message);
		
	}
	
	@Test
	public void testCreateTest() {
	
	Test1 test1= new Test1 (444, new Date(), new ArrayList<ExecutieTest>(), "Test 06");
	test1=service.add(test1);
	logger.info("DEBUG createTest: saved test: " + test1);
	assertNotNull("Fail to create new test1 in repository!", test1);
	test1=service.getById(06);
	assertNotNull("Fail to find new test1 in repository!", test1);
	logger.info("DEBUG createTest: queried test" + test1 );	
	}
	
	@Test
	public void testCreateNewTest1(){
		Test1 test1 = service.createNewTest(07);
		assertNotNull("Fail to create new test in repository!", test1);
		test1.setNumeTest(test1.getNumeTest() + "-changed by test client");
		List<ExecutieTest>executieteste=test1.getExecutieteste();
		for(ExecutieTest et:executieteste)
			et.setIdExecutieTest(et.getIdExecutieTest()+ " -changed by test client ");
		test1= service.add(test1);
		assertNotNull("Fail to save new test1 in repository!", test1);
		logger.info("DEBUG createNewTest1: test changed:" + test1);
		test1= service.getById(06);
		assertNotNull("Fail to find changed test in repository!", test1);
		logger.info("DEBUG createNewTest1: queried test1 :" + test1);
		
	}
	@Test
	public void testToCollection() {
		Collection<Test1> teste1= service.toCollection();
		assertTrue("Fail to read tests from repository!", teste1 !=null);
		logger.info("DEBUG testtoCollection:" +  teste1.size());
		assertTrue("Fail to read any tests from repository!", teste1.size()>0);
	}
	
	@Test
	public void testAdd() {
		Test1 test1= service.getById(06);
		assertNotNull("Fail to get test from repository!", test1);
		test1.setNumeTest(test1.getNumeTest() +  "- updated by test client");
		test1= service.add(test1);
		assertNotNull("Fail to save updated test in repository!", test1);
		test1=service.getById(06);
		assertNotNull("Fail to find updated test in repository!", test1);
		logger.info("DEBUG testAdd: test1 updated :" + test1);
		
	}
	
	@Test
	public void testRemove() {
		Test1 test1= service.getById(06);
		assertNotNull("Fail to get  test from repository!", test1);
		service.remove(test1);
		test1= service.getById(06);
		logger.info("DEBUG testAdd: test1 removed :" + test1);
		assertNotNull("Fail to remove test in repository!", test1);
	
}
}