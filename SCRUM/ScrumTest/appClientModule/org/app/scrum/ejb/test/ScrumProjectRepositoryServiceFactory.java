package org.app.scrum.ejb.test;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.scrum.ejb.ScrumProjectRepositoryEJB;
import org.app.scrum.ejb.ScrumProjectRepositoryService;

public class ScrumProjectRepositoryServiceFactory {
	private static Logger logger = Logger.getLogger(ScrumProjectRepositoryServiceFactory.class.getName());
	
	public static ScrumProjectRepositoryService getScrumProjectRepositoryService() throws Exception{
		return lookupEJBService();
	}
	
	private static String MODULE_NAME = "ScrumEJB";
	private static String SERVICE_NAME = ScrumProjectRepositoryEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME = ScrumProjectRepositoryService.class.getName();
	
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");        
        final Context context = new InitialContext(jndiProperties);
        
        String lookUpURL =  "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
        
        logger.info("!DEBUG: lookUpURL =  " + lookUpURL + "\n");
        return (T) context.lookup(lookUpURL);
    }
}