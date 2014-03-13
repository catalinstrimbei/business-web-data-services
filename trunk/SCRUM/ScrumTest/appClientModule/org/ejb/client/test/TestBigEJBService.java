package org.ejb.client.test;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.scrum.ejb.ScrumProjectRepositoryService;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestBigEJBService {

	/* Resurse test*/
	private static Logger logger = Logger.getLogger(TestBigEJBService.class.getName());

	private static ScrumProjectRepositoryService serviceBigEJB;
	
	/* Set up */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		serviceBigEJB = lookupBigEJBService();
	}

	// Test simplu invocare
	@Test
	public void testBigEJBServiceInterceptor() throws Exception{
		logger.info("Begin test: ..." );
		
		logger.info(serviceBigEJB.sayMessage("Hello!"));
		
		logger.info("End test: ...");
	}	
	
	/*--- Utils: Obtinere referinta serviciu EJB ----------------------------------------------------*/
    private static <T> T lookupBigEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        logger.info(Context.URL_PKG_PREFIXES);
        final Context context = new InitialContext(jndiProperties);
        String lookUpName = "ejb:/ScrumEJB/ScrumProjectRepositoryEJB!org.app.scrum.ejb.ScrumProjectRepositoryService";
        logger.info("LOOKUP FOR " + lookUpName);
        return (T) context.lookup(lookUpName);
    }	    
}
