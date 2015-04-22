package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.logging.Logger;

import org.app.service.ejb.FeatureDataService;
import org.app.service.entities.Feature;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestFeatureDataServiceEJB {
	private static Logger logger = Logger.getLogger(TestFeatureDataServiceEJB.class.getName());
	
	private static FeatureDataService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = FeatureDataServiceEJBFactory.getService();
	}	

	@Test
	public void test0GetMessage() {
		logger.info("DEBUG: Junit TESTING: testGetMessage ...");
		
//		String response = service.sayRest();
//		assertNotNull("Data Service failed!", response);
//		logger.info("DEBUG: EJB Response ..." + response);
	}
	
	@Test
	public void test1DeleteFeature() {
		logger.info("DEBUG: Junit TESTING: testDeleteFeature ...");
		
		Collection<Feature> features = service.getFeatures();
		for (Feature f: features)
			service.removeFeature(f);
		Collection<Feature> featuresAfterDelete = service.getFeatures();
		assertTrue("Fail to read features!", featuresAfterDelete.size() == 0);
	}	
	
	@Test
	public void test2AddFeature() {
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
	public void test3GetFeatures() {
		logger.info("DEBUG: Junit TESTING: testGetFeatures ...");
		Collection<Feature> features = service.getFeatures();
		assertTrue("Fail to read features!", features.size() > 0);
	}	
}
