package org.ejb.client.test;

import java.net.URL;
import java.net.URLConnection;
import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.scrum.services.ProjectSprintDataService;
import org.app.scrum.services.ProjectSprintDataServiceEJB;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEJBServices {

	/* Resurse test*/
	private static Logger logger = Logger.getLogger(TestEJBServices.class.getName());

	private static ProjectSprintDataService serviceEJB;
	
	/* Set up */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		serviceEJB = lookupEJBService();
		URL url = new URL("http://localhost:8080/ScrumREST/projects/check");
		URLConnection conn = url.openConnection();
		conn.getInputStream();
	}

	// Test simplu invocare EJB
	@Test
	public void testEJBService() throws Exception{
		logger.info("Begin test: ..." );
		
		logger.info(serviceEJB.getMessage());
		
		logger.info("End test: ...");
	}
	
	/*--- Utils: Obtinere referinta serviciu EJB ----------------------------------------------------*/
	private static String MODULE_NAME = "ScrumREST";
	private static String SERVICE_NAME = ProjectSprintDataServiceEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME = ProjectSprintDataService.class.getName();
	
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");        
        final Context context = new InitialContext(jndiProperties);
        String lookUpURL =  "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
        logger.info("!DEBUG: lookUpURL =  " + lookUpURL + "\n");
        return (T) context.lookup(lookUpURL);
    }	
	    
}
