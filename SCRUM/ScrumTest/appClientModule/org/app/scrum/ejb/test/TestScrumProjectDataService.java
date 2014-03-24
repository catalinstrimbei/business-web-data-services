package org.app.scrum.ejb.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import org.app.scrum.Project;
import org.app.scrum.Release;
import org.app.scrum.ejb.ScrumProjectDataServiceEJB;
import org.app.scrum.ejb.ScrumProjectDataService;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestScrumProjectDataService {
	private static Logger logger = Logger.getLogger(TestScrumProjectDataService.class.getName());
	private static ScrumProjectDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = ScrumProjectDataServiceEJBFactory.getScrumProjectRepositoryService();
	}
	
//	@Test
	public void test() {
		logger.info("START TESTING ...");
		
		Project project = new Project(1001, "Test project 1001"); 
		service.add(project);
		
		assertTrue("Fail to add new project in repository", service.size() == 1);
		
		service.remove(project);
		
		assertTrue("Fail to delete project in repository", service.size() == 0);
	}

	@Test
	public void createNewProject(){
		logger.info("START TEST createNewProject ...");
		
		Project project = service.createNewProject();
		assertTrue("Fail to create new project in repository", service.size() == 1);
		
		List<Release> releases = service.getReleases(project);
		assertNotNull("Fail to create releases in repository (is null)", releases);
		assertTrue("Fail to create releases in repository (is empty)", releases.size() >= 1);
		
//		service.remove(project);
//		assertTrue("Fail to delete project in repository", service.size() == 0);
		logger.info("... END TEST createNewProject!");
	}
}
