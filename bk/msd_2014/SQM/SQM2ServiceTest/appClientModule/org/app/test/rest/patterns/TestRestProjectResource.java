package org.app.test.rest.patterns;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;

import org.app.service.entities.Task;
import org.app.test.rest.patterns.RESTfullResource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jboss.resteasy.util.GenericType;

public class TestRestProjectResource {
	
	// http://localhost:8080/SQM2ServiceREST/tasks/
	
	private static Logger logger =
			Logger.getLogger(TestRestProjectResource.class.getName());
			
//	        @Test
			public void testGetTask() throws Exception{
			RESTfullResource<Task> resource = new RESTfullResource<Task>(
			"http://localhost:8080/SQM2ServiceREST/tasks/1002",
			Task.class, "application/xml");
			Task task = resource.get();
			assertNotNull("Resource [/tasks/1002] not returned from service!", task);
			logger.info("DEBUG testGetProject: queried project" + task);
			}
	        
//	        @Test
	        public void testGetTasks() throws Exception{
	        RESTfullResource<Collection<Task>> resource =
	        new RESTfullResource<Collection<Task>>(
	        "http://localhost:8080/SQM2ServiceREST/tasks/",
	        "application/xml",
	        new GenericType<Collection<Task>>(){});
	        Collection<Task> tasks = resource.get();
	        assertNotNull("Message not returned from service!", tasks);
	        for(Task t: tasks)
	        logger.info("DEBUG testGetTasks: queried task" + t);
	        }
	        
//	        @Test
	        public void testPOSTNewTaskResource() throws Exception{
	        RESTfullResource<Collection<Task>> resource =
	        new RESTfullResource<Collection<Task>>(
	        "http://localhost:8080/SQM2ServiceREST/tasks/",
	        "application/xml",
	        new GenericType<Collection<Task>>(){});
	        Collection<Task> tasks = resource.get();
	        logger.info("DEBUG testPOSTNewTaskResource: queried tasks count: "
	        + tasks.size());
	        Task task = new Task();
	        task.setIdTask(3003);
	        task.setName("NEW Task");
	        task.setName(task.getName() + "_REST");
	        logger.info("DEBUG testPOSTNewTaskResource: " + task);
	        resource.post(task);
	        tasks = resource.get();
	        logger.info("DEBUG testPOSTNewTaskResource: queried tasks count: "
	        + tasks.size());
	        }
	        
//	        @Test
	        public void testDELETETasksResource() throws Exception{
	        	RESTfullResource<Collection<Task>> resourceProjects =
	        	new RESTfullResource<Collection<Task>>(
	        	"http://localhost:8080/SQM2ServiceREST/tasks/",
	        	"application/xml",
	        	new GenericType<Collection<Task>>(){});
	        	Collection<Task> tasks = resourceProjects.get();
	        	logger.info("DEBUG testDELETETasksResource: queried tasks count: "
	        	+ tasks.size());
	        	RESTfullResource<Task> resource = new RESTfullResource<Task>(
	        	"http://localhost:8080/SQM2ServiceREST/tasks/325",
	        	Task.class, "application/xml");
	        	Task task = resource.get();
	        	logger.info("DEBUG testDELETETasksResource: " + task);
	        	assertNotNull("DEBUG testDELETETasksResource: Resource queried[/tasks/3001] not returned from service!", task);
	        	resourceProjects.delete(task);
	        	tasks = resourceProjects.get();
	        	logger.info("DEBUG testDELETETasksResource: queried tasks count: "
	        	+ tasks.size());
	        	}
	        
	        @Test
	        public void testPUTNewTaskResource() throws Exception{
	        RESTfullResource<Task> resource = new RESTfullResource<Task>(
	        "http://localhost:8080/SQM2ServiceREST/tasks/3001",
	        Task.class, "application/xml");
	        Task task = new Task();
	        task.setIdTask(3003);
	        task.setName("NEW Task");
	        task.setName(task.getName() + "_REST");
	        logger.info("DEBUG testPUTNewTaskResource: " + task);
	        Task result = resource.put(task);
	        logger.info("DEBUG testPUTNewTaskResource: " + result);
	        assertNotNull("Resource created [/tasks/3001] not returned from service!",
	        result);
	        }
	        
//	        @Test
	        public void testDELETETaskResource() throws Exception{
	        RESTfullResource<Task> resource = new RESTfullResource<Task>(
	        "http://localhost:8080/SQM2ServiceREST/tasks/3003",
	        Task.class, "application/xml");
	        Task task = resource.get();
	        logger.info("DEBUG testDELETETaskResource: " + task);
	        assertNotNull("Resource queried [/tasks/3001] not returned from service!",
	        		task);
	        resource.delete(null);
	        }

}
