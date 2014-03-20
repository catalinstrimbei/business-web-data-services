package org.ejb.client.test;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.scrum.ejb.ScrumProjectDataService;
import org.app.scrum.ejb.ScrumProjectDataServiceEJB;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEJBServices {

	/* Resurse test*/
	private static Logger logger = Logger.getLogger(TestEJBServices.class.getName());

	private static ScrumProjectDataService serviceEJB;
	
	/* Set up */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		serviceEJB = lookupEJBService();
	}

	// Test simplu invocare EJB
	@Test
	public void testEJBService() throws Exception{
		logger.info("Begin test: ..." );
		
		logger.info(serviceEJB.sayMessage("Hello!"));
		
		logger.info("End test: ...");
	}
	
	/*--- Utils: Obtinere referinta serviciu EJB ----------------------------------------------------*/
	private static String serviceInterface = ScrumProjectDataService.class.getName();
	private static String serviceName = ScrumProjectDataServiceEJB.class.getSimpleName();
	private static String serviceModule = "ScrumEJB";	
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        logger.info(Context.URL_PKG_PREFIXES);
        final Context context = new InitialContext(jndiProperties);
        logger.info("LOOKUP FOR ejb:/" + serviceModule + "//" + serviceName + "!" + serviceInterface);
        return (T) context.lookup("ejb:/" + serviceModule + "//" + serviceName + "!" + serviceInterface);
    }	
	    
}
