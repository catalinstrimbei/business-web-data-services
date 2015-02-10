package org.app.sqm3.ejb.test;


import java.util.logging.Logger;
import java.net.URL;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.app.service.ejb.ComplaintsDataService;
import org.app.service.ejb.ComplaintsDataServiceEJB;

public class ComplaintsDataServicesEJBFactory {
	private  static Logger LOGGER = Logger.getLogger(ComplaintsDataServicesEJBFactory.class.getName());
	
	public static ComplaintsDataService  getComplaintsRepositoryService() throws Exception{
		return LookupEJBService();
	}
	
	private static String MODULE_NAME = "SQM3ServiceREST";
	private static String SERVICE_NAME = ComplaintsDataServiceEJB.class.getSimpleName();
	private static String  REMOTE_INTERFACE_NAME = ComplaintsDataService.class.getName();
	
	
	private static <T> T LookupEJBService() throws Exception{
		// TODO Auto-generated method stub
		final Hashtable jndiProperties = new Hashtable();
		
		jndiProperties.put("jboss.naming.client.ejb.context", true);
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		String lookUpURL = "ejb:/" + MODULE_NAME + "//" + SERVICE_NAME + "!" + REMOTE_INTERFACE_NAME;
		LOGGER.info("!DEBUG: lookUpURL = " + lookUpURL + "\n");
		return (T) context.lookup(lookUpURL);
	}
}
