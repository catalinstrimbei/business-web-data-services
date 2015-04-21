package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.ProjectDataService;
import org.app.service.entities.Project;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestProjectDataServiceEJB {
	private static Logger logger = Logger.getLogger(TestProjectDataServiceEJB.class.getName());
	
	@EJB
	private static ProjectDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = ProjectDataServiceEJBFactory.getService();
	}	
	
	@Test
	public void testGetMessage() {
		logger.info("DEBUG: Junit TESTING: testGetMessage ...");
		
//		String response = service.sayRest();
//		assertNotNull("Data Service failed!", response);
//		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void testGetProjects() {
		logger.info("DEBUG: Junit TESTING: testGetProjects ...");
		Collection<Project> Projects = service.toCollection();
		assertTrue("Fail to read Projects!", Projects.size() > 0);
	}

	@Test
	public void testAddProject() {
		logger.info("DEBUG: Junit TESTING: testAddProject ...");
		
		Integer projectsToAdd = 3;
		for (int i=1; i <= projectsToAdd; i++){
			service.add(new Project(i, "Project_" + (100 + i)));
		}
		Collection<Project> projects = service.toCollection();
		assertTrue("Fail to add Projects!", projects.size() == projectsToAdd);
	}

	@Test
	public void testDeleteProject() {
		logger.info("DEBUG: Junit TESTING: testDeleteProject ...");
		
		Collection<Project> projects = service.toCollection();
		for (Project p: projects)
			service.remove(p);
		Collection<Project> ProjectsAfterDelete = service.toCollection();
		assertTrue("Fail to read Projects!", ProjectsAfterDelete.size() == 0);
	}	
}
