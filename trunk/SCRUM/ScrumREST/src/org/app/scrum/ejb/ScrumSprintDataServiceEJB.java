package org.app.scrum.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;

import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.sprint.Sprint;

/*
 * 1.Referencing
 * - Could be injected as named-bean 
 * Could be injected as ejb-local component by other ejb-local
 * 2.Usability
 * - as ejb: extends EntityRepositoryBase and inject entityManager as PersistenceContext
 * - as named-bean: could get entityManager in constructor by Producer
 */
@Stateless 
@LocalBean // No interface view
public class ScrumSprintDataServiceEJB
	extends EntityRepositoryBase<Sprint>
	implements ScrumSprintDataService
	{

	public ScrumSprintDataServiceEJB() {
		super();
	}

	public ScrumSprintDataServiceEJB(EntityManager em, Class<Sprint> t) {
		super(em, t);
	}
	
}
