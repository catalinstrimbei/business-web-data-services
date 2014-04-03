package org.app.scrum.ejb.test;

import static org.junit.Assert.*;

import java.util.List;
import java.util.logging.Logger;

import org.app.scrum.ejb.ScrumProjectDataService;
import org.app.scrum.project.Project;
import org.app.scrum.project.Release;
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

//	@Test
	public void createNewProject(){
		logger.info("START TEST createNewProject ...");
		
		Project project = service.createNewProject();
		assertTrue("Fail to create new project in repository", service.size() == 1);
//		project = service.getById(project.getProjectNo());
//		project = service.refresh(project);
//		List<Release> releases = service.getReleases(project);
		List<Release> releases = project.getReleases();
		assertNotNull("Fail to create releases in repository (is null)", releases);
		assertTrue("Fail to create releases in repository (is empty)", releases.size() >= 1);
		for(Release r: releases){
			logger.info("DEBUG release: " + r);
			r.setDescription("Client Updated");
		}
		project = service.add(project);
//		assertTrue("Fail to delete project in repository", service.size() == 0);
//		service.refresh(project);
		logger.info("... END TEST createNewProject!");
	}
	
	@Test
	public void getProject(){
		logger.info("START TEST getProject ...");
		Project project = service.getByKey(0);
		assertNotNull("Fail to create get project from repository (is null)", project);
		logger.info("END TEST getProject ...");
	}
}
