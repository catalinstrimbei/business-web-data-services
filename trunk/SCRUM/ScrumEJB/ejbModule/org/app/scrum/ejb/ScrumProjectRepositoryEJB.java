package org.app.scrum.ejb;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.app.patterns.EntityRepository;
import org.app.scrum.Project;

/**
 * Session Bean implementation class ScrumTeamRepositoryService
 */
// 1. Remote interface
@Stateless
@LocalBean
@Remote(ScrumProjectRepositoryService.class)
public class ScrumProjectRepositoryEJB extends EntityRepository<Project> implements ScrumProjectRepositoryService{

	// 2. Inject resource
	@PersistenceContext(unitName="ScrumEJB")
	private EntityManager scrumEM;

    // 3. Init with injected EntityManager
    @PostConstruct
	public void init(){
		this.em = scrumEM;
	}	
	
    /**
     * @see EntityRepository#EntityRepository(EntityManager, Class<T>)
     */
    public ScrumProjectRepositoryEJB(EntityManager em, Class<Project> t) {
        super(em, t);
    }

	public ScrumProjectRepositoryEJB() {
		super();
	}

    
}
