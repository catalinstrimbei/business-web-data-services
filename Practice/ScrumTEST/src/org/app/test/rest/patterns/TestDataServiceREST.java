package org.app.test.rest.patterns;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;
 
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.BeforeClass;
import org.junit.Test;
 
public class TestDataServiceREST {
	private static Logger logger = Logger.getLogger(TestDataServiceREST.class.getName());
	private static String BASE_URL = "http://localhost:8080/ScrumREST";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}
	
	@Test
	public void test() {
		logger.info("DEBUG: Junit TESTING ...");
		String response = null;
		Client client = ClientBuilder.newClient();
		WebTarget serviceREST = client.target(BASE_URL).path("/service");
		try{
			response = serviceREST.request(MediaType.APPLICATION_JSON).get().toString();
		} catch (Exception e) {
			System.out.println("Exception : " + e.getMessage());
			e.printStackTrace();
		}
		assertNotNull("Data Service failed!", response);
		logger.info("DEBUG: EJB Response ..." + response);
	}	
}
/*
	Client client = ClientBuilder.newClient();
	WebTarget myResource = client.target(BASE_URL + "/overAge").path(anAge);
	
	StudentWrapper wrapper = null;
	try {
		
	  wrapper = myResource.request(MediaType.APPLICATION_JSON).get(StudentWrapper.class);
		
	} catch (Exception e) {
		System.out.println("Exception : " + e.getMessage());
		e.printStackTrace();
	}
*/