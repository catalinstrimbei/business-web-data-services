package org.app.scrum.ejb;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.inject.Named;
import javax.interceptor.Interceptors;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.patterns.ProjectBuilder;
import org.app.scrum.project.Project;
import org.app.scrum.project.Release;
import org.app.scrum.rest.ApplicationConfigBean;
import org.app.scrum.rest.CredentialBean;
import org.app.scrum.sprint.Sprint;
import org.app.scrum.team.Team;

/**
 * Facade Service Implementation:
 * Session Bean implementation of ScrumProjectDataService facade interface:
 * - Main DAO: Project Aggregate Repository Service: Project - features - releases
 * - DAO Service Integration (local orchestration):
 * 		ProjectRepository [Project, Release, Feature]
 * 		SprintRepository [Sprint, Task]
 * - Prepare DTO for Project Aggregate Data Service
 * 		Project SimpleList(key,value)
 * 		Project SingleAggregate [Project-releases-features]
 * 		Release SingleAggregate[project-Release-features]
 * 		(Sprint SimpleList)
 * 		(Sprint SingleAggregate [Sprint-tasks])
 */
@Path("scrum")
@Stateless //@Stateful
@LocalBean
@Interceptors({SecurityInterceptor.class})
public class ScrumProjectDataServiceEJB 
	extends EntityRepositoryBase<Project> implements ScrumProjectDataService, Serializable{
	private static Logger logger = Logger.getLogger(ScrumProjectDataServiceEJB.class.getName());
	
	@EJB
	private ScrumTeamDataServiceEJB teamEntityRepository;
	
	@Inject @DataServiceBean
	private ScrumSprintDataService sprintEntityRepository;
	
	private EntityRepositoryBase<Release> releaseEntityRepository;
	
    @PostConstruct
	public void init(){
    	// direct init referenced releaseEntityRepository
    	releaseEntityRepository = new EntityRepositoryBase<Release>(this.em, Release.class);
    	// check references: directed and injected
		logger.info("Initialized releaseEntityRepository : " + releaseEntityRepository.size());
		logger.info("Initialized teamEntityRepository : " + teamEntityRepository.size());	
		logger.info("Initialized sprintEntityRepository : " + sprintEntityRepository.size());
	}	

	public ScrumProjectDataServiceEJB() {
		super();
		logger.info("INIT DEF CONSTRUCTOR ScrumProjectDataServiceEJB : " + this.em);		
	}

	@GET
	@Produces("text/html")
	public String sayRest() {
		
		return "Scrumming ";
	}
	
	@Override
	public String sayMessage(String m) {
		logger.info("DEBUG ... BREAKPOINT");
		return m + " ... from remote ScrumProjectDataServiceEJB!";
	}

	@Override
	@GET @Path("/project")
	@Produces("application/json")
	public Project createNewProject(){
		Project project = ProjectBuilder.buildProiect(1001, "NEW Project", 3);
		this.add(project);
//		this.refresh(project);
		project = getById(project.getProjectNo());
//		debugCheckRelease(project);
		// Project DTO: service getEntityDTO() or entity.getDTO()
//		project.setReleases(null);
		return project;
	}
	
	@Override
	public List<Release> getReleases(Project p){
		Project project = null;
		if (p.getProjectNo() != null){
			project = this.getById(p.getProjectNo());
			logger.info("Found project by ID: " + project);
		}else{
			List<Project> projects = new ArrayList<>(this.get(p));
			if (!projects.isEmpty()){
				project = projects.get(0);
				logger.info("Found project by sample: " + project);
			}
		}
		
		// Releases DTO
		for (Release r: project.getReleases()){
			r.setProject(null);
		}
		return ((project != null) ? project.getReleases() : null) ;
	}	
	
	@GET @Path("/project/{id}") @Produces("application/xml")
	public Project getByKey(@PathParam("id") Integer id) {
		logger.info("DEBUG: get project with id " + id);
		Project p = em.find(repositoryType, id);
		logger.info("DEBUG: found project " + p);
		return p;
	}		
	
}

// http://localhost:8080/ScrumREST/resources/scrum/sayMessage
// http://localhost:8080/ScrumREST/scrum

/*
// EJBe Resource Session Context Injection 
//	@PersistenceContext(unitName="ScrumEJB")
//	private EntityManager scrumEM;
	
	@EJB
	private ScrumTeamDataServiceEJB teamRepository;

	@Resource
	private SessionContext sctx;
	
	@Resource
	private TransactionSynchronizationRegistry tsr;   	
	
	@Resource
	private UserTransaction usrt;
	
	
	// Context Dependency Injection
	
	@Inject
    private CredentialBean creds;
	
	@Inject
    private ApplicationConfigBean cfgs;
	
	@Inject
	private ProjectBuilder projectBuilder;

	
    // 3. Init with injected EntityManager
	private EntityRepository<Sprint> sprintRepository;
*/
