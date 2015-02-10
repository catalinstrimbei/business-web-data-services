package org.app.spm5.ejb.test;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.service.ejb.ProduseDataService;
import org.app.service.ejb.ProduseDataServiceEJB;

public class ProduseDataServiceEJBFactory {
	private static Logger logger = Logger.getLogger(ProduseDataServiceEJBFactory.class.getName());
	
	public static ProduseDataService getScrumProjectRepositoryService() throws Exception{
		return lookupEJBService();
	}
	
	private static String MODULE_NAME = "SPM5ServiceREST";
	private static String SERVICE_NAME = ProduseDataServiceEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME = ProduseDataService.class.getName();
	
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");        
        final Context context = new InitialContext(jndiProperties);
        
        String lookUpURL =  "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
        
        logger.info("!DEBUG: lookUpURL =  " + lookUpURL + "\n");
        return (T) context.lookup(lookUpURL);
    }
}