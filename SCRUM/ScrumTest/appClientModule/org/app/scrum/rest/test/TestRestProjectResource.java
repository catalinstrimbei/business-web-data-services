package org.app.scrum.rest.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Date;
import java.util.logging.Logger;

import org.app.scrum.project.Project;
import org.app.test.rest.patterns.RESTResource;
import org.app.test.rest.patterns.RESTfullResource;
import org.junit.BeforeClass;
import org.junit.Test;

/* Test CRUD on Rest with POST, GET, PUT, DELETE requests*/
public class TestRestProjectResource {
	private static Logger logger = Logger.getLogger(TestRestProjectResource.class.getName());
	
	static RESTfullResource<Project> projectResource;
	static RESTfullResource projectsResource;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		projectsResource = new RESTResource<Project>(
				"http://localhost:8080/ScrumREST/projects/", 
				Project.class, "application/xml", 
				"/", 			//GETpath
				"/",			//POSTpath 
				null, 			//PUTpath 
				null); 			//DELETEpath
		
		projectResource = new RESTResource<Project>(
				"http://localhost:8080/ScrumREST/projects/", 
				Project.class, "application/xml", 
				"/1002", 		//GETpath
				"/",			//POSTpath 
				"/", 			//PUTpath 
				"/"); 			//DELETEpath	
	}
	
	/* CHECK EJB live test*/
	@Test
	public void testGetMessage() throws Exception{
		String message = (String) projectsResource.get("test");
		assertNotNull("Message not returned from service!", message);
		logger.info("DEBUG testGetMessage: " + message);		
	}
	
	/* CREATE Test 1 (create-read)*/
//	@Test
	public void testCreateProject() throws Exception{
		Project project = new Project(7001, "Project Test 7001", new Date());
		project = projectResource.post(project); // HTTP
		logger.info("DEBUG createProject: saved project: " + project);
		assertNotNull("Fail to create new project in repository!", project);
		project = projectResource.get(); // HTTP
		assertNotNull("Fail to find new project in repository!", project);
		logger.info("DEBUG createProject: queried project" + project);		
	}
	
	
	
	/* REMOVE Test */
	@Test
	public void testRemove() throws Exception{
//		Integer projectNo = 7001;
//		Project project = projectResource.get(projectNo);
//		assertNotNull("Fail to get project from repository!", project);
//		service.remove(project);
//		// check read
//		project = service.getById(projectNo);
//		logger.info("DEBUG testAdd: project removed: " + project);
//		assertNull("Fail to remove project in repository!", project);
	}	
	
	/****************************************************************/
//	@Test
	public void testGETProjectResource() throws Exception{
		Project project = projectResource.get();
		logger.info("DEBUG TEST RESOURCE: " + project);
		
	}
	
//	@Test
	public void testPUTProjectResource() throws Exception{
		Project project = projectResource.get();
		project.setName(project.getName() + "_REST");
		logger.info("DEBUG TEST RESOURCE GET: " +  project);
		
		Object result = projectResource.put(project);
		logger.info("DEBUG TEST RESOURCE PUT: " +  result);
	}
	
//	@Test
	public void testPUTNewProjectResource() throws Exception{
		Project project = new Project();
		project.setName("NEW Project");
		project.setName(project.getName() + "_REST");
		logger.info("DEBUG TEST RESOURCE GET: " +  project);
		
		Object result = projectResource.put(project);
		logger.info("DEBUG TEST RESOURCE PUT: " +  result);
	}
	

}
