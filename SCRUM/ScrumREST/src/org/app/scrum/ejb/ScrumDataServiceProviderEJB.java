package org.app.scrum.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.sprint.Sprint;
import org.app.scrum.team.Member;

@Singleton
@LocalBean
public class ScrumDataServiceProviderEJB {
	
	@PersistenceContext(unitName="ScrumEJB")
	private EntityManager emForRepositoryBeans;
	
	@Produces
	public ScrumSprintDataService getSprintEntityRepository(){
		return new ScrumSprintDataServiceEJB(emForRepositoryBeans, Sprint.class);
	}
	
//	@Produces
//	private EntityRepository<Member> getMemberEntityRepository(){
//		return new EntityRepositoryBase<Member>(emForRepositoryBeans, Member.class);
//	}	
}
