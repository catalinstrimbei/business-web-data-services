package org.app.service.ejb.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.FeatureService;
import org.app.service.ejb.FeatureServiceEJB;
import org.app.service.entities.Feature;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class TestFeatureServiceEJBArq {
	private static Logger logger = Logger.getLogger(TestFeatureServiceEJBArq.class.getName());
	// Arquilian infrastructure
	@EJB
	private static FeatureService service;
	
	@Deployment
	public static Archive<?> createDeployment() {
	        return ShrinkWrap
	                .create(WebArchive.class, "SCRUM-S2-test.war")
	                .addClass(Feature.class)
	                .addClass(FeatureService.class)
	                .addClass(FeatureServiceEJB.class)
	                .addAsResource("META-INF/persistence.xml")
	                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
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
/* http://localhost:8080/SCRUM-S2/data/features */