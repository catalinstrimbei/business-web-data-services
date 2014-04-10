package org.app.patterns;

import java.lang.annotation.Annotation;
import java.util.logging.Logger;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.scrum.ejb.DataServiceBean;
import org.app.scrum.ejb.ScrumSprintDataService;
import org.app.scrum.ejb.ScrumSprintDataServiceEJB;
import org.app.scrum.project.Project;
import org.app.scrum.sprint.Sprint;
import org.app.scrum.team.Member;

@Singleton
@LocalBean
public class DataRepositoryProviderEJB {
	private static Logger logger = Logger.getLogger(DataRepositoryProviderEJB.class.getName());
	
	@PersistenceContext(unitName="ScrumEJB")
	private EntityManager emForRepositoryBeans;
	
	@Produces @DataServiceBean
	public ScrumSprintDataService getSprintEntityRepository(){
		logger.info("DEBUG: ScrumDataServiceProviderEJB produces " + "DataServiceBeans");
		return new ScrumSprintDataServiceEJB(emForRepositoryBeans, Sprint.class);
	}
	
	@Produces @DataRepositoryBean(entityType=Project.class)
	public EntityRepository getEntityRepository(InjectionPoint injectionPoint) throws Exception{
		Class entityType = null;
		for (Annotation qualifier:injectionPoint.getQualifiers()) {
			if (qualifier instanceof DataRepositoryBean) {
				entityType = ((DataRepositoryBean)qualifier).entityType();
			}
		}
		if (entityType == null)
			throw new Exception("Invalid entity type: null");
		
		return new EntityRepositoryBase(emForRepositoryBeans, entityType);
	}	
}
