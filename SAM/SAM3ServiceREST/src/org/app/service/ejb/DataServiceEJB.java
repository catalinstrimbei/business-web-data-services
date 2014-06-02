package org.app.service.ejb;

import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.EntityBase;
import org.app.service.entities.ProjectFactory;
import org.app.service.entities.Projects;
import org.app.service.entities.Releases;

/**
 * Session Bean implementation class ScrumTeamRepositoryService
 * Aggregate Repository Service Facade: Project - features - releases
 * @param <Sprint>
 */
@Path("service")
// 1. Remote interface
@Stateless
@LocalBean
public class DataServiceEJB<Sprint> 
	extends EntityRepositoryBase<Projects> 
	implements DataService, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3768641079515813837L;

	private static Logger logger = Logger.getLogger(DataServiceEJB.class.getName());
	
	// 2. Inject resource 
//	@PersistenceContext(unitName="ScrumEJB")
//	private EntityManager scrumEM;

    // 3. Init with injected EntityManager
	private EntityRepository<Releases> releaseRepository;
	
	@Inject
	private ProjectFactory projectFactory;
	
	@Inject
	private EntityRepository<Sprint> sprintRepository;
	
    @PostConstruct
	public void init(){
    	releaseRepository = new EntityRepositoryBase<Releases>(this.em, Releases.class);
    	sprintRepository.setEm(this.em);
		logger.info("Initialized releaseRepository : " + releaseRepository.size());	
		logger.info("Initialized sprintRepository : " + sprintRepository.size());
	}	

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Projects createNewProjects(Integer id){
    	Projects project = projectFactory.buildProject(id, "New Project", 3);
    	this.add(project);
    	return Projects.toDTOAggregate(project);
    }
    public String getMessage(){
    	return "DataService is working ..... ";
    }
    
    @Override
    public Projects getById(Object id){
    	Projects project = super.getById(id);
    	return Projects.toDTOAggregate(project);
    	}
    @Override
    public Collection<Projects> toCollection(){
    	return Projects.toDTOs(super.toCollection());
    	}
    @Override
    public Projects add(Projects project){
    	project = super.add(project);
    	return Projects.toDTOAggregate(project);
    	}
    
    @Override
    public boolean remove(Projects project){
    	return super.remove(project);
    	}
    
	public DataServiceEJB() {
		super();
		logger.info("INIT DEF CONSTRUCTOR ScrumProjectDataServiceEJB : " + this.em);		
	}

	
	/********************************************************************/
	@GET
	@Produces("text/html")
	public String sayRest() {
		return "Rest-EJB-SAM3...";
	}	
	/********************************************************************/

	public EntityRepository<Releases> getReleaseRepository() {
		return releaseRepository;
	}

	public void setReleaseRepository(EntityRepository<Releases> releaseRepository) {
		this.releaseRepository = releaseRepository;
	}
}

// http://localhost:8080/ServiceREST/service
