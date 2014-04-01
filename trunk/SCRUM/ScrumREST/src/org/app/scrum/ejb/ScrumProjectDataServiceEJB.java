package org.app.scrum.ejb;

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
import javax.ejb.Stateless;
import javax.inject.Inject;
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
// 1. Remote interface
@Stateless
@LocalBean
@Interceptors({SecurityInterceptor.class})
public class ScrumProjectDataServiceEJB 
	extends EntityRepositoryBase<Project> implements ScrumProjectDataService{
	
	private static Logger logger = Logger.getLogger(ScrumProjectDataServiceEJB.class.getName());
	
	// 2. Inject resource 
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
	
	
	//
	
	@Inject
    private CredentialBean creds;	
	
    // 3. Init with injected EntityManager
	private EntityRepository<Sprint> sprintRepository;
	
    @PostConstruct
	public void init(){
		sprintRepository = new EntityRepositoryBase<Sprint>(this.em, Sprint.class);
		logger.info("Initialized sprintRepository : " + sprintRepository.size());
		logger.info("Initialized teamRepository : " + teamRepository.size());	
		
		logger.info("Initialized TransactionSynchronizationRegistry : " + tsr);
		logger.info("Initialized UserTransaction : " + usrt);
		
		logger.info("Initialized creds : " + creds);
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
		logger.info("Initialized creds : " + creds);
		
		Map<String, Object> contextData = sctx.getContextData();
		for(String key: contextData.keySet()){
			logger.info("DEBUG contextData: " + key + " = " + contextData.get(key));
		}		
		
//		Properties properties = sctx.getEnvironment();
		
//		Enumeration e = properties.propertyNames();
//
//	    while (e.hasMoreElements()) {
//	      String key = (String) e.nextElement();
//	      logger.info("DEBUG properties: " + key + " -- " + properties.getProperty(key));
//	    }		
//		
		return m + " ... from remote ScrumProjectDataServiceEJB!";
	}

	@Override
	@GET @Path("/project")
	@Produces("application/json")
	public Project createNewProject(){
		
		Project project = ProjectBuilder.buildProiect(1001, "NEW Project", 3);
		debugCheckRelease(project);
		this.add(project);
//		this.refresh(project);
		project = getById(project.getProjectNo());
//		debugCheckRelease(project);
		// Project DTO: service getEntityDTO() or entity.getDTO()
//		project.setReleases(null);
		return project;
	}
	
	private void debugCheckRelease(Project p){
		logger.info("---------------------------------------------");
		for(Release r: p.getReleases()){
			logger.info("Check Releases: " + r.getProject());
		}
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
