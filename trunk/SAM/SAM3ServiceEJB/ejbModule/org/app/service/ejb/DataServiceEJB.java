package org.app.service.ejb;

import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.entities.BaseDomainEntity;
import org.app.patterns.EntityRepositoryBase;

@Stateless
@LocalBean
public class DataServiceEJB 
	extends EntityRepositoryBase<BaseDomainEntity> 
	implements DataService{
	
	private static Logger logger = Logger.getLogger(DataServiceEJB.class.getName());
	
	@Override
	public String sayMessage(String message) {
		logger.info("DEBUG: Received message: " + message);;
		return message + " (from JEE Container)";
	}

	/* Add Specific Data Service Methods*/
}