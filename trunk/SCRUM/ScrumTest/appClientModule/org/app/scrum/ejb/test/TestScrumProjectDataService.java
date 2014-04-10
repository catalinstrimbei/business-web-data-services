package org.app.scrum.ejb.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import org.app.scrum.project.Project;
import org.app.scrum.project.Release;
import org.app.scrum.services.ProjectSprintDataService;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestScrumProjectDataService {
	private static Logger logger = Logger.getLogger(TestScrumProjectDataService.class.getName());
	private static ProjectSprintDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = ProjectSprintDataServiceEJBFactory.getScrumProjectRepositoryService();
	}

	@Test
	public void createNewProject(){
		logger.info("START TEST createNewProject ...");
		
		Project project = service.createNewProject(1003);
		assertTrue("Fail to create new project in repository", service.size() > 0);

		List<Release> releases = project.getReleases();
		logger.info("DEBUG releases: " + releases);
				
		project.setName(project.getName() + " - changed by test client");
		project = service.add(project);
		logger.info("DEBUG project changed: " + project);
		
		logger.info("... END TEST createNewProject!");
	}
	
//	@Test
	public void getProject(){
		logger.info("START TEST getProject ...");
		Project project = service.getById(null);
		assertNotNull("Fail to create get project from repository (is null)", project);
		logger.info("END TEST getProject ...");
	}
}
