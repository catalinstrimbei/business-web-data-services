package org.app.test.rest.patterns;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.logging.Logger;

import org.app.service.entities.Produs;
import org.jboss.resteasy.util.GenericType;
import org.junit.Test;

public class TestRestProdusResource {

		private static Logger logger =Logger.getLogger(TestRestProdusResource.class.getName());
			
			@Test
			public void testGetProdus() throws Exception{
			RESTfullResource<Produs> resource = 
					new RESTfullResource<Produs>("http://localhost:8080/SPM1ServiceREST/produse/2",
							Produs.class, "application/xml");
			
			Produs produs = resource.get();
			assertNotNull("Resource [/produse/2] not returned from service!", produs);
			logger.info("DEBUG testGetProdus: queried produs" + produs);
			}
			
			
			@Test
			public void testGetProduse() throws Exception{
			RESTfullResource<Collection<Produs>> resource = 
					new RESTfullResource<Collection<Produs>>("http://localhost:8080/SPM1ServiceREST/produse/","application/xml",
							new GenericType<Collection<Produs>>(){});
			Collection<Produs> produse = resource.get();
			assertNotNull("Message not returned from service!", produse);
			for(Produs p: produse)
			logger.info("DEBUG testGetProjects: queried project" + produse);
			}
			
			@Test
			public void testPOSTNewProdusResource() throws Exception{
			RESTfullResource<Collection<Produs>> resource =new RESTfullResource<Collection<Produs>>("http://localhost:8080/SPM1ServiceREST/produse/","application/xml",new GenericType<Collection<Produs>>(){});
			Collection<Produs> produse = resource.get();
			logger.info("DEBUG testPOSTNewProdusResource: queried produse count: "+ produse.size());
			Produs produs = new Produs();
			produs.setIdProdus(3);
			produs.setDenProdus("NEW Produs");
			produs.setDenProdus(produs.getDenProdus() + "_REST");
			logger.info("DEBUG testPOSTNewProdusResource: " + produs);
			resource.post(produs);
			produse = resource.get();
			logger.info("DEBUG testPOSTNewProjectResource: queried projects count: "+ produse.size());
			}
			
			public void testDELETEProduseResource() throws Exception{
				RESTfullResource<Collection<Produs>> resourceProduse =
				new RESTfullResource<Collection<Produs>>(
				"http://localhost:8080/SPM1ServiceREST/produse/",
				"application/xml",
				new GenericType<Collection<Produs>>(){});
				
				Collection<Produs> produse = resourceProduse.get();
				logger.info("DEBUG testDELETEProduseResource: queried produse count: "+ produse.size());
				RESTfullResource<Produs> resource = new RESTfullResource<Produs>(
				"http://localhost:8080/ScrumREST/produse/325",
				Produs.class, "application/xml");
				Produs produs = resource.get();
				logger.info("DEBUG testDELETEProdusResource: " + produs);
				assertNotNull("DEBUG testDELETEProdusResource: Resource queried [/produse/3001] not returned from service!", produs);
				resourceProduse.delete(produs);
				produse = resourceProduse.get();
				logger.info("DEBUG testDELETEProduseResource: queried produse count: "
				+ produse.size());
				}
			
			@Test
			public void testPUTNewProdusResource() throws Exception{
			RESTfullResource<Produs> resource = new RESTfullResource<Produs>(
			"http://localhost:8080/SPM1ServiceREST/produse/3001",
			Produs.class, "application/xml");
			Produs produs = new Produs();
			produs.setIdProdus(3001);
			produs.setDenProdus("NEW Produs");
			produs.setDenProdus(produs.getDenProdus() + "_REST");
			logger.info("DEBUG testPUTNewProdusResource: " + produs);
			Produs result = resource.put(produs);
			logger.info("DEBUG testPUTNewProdusResource: " + result);
			assertNotNull("Resource created [/produse/3001] not returned from service!",
			result);
			}
			
			@Test
			public void testDELETEProdusResource() throws Exception{
			RESTfullResource<Produs> resource = new RESTfullResource<Produs>(
			"http://localhost:8080/SPM1ServiceREST/produse/3003",
			Produs.class, "application/xml");
			Produs produs = resource.get();
			logger.info("DEBUG testDELETEProjectResource: " + produs);
			assertNotNull("Resource queried [/projects/3001] not returned from service!",
			produs);
			resource.delete(null);
			}
}
