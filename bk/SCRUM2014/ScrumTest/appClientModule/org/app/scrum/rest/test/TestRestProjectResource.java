package org.app.scrum.rest.test;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.app.scrum.project.Project;
import org.app.test.rest.patterns.RESTfullResource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jboss.resteasy.util.GenericType;


/* Test CRUD on Rest with POST, GET, PUT, DELETE requests
 * EJB-REST: ProjectSprintDataServiceRest
 * 
*/
public class TestRestProjectResource {
	private static Logger logger = Logger.getLogger(TestRestProjectResource.class.getName());
	@Test
	public void testGetMessage() throws Exception{
		RESTfullResource<String> resource = new RESTfullResource("http://localhost:8080/ScrumREST/projects/test");
		String message = resource.get();
		assertNotNull("Message not returned from service!", message);
		logger.info("DEBUG testGetMessage: " + message);		
	}
	
	@Test
	public void testGetProjects() throws Exception{
		RESTfullResource<Collection<Project>> resource = new RESTfullResource<Collection<Project>>(
				"http://localhost:8080/ScrumREST/projects/",
				"application/xml",
				new GenericType<Collection<Project>>(){});			
		
		Collection<Project> projects = resource.get();
		assertNotNull("Message not returned from service!", projects);
		for(Project p: projects)
			logger.info("DEBUG testGetProjects: queried project" + projects);
	}
	
//	@Test 
	public void testGetProject() throws Exception{
		RESTfullResource<Project> resource = new RESTfullResource<Project>(
				"http://localhost:8080/ScrumREST/projects/2", 
				Project.class, "application/xml");
		
		Project project = resource.get();
		assertNotNull("Resource [/projects/2] not returned from service!", project);
		logger.info("DEBUG testGetProject: queried project" + project);
	}
	
//	@Test
	public void testPUTUpdatedProjectResource() throws Exception{
		RESTfullResource<Project> resource = new RESTfullResource<Project>(
				"http://localhost:8080/ScrumREST/projects/2", 
				Project.class, "application/xml");
		Project project = resource.get();
		logger.info("DEBUG TEST RESOURCE GET: " +  project);
		assertNotNull("Resource queried [/projects/2] not returned from service!", project);
		project.setName(project.getName() + "_REST");
		Project result = resource.put(project);
		logger.info("DEBUG testPUTUpdatedProjectResource: " +  result);
		assertNotNull("Resource updated [/projects/2] not returned from service!", result);
	}
	
//	@Test
	public void testPUTNewProjectResource() throws Exception{
		RESTfullResource<Project> resource = new RESTfullResource<Project>(
				"http://localhost:8080/ScrumREST/projects/7001", 
				Project.class, "application/xml");
		
		Project project = new Project();
		project.setProjectNo(7001);
		project.setName("NEW Project");
		project.setName(project.getName() + "_REST");
		logger.info("DEBUG testPUTNewProjectResource: " +  project);
		
		Project result = resource.put(project);
		logger.info("DEBUG testPUTNewProjectResource: " +  result);
		assertNotNull("Resource created [/projects/7001] not returned from service!", result);
	}
	
//	@Test
	public void testPOSTNewProjectResource() throws Exception{
		RESTfullResource<Collection<Project>> resource = new RESTfullResource<Collection<Project>>(
				"http://localhost:8080/ScrumREST/projects/",
				"application/xml",
				new GenericType<Collection<Project>>(){});
		
		Collection<Project> projects = resource.get();
		logger.info("DEBUG testPOSTNewProjectResource: queried projects count: " + projects.size());	
		
		Project project = new Project();
		project.setProjectNo(3003);
		project.setName("NEW Project");
		project.setName(project.getName() + "_REST");
		logger.info("DEBUG testPOSTNewProjectResource: " +  project);
		resource.post(project);
		
		projects = resource.get();
		logger.info("DEBUG testPOSTNewProjectResource: queried projects count: " + projects.size());
	}	
	
//	@Test
	public void testDELETEProjectResource() throws Exception{
		RESTfullResource<Project> resource = new RESTfullResource<Project>(
				"http://localhost:8080/ScrumREST/projects/2", 
				Project.class, "application/xml");
		
		Project project = resource.get();
		logger.info("DEBUG testDELETEProjectResource: " +  project);
		assertNotNull("Resource queried [/projects/2] not returned from service!", project);
		
		resource.delete(null);
		
	}	

//	@Test
	public void testDELETEProjectsResource() throws Exception{
		RESTfullResource<Collection<Project>> resourceProjects = new RESTfullResource<Collection<Project>>(
				"http://localhost:8080/ScrumREST/projects/",
				"application/xml",
				new GenericType<Collection<Project>>(){});
		Collection<Project> projects = resourceProjects.get();
		logger.info("DEBUG testDELETEProjectsResource: queried projects count: " + projects.size());	
		RESTfullResource<Project> resource = new RESTfullResource<Project>(
				"http://localhost:8080/ScrumREST/projects/7001", 
				Project.class, "application/xml");
		Project project = resource.get();
		logger.info("DEBUG testDELETEProjectsResource: " +  project);
		assertNotNull("DEBUG testDELETEProjectsResource: Resource queried [/projects/7001] not returned from service!", project);
		resourceProjects.delete(project);
		projects = resourceProjects.get();
		logger.info("DEBUG testDELETEProjectsResource: queried projects count: " + projects.size());		
	}	
	
}