package org.app.spm2.ejb.test;
import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.app.service.ejb.ClientDataService;
import org.app.service.ejb.ClientDataServiceEJB;
import org.app.service.ejb.ContractDataService;
import org.app.service.ejb.ContractDataServiceEJB;

/**
 * @author Alina
 *
 */
public class ContractDataServiceEJBFactory {
private static Logger logger = Logger.getLogger(ContractDataServiceEJBFactory.class.getName());
	
	public static ContractDataService getScrumProjectRepositoryService() throws Exception{
		return lookupEJBService();
	}
	
	private static String MODULE_NAME = "SPM2ServiceREST";
	private static String SERVICE_NAME = ContractDataServiceEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME = ClientDataService.class.getName();
	
	
    private static <T> T lookupEJBService() throws NamingException {
        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.contract.naming");        
        final Context context = new InitialContext(jndiProperties);
        
        String lookUpURL =  "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
        
        logger.info("!DEBUG: lookUpURL =  " + lookUpURL + "\n");
        return (T) context.lookup(lookUpURL);
    }
}
