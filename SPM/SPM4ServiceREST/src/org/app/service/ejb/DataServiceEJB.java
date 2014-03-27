package org.app.service.ejb;

import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.EntityBase;

/**
 * Session Bean implementation class ScrumTeamRepositoryService
 * Aggregate Repository Service Facade: Project - features - releases
 */
@Path("service")
// 1. Remote interface
@Stateless
@LocalBean
public class DataServiceEJB extends EntityRepositoryBase<EntityBase> implements DataService{
	private static Logger logger = Logger.getLogger(DataServiceEJB.class.getName());
	
	// 2. Inject resource 
//	@PersistenceContext(unitName="ScrumEJB")
//	private EntityManager scrumEM;

    // 3. Init with injected EntityManager
	private EntityRepository<EntityBase> teamRepository;
	
    @PostConstruct
	public void init(){
		logger.info("Initialized :");		
	}	

	public DataServiceEJB() {
		super();
		logger.info("INIT DEF CONSTRUCTOR ScrumProjectDataServiceEJB : " + this.em);		
	}

	
	/********************************************************************/
	@GET
	@Produces("text/html")
	public String sayRest() {
		return "Rest-EJB-SPM4... ";
	}	
	/********************************************************************/
}

// http://localhost:8080/ServiceREST/service
