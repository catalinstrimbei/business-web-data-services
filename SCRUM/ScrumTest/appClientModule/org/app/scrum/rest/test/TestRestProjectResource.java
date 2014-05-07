package org.app.scrum.rest.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Collection;
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
	
//	static RESTfullResource<Project> projectResource;
//	static RESTfullResource<Project> projectsResource;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
//		projectsResource = new RESTfullResource<Project>(
//				"http://localhost:8080/ScrumREST/projects/", 
//				Project.class, "application/xml");
//		
//		projectResource = new RESTfullResource<Project>(
//				"http://localhost:8080/ScrumREST/projects/1002", 
//				Project.class, "application/xml");
	}
	
	/* CHECK Rest live test*/
	@Test
	public void testGetMessage() throws Exception{
		String message = (String) new RESTfullResource("http://localhost:8080/ScrumREST/projects/test").get();
		assertNotNull("Message not returned from service!", message);
		logger.info("DEBUG testGetMessage: " + message);		
	}
	
//	@Test ?
	public void testGetProjects() throws Exception{
		RESTfullResource<Collection<Project>> resource = new RESTfullResource<Collection<Project>>(
				"http://localhost:8080/ScrumREST/projects/");
		
		Collection<Project> projects = resource.get();
		assertNotNull("Message not returned from service!", projects);
	}
	
//	@Test 
	public void testGetProject() throws Exception{
		RESTfullResource<Project> resource = new RESTfullResource<Project>(
				"http://localhost:8080/ScrumREST/projects/1002", 
				Project.class, "application/xml");
		
		Project project = resource.get();
		assertNotNull("Resource [/projects/1002] not returned from service!", project);
		logger.info("DEBUG createProject: queried project" + project);
	}
	
//	@Test
	public void testPUTUpdatedProjectResource() throws Exception{
		RESTfullResource<Project> resource = new RESTfullResource<Project>(
				"http://localhost:8080/ScrumREST/projects/1002", 
				Project.class, "application/xml");
		
		Project project = resource.get();
		logger.info("DEBUG TEST RESOURCE GET: " +  project);
		assertNotNull("Resource queried [/projects/1002] not returned from service!", project);
		project.setName(project.getName() + "_REST");
		
		Project result = resource.put(project);
		logger.info("DEBUG TEST RESOURCE PUT: " +  result);
		assertNotNull("Resource updated [/projects/1002] not returned from service!", result);
	}
	
//	@Test
	public void testPUTNewProjectResource() throws Exception{
		RESTfullResource<Project> resource = new RESTfullResource<Project>(
				"http://localhost:8080/ScrumREST/projects/3001", 
				Project.class, "application/xml");
		
		Project project = new Project();
		project.setProjectNo(3001);
		project.setName("NEW Project");
		project.setName(project.getName() + "_REST");
		logger.info("DEBUG TEST RESOURCE GET: " +  project);
		
		Project result = resource.put(project);
		logger.info("DEBUG TEST RESOURCE PUT: " +  result);
		assertNotNull("Resource created [/projects/3001] not returned from service!", result);
	}
	
//	@Test ?
	public void testPOSTNewProjectResource() throws Exception{
		RESTfullResource<Collection<Project>> resource = new RESTfullResource<Collection<Project>>(
				"http://localhost:8080/ScrumREST/projects/");
		
		Project project = new Project();
		project.setProjectNo(3001);
		project.setName("NEW Project");
		project.setName(project.getName() + "_REST");
		logger.info("DEBUG TEST RESOURCE GET: " +  project);
		
//		resource.post(project);
	}	
	
	@Test
	public void testDELETEProjectResource() throws Exception{
		RESTfullResource<Project> resource = new RESTfullResource<Project>(
				"http://localhost:8080/ScrumREST/projects/3001", 
				Project.class, "application/xml");
		
		Project project = resource.get();
		logger.info("DEBUG TEST RESOURCE GET: " +  project);
		assertNotNull("Resource queried [/projects/3001] not returned from service!", project);
		
		resource.delete(null);
		
	}	

}
