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
	private static String MODULE_NAME = "ScrumREST";
	private static String SERVICE_NAME = ScrumProjectDataServiceEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME = ScrumProjectDataService.class.getName();
	
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");        
        final Context context = new InitialContext(jndiProperties);
        
        String lookUpURL =  "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
        
        logger.info("!DEBUG: lookUpURL =  " + lookUpURL + "\n");
        
        return (T) context.lookup(lookUpURL);
    }	
	    
}
