package org.app.scrum.ejb.test;

import static org.junit.Assert.*;

import java.util.logging.Logger;

import org.app.scrum.Project;
import org.app.scrum.ejb.ScrumProjectRepositoryEJB;
import org.app.scrum.ejb.ScrumProjectRepositoryService;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestScrumProjectRepositoryService {
	private static Logger logger = Logger.getLogger(TestScrumProjectRepositoryService.class.getName());
	private static ScrumProjectRepositoryService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = ScrumProjectRepositoryServiceFactory.getScrumProjectRepositoryService();
	}
	
	@Test
	public void test() {
		logger.info("START TESTING ...");
		
		Project project = new Project(1001, "Test project 1001"); 
		service.add(project);
		
		assertTrue("Fail to add new project in repository", service.size() == 1);
		
		service.remove(project);
		
		assertTrue("Fail to delete project in repository", service.size() == 0);
	}

}
