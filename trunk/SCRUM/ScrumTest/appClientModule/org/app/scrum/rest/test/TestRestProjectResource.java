package org.app.scrum.rest.test;

import java.util.logging.Logger;

import org.app.scrum.project.Project;
import org.app.test.rest.patterns.RESTResource;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestRestProjectResource {
	private static Logger logger = Logger.getLogger(TestRestProjectResource.class.getName());
	
	static RESTResource<Project> projectResource;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		projectResource = new RESTResource<Project>(
				"http://localhost:8080/ScrumREST/projects/", 
				Project.class, "application/xml", 
				"project/1002", //GETpath
				null,// POSTpath 
				"project/save", //PUTpath 
				null); //DELETEpath		
	}
	
	@Test
	public void testGETProjectResource() throws Exception{
		Project project = projectResource.get();
		logger.info("DEBUG TEST RESOURCE: " + project);
		
	}
	
	@Test
	public void testPUTProjectResource() throws Exception{
		Project project = projectResource.get();
		project.setName(project.getName() + "_REST");
		logger.info("DEBUG TEST RESOURCE GET: " +  project);
		
		Object result = projectResource.put(project);
		logger.info("DEBUG TEST RESOURCE PUT: " +  result);
		
	}

}
