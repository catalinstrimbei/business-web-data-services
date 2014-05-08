package org.app.test.rest.patterns;

import java.io.StringWriter;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.app.scrum.project.Project;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.junit.Test;


public class TestRestProjectSprintDataService {
	private static Logger logger = Logger.getLogger(TestRestProjectSprintDataService.class.getName());
	
//	@Test
	public void testGET() throws Exception{
		ClientRequest request = new ClientRequest("http://localhost:8080/ScrumREST/projects/project/1001");
		//Set the accept header to tell the accepted response format
	    request.accept("application/xml");
	     
	    //RESTEasy client automatically map response entity to target entity.
	    ClientResponse<Project> response = request.get(Project.class);
	     
	    //Check response status code
	    int apiResponseCode = response.getResponseStatus().getStatusCode();
	    if(response.getResponseStatus().getStatusCode() != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + apiResponseCode);
	    }
	     
	    //Get the project object from entity
	    Project project = response.getEntity();		

	    logger.info("DEBUG TEST: " + project);
	    
	    // second
	    response = request.get(Project.class);
	     
	    //Check response status code
	    apiResponseCode = response.getResponseStatus().getStatusCode();
	    if(response.getResponseStatus().getStatusCode() != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + apiResponseCode);
	    }	    
	    project = response.getEntity();		

	    logger.info("DEBUG TEST SECOND: " + project);	    
	}
	
	@Test
	public void testPOST() throws Exception{
		ClientRequest request = new ClientRequest("http://localhost:8080/ScrumREST/projects/project/1001");
		//Set the accept header to tell the accepted response format
	    request.accept("application/xml");
	     
	    //RESTEasy client automatically map response entity to target entity.
	    ClientResponse<Project> response = request.get(Project.class);
	     
	    //Check response status code
	    int apiResponseCode = response.getResponseStatus().getStatusCode();
	    if(response.getResponseStatus().getStatusCode() != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + apiResponseCode);
	    }
	     
	    //Get the project object from entity
	    Project project = response.getEntity();		
	    
	    logger.info("DEBUG TEST: " + project);
	    
	    // PUT (update) Logic
	    project.setName(project.getName() + "_REST");
	    
	    StringWriter writer = new StringWriter();
	    JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
	    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	    jaxbMarshaller.marshal(project, writer);
	     
	    //Define the API URI where API will be accessed
	    ClientRequest requestPut = new ClientRequest("http://localhost:8080/ScrumREST/projects/project/save");
	     
	    //Set the accept header to tell the accepted response format
	    requestPut.body("application/xml", writer.getBuffer().toString());
	     
	    //Send the request
	    ClientResponse<Project> responsePut = requestPut.put();
	    
	     
	    //First validate the api status code
	    int putResponseCode = response.getResponseStatus().getStatusCode();
	    if(response.getResponseStatus().getStatusCode() != 200)
	    {
	        throw new RuntimeException("Failed with HTTP error code : " + putResponseCode);
	    }
	    
	    project = response.getEntity();	
	    logger.info("DEBUG TEST PUT : " + project);
	}
	
}
