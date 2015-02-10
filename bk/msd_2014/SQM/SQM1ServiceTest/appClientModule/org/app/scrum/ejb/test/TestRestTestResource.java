package org.app.scrum.ejb.test;

import static org.junit.Assert.assertNotNull;

import java.util.Collection;
import java.util.logging.Logger;

import org.app.service.entities.Test1;
import org.app.test.rest.patterns.RESTfullResource;
import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;
import org.jboss.resteasy.util.GenericType;
import org.junit.Test;

public class TestRestTestResource {

	private static Logger logger = 
			Logger.getLogger(TestRestTestResource.class.getName());
	
	
			@Test 
			public void testGetTest1() throws Exception{
			RESTfullResource<Test1> resource = new RESTfullResource<Test1>(
			"http://localhost:8080/SQM1ServiceREST/teste/1002", 
			Test1.class, "application/xml");
			Test1 test1 = resource.get();
			assertNotNull("Resource [/teste1/1002] not returned from service!", test1);
			logger.info("DEBUG testGetTest1: queried test1" + test1);
}
			
			@Test
			public void testGET() throws Exception {
				ClientRequest request= new ClientRequest ("http://localhost:8080/SQM1ServiceREST/teste/test1/1001");
				request.accept("application/xml");
				ClientResponse<Test1> response= request.get(Test1.class);
				int apiResponseCode=response.getResponseStatus().getStatusCode();
				if(response.getResponseStatus().getStatusCode()!=200) {
					{
						throw new RuntimeException("Failed with HTTP error code :" + apiResponseCode);
					}
					/*Test1 test1 = response.getEntity();
					logger.info("DEBUG TEST:" + test1);
					response=request.get(Test1.class);
					apiResponseCode= response.getResponseStatus().getStatusCode();
					if(response.getResponseStatus().getStatusCode()!=200)
					{
						throw new RuntimeException("Failed with HTTP error code: " + apiResponseCode);
					}
						
						test1=response.getEntity();
						logger.info("DEBUG TEST SECOND:" + test1);
					*/	
				}
			}

					
				
				@Test
				public void Teste1() throws Exception{
				RESTfullResource<Collection<Test1>> resource = 
				new RESTfullResource<Collection<Test1>>("http://localhost:8080/SQM1ServiceREST/teste/","application/xml",
				new GenericType<Collection<Test1>>(){});
				Collection<Test1> teste1 = resource.get();
				assertNotNull("Message not returned from service!", teste1);
				for(Test1 t: teste1)
				logger.info("DEBUG testGetTeste1: queried test1" + teste1);
				}
				
				
				@Test
				public void testPOSTNewTest1Resource() throws Exception{
				RESTfullResource<Collection<Test1>> resource = 
				new RESTfullResource<Collection<Test1>>(
				"http://localhost:8080/SQM1ServiceREST/teste/",
				"application/xml",
				new GenericType<Collection<Test1>>(){});
				Collection<Test1> teste1 = resource.get();
				logger.info("DEBUG testPOSTTestResource: queried teste count: " 
				+ teste1.size());
				Test1 test1 = new Test1();
				test1.setIdTest(3003);
				test1.setNumeTest("NEW Test1");
				test1.setNumeTest(test1.getNumeTest() + "_REST");
				logger.info("DEBUG testPOSTNewTestResource: " + test1);
				resource.post(test1);
				teste1 = resource.get();
				logger.info("DEBUG testPOSTNewTestResource: queried teste count: " 
				+ teste1.size());
				}

				public void testDELETETeste1Resource() throws Exception{
					RESTfullResource<Collection<Test1>> resourceTeste1 = 
					new RESTfullResource<Collection<Test1>>(
					"http://localhost:8080/ScrumREST/teste1/",
					"application/xml",
					new GenericType<Collection<Test1>>(){});
					Collection<Test1> teste1 = resourceTeste1.get();
					logger.info("DEBUG testDELETETesteResource: queried teste count: " 
					+ teste1.size());
					RESTfullResource<Test1> resource = new RESTfullResource<Test1>(
					"http://localhost:8080/SQM1ServiceREST/teste//325", 
					Test1.class, "application/xml");
					Test1 test1 = resource.get();
					logger.info("DEBUG testDELETETesteResource: " + test1);
					assertNotNull("DEBUG testDELETETesteResource: Resource queried [/teste/3] not returned from service!", test1);
					resourceTeste1.delete(test1);
					test1 =  (Test1) resourceTeste1.get();
					logger.info("DEBUG testDELETETesteResource: queried teste count: " 
					+ teste1.size());

				}
				
				@Test
				public void testPUTNewTest1Resource() throws Exception{
				RESTfullResource<Test1> resource = new RESTfullResource<Test1>(
				"http://localhost:8080/SQM1ServiceREST/teste/3", 
				Test1.class, "application/xml");
				Test1 test1 = new Test1();
				test1.setIdTest(3001);
				test1.setNumeTest("NEW Test");
				test1.setNumeTest(test1.getNumeTest() + "_REST");
				logger.info("DEBUG testPUTNewProjectResource: " + test1);
				Test1 result = resource.put(test1);
				logger.info("DEBUG testPUTNewTestResource: " + result);
				assertNotNull("Resource created [/teste/3] not returned from service!", 
				result);
				}
				
				@Test
				public void testDELETETest1Resource() throws Exception{
				RESTfullResource<Test1> resource = new RESTfullResource<Test1>(
				"http://localhost:8080/SQM1ServiceREST/teste/3003", 
				Test1.class, "application/xml");
				Test1 test1 = resource.get();
				logger.info("DEBUG testDELETETestResource: " + test1);
				assertNotNull("Resource queried [/teste/3] not returned from service!",
				 test1);
				resource.delete(null);
				}
				
			}


			
