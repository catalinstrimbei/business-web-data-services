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
//@LocalBean
public class ScrumProjectRepositoryEJB extends EntityRepository<Project> implements ScrumProjectRepositoryService{

	// 2. Inject resource
	@PersistenceContext(unitName="ScrumEJB")
	private EntityManager scrumEM;

    // 3. Init with injected EntityManager
    @PostConstruct
	public void init(){
    	System.out.println(">>>>>>>>>>>>>>>>>>> INIT ScrumProjectRepositoryEJB >>>>>>>>>>>>>>>>>>>>>>");
		this.em = scrumEM;
		this.repositoryType = Project.class;
		genericSQL = "SELECT o FROM " + repositoryType.getName().substring(repositoryType.getName().lastIndexOf('.') + 1)
				+ " o";
//		logger.info("generic JPAQL: " + genericSQL);
		
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

	@Override
	public String sayMessage(String m) {
		// TODO Auto-generated method stub
		return m + " ... from remote EJB!";
	}

    
}
