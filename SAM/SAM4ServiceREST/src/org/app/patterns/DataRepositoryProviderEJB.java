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






import org.app.service.project.Proiect;
import org.app.service.sprint.Sprint;
import org.app.service.team.Member;

@Singleton
@LocalBean
public class DataRepositoryProviderEJB {
	private static Logger logger = Logger.getLogger(DataRepositoryProviderEJB.class.getName());
	
	@PersistenceContext(unitName="ScrumEJB")
	private EntityManager emForRepositoryBeans;
	
	@Produces @DataRepositoryBean(entityType=Proiect.class)
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
