package org.app.scrum.ejb.test;

import java.util.Hashtable;
import java.net.URL; 
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.service.ejb.DataService;
import org.app.service.ejb.DataServiceEJB;

public class ProdusDataServiceEJBFactory {
	private static Logger logger = Logger.getLogger(ProdusDataServiceEJBFactory.class.getName());
	
	public static DataService getScrumProjectRepositoryService() throws Exception{
		return lookupEJBService();
	}
	
	private static String MODULE_NAME = "ScrumREST";
	private static String SERVICE_NAME = ProdusSprintDataServiceEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME = ProdusSprintDataService.class.getName();
	
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");        
        final Context context = new InitialContext(jndiProperties);
        
        String lookUpURL =  "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
        
        logger.info("!DEBUG: lookUpURL =  " + lookUpURL + "\n");
        return (T) context.lookup(lookUpURL);
    }
}