package org.app.scrum.ejb;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.patterns.ProjectBuilder;
import org.app.scrum.Project;
import org.app.scrum.Release;
import org.app.scrum.team.Team;

/**
 * Session Bean implementation class ScrumTeamRepositoryService
 * Aggregate Repository Service Facade: Project - features - releases
 */
@Path("scrum")
// 1. Remote interface
@Stateless
@LocalBean
public class ScrumProjectDataServiceEJB extends EntityRepositoryBase<Project> implements ScrumProjectDataService{
	private static Logger logger = Logger.getLogger(ScrumProjectDataServiceEJB.class.getName());
	
	// 2. Inject resource 
//	@PersistenceContext(unitName="ScrumEJB")
//	private EntityManager scrumEM;

    // 3. Init with injected EntityManager
	private EntityRepository<Team> teamRepository;
	
    @PostConstruct
	public void init(){
		teamRepository = new EntityRepositoryBase<Team>(this.em, Team.class);
		logger.info("Initialized teamRepository : " + teamRepository.size());		
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
	public Project createNewProject(){
		Project project = ProjectBuilder.buildProiect(1001, "NEW Project", 3);
		debugCheckRelease(project);
		this.add(project);
		debugCheckRelease(project);
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
	
	
}

// http://localhost:8080/ScrumREST/resources/scrum/sayMessage
// http://localhost:8080/ScrumREST/scrum
