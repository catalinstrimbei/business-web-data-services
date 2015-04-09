package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.FeatureService;
import org.app.service.entities.Feature;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestFeatureServiceEJB {
	private static Logger logger = Logger.getLogger(TestFeatureServiceEJB.class.getName());
	
	@EJB
	private static FeatureService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = FeatureServiceEJBFactory.getService();
	}	
	
	@Test
	public void testSayRest() {
		logger.info("DEBUG: Junit TESTING: testSayRest ...");
		
//		String response = service.sayRest();
//		assertNotNull("Data Service failed!", response);
//		logger.info("DEBUG: EJB Response ..." + response);
	}

	@Test
	public void testGetFeatures() {
		logger.info("DEBUG: Junit TESTING: testGetFeatures ...");
		
		Collection<Feature> features = service.getFeatures();
		assertTrue("Fail to read features!", features.size() > 0);
	}

	@Test
	public void testAddFeature() {
		logger.info("DEBUG: Junit TESTING: testAddFeature ...");
		
		Integer featuresToAdd = 3;
		for (int i=1; i <= featuresToAdd; i++){
			//service.addFeature(new Feature(100 + i, "Feature_" + (100 + i)));
			service.addFeature(new Feature(null, "Feature_" + (100 + i)));
		}
		Collection<Feature> features = service.getFeatures();
		assertTrue("Fail to add features!", features.size() == featuresToAdd);
	}

	@Test
	public void tesDeleteFeature() {
		logger.info("DEBUG: Junit TESTING: testDeleteFeature ...");
		
		Collection<Feature> features = service.getFeatures();
		for (Feature f: features)
			service.removeFeature(f);
		Collection<Feature> featuresAfterDelete = service.getFeatures();
		assertTrue("Fail to read features!", featuresAfterDelete.size() == 0);
	}	
}
