package org.app.spm2.ejb.test;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.service.ejb.ClientDataService;
import org.app.service.ejb.ClientDataServiceEJB;
import org.app.service.ejb.DataService;
import org.app.service.ejb.DataServiceEJB;

public class ClientDataServiceEJBFactory {
	private static Logger logger = Logger.getLogger(ClientDataServiceEJBFactory.class.getName());
	
	public static ClientDataService getScrumProjectRepositoryService() throws Exception{
		return lookupEJBService();
	}
	
	private static String MODULE_NAME = "SPM2ServiceREST";
	private static String SERVICE_NAME = ClientDataServiceEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME = ClientDataService.class.getName();
	
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");        
        final Context context = new InitialContext(jndiProperties);
        
        String lookUpURL =  "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
        
        logger.info("!DEBUG: lookUpURL =  " + lookUpURL + "\n");
        return (T) context.lookup(lookUpURL);
    }
}