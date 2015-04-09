package org.app.service.ejb.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;

import org.app.service.ejb.FeatureService;
import org.app.service.entities.Feature;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestFeatureServiceEJBPrm {
	private static Logger logger = Logger.getLogger(TestFeatureServiceEJBPrm.class.getName());
	
	@EJB
	private static FeatureService service;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		service = FeatureServiceEJBFactory.getService();
	}	
	
	/********************************************************/
	@Parameters
	public static Collection<Object[]> generateEntities(){
		Integer featuresToAdd = 3;
		List<Object[]> dataset = new ArrayList<Object[]>();
		for (int i=1; i <= featuresToAdd; i++){
			dataset.add(new Object[]{null, "Feature_" + (100 + i)});
		}
		return dataset;
	}
	private Feature featureToTest;
	public TestFeatureServiceEJBPrm(Integer fCode, String fName) {
		featureToTest = new Feature(fCode, fName);
	}
	@Test
	public void testParametrizedAddFeature() {
		logger.info("DEBUG: Junit TESTING: testParametrizedAddFeature ...");
		int beforeFeatures = service.getFeatures().size();
		service.addFeature(featureToTest);
		int afterFeatures = service.getFeatures().size();
		assertEquals("Failed to add feature!", afterFeatures, beforeFeatures + 1);
	}
}
