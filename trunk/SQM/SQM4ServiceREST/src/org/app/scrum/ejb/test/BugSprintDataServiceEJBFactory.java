package org.app.scrum.ejb.test;

import java.util.Hashtable;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.app.scrum.services.BugSprintDataService;
import org.app.scrum.services.BugSprintDataServiceEJB;
import org.app.service.ejb.DataServiceEJB;

import java.net.URL;


public class BugSprintDataServiceEJBFactory {

	private static Logger logger = Logger.getLogger(BugSprintDataServiceEJBFactory.class.getName());
	
	public static BugSprintDataService getScrumBugRepositoryService() throws Exception{
		return lookupEJBServices();
	}
	
	private static String MODULE_NAME = "ScrumREST";
	private static String SERVICE_NAME = BugSprintDataServiceEJB.class.getSimpleName();
	private static String REMOTE_INTERFACE_NAME = BugSprintDataService.class.getName();
	
	private static <T> T lookupEJBServices() throws Exception {
		//lookup ejb
		final Hashtable jndiProperties = new Hashtable();
		jndiProperties.put(Context.URL_PKG_PREFIXES,  "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		
		String lookUpURL = "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
		
		logger.info("!DEBUG: lookUpURL = " + lookUpURL +  "\n");
		return (T) context.lookup(lookUpURL);
	}
		
}
