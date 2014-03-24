package org.app.scrum.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.app.patterns.EntityRepository;
import org.app.patterns.ProjectBuilder;
import org.app.scrum.Feature;
import org.app.scrum.Project;
import org.app.scrum.Release;

/**
 * Session Bean implementation class ScrumTeamRepositoryService
 * Aggregate Repository Service Facade: Project - features - releases
 */
// 1. Remote interface
@Stateless
@LocalBean
public class ScrumProjectDataServiceEJB extends EntityRepository<Project> implements ScrumProjectDataService{
	private static Logger logger = Logger.getLogger(ScrumProjectDataServiceEJB.class.getName());
	
	// 2. Inject resource
//	@PersistenceContext(unitName="ScrumEJB")
//	private EntityManager scrumEM;

    // 3. Init with injected EntityManager
	private EntityRepository<Release> releaseRepository;
	
    @PostConstruct
	public void init(){
		releaseRepository = new EntityRepository<Release>(this.em, Release.class);
		logger.info("Initialized releaseRepository : " + releaseRepository.size());		
	}	

	public ScrumProjectDataServiceEJB() {
		super();
		logger.info("INIT DEF CONSTRUCTOR ScrumProjectDataServiceEJB : " + this.em);		
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
	
	@Override
	public List<Release> getAllReleases(){
		List<Release> releases = new ArrayList<>();
		releases.addAll(releaseRepository.toCollection());
		return releases;
	}	
}
