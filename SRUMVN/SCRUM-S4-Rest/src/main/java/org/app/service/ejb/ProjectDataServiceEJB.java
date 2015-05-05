package org.app.service.ejb;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.service.entities.Project;
import org.app.service.entities.Release;

@Path("projects") /* http://localhost:8080/SCRUM/data/projects */
@Stateless @LocalBean
public class ProjectDataServiceEJB 
		extends EntityRepositoryBase<Project>
		implements ProjectDataService {
	private static Logger logger = Logger.getLogger(ProjectDataServiceEJB.class.getName());
			
	@EJB // injected DataService
	private FeatureDataService featureDataService; 
	// Local component-entity-repository
	private EntityRepository<Release> releaseRepository;
	@PostConstruct
	public void init() {
		// local instantiation of local component-entity-repository
		releaseRepository = new EntityRepositoryBase<Release>(this.em,Release.class);
		logger.info("POSTCONSTRUCT-INIT releaseRepository: " + this.releaseRepository);
		logger.info("POSTCONSTRUCT-INIT featureDataService: " + this.featureDataService);
	}
	
	// Aggregate factory method
	public Project createNewProject(Integer id){
		// create project aggregate
		Project project = new Project(id, "NEW Project" + "." + id , new Date());
		List<Release> releasesProject = new ArrayList<>();
		Date dataPublicare = new Date();
		Long interval =  30l /*zile*/ * 24 /*ore*/ * 60 /*min*/ * 60 /*sec*/ * 1000 /*milisec*/;
		Integer releaseCount = 3;
		for (int i=0; i<=releaseCount-1; i++){
			releasesProject.add(new Release(null, "R: " + project.getProjectNo() + "." + i, 
					new Date(dataPublicare.getTime() + i * interval), project));
		}
		project.setReleases(releasesProject);		
		// save project aggregate
		this.add(project);
		// return project aggregate to service client
		return project;
	}
	
	public Release getReleaseById(Integer releaseid) {
		return releaseRepository.getById(releaseid);
	}

	public String getMessage() {
		return "ProjectSprint DataService is working...";
	}

	@Override
	@GET 					/* scrum/data/projects 		REST-resource: projects-collection*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
//	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Project> toCollection() {
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
		//return Project.toDTOs(super.toCollection());
	}	
	
	
	@GET @Path("/{id}") 	/* scrum/projects/data/{id} 	REST-resource: project-entity*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Project getById(@PathParam("id") Integer id){ 
		Project project = super.getById(id);
		logger.info("**** DEBUG REST getById(" + id +") = " + project);
		return project;
//		return Project.toDTOAggregate(project);
	}	
}