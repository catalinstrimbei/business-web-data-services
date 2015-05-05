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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
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
	
	/******** REST MAPPING IMPLEMENTATION ************************************************/

	@Override
	@GET 					/* scrum/data/projects 		REST-resource: projects-collection*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Project> toCollection() {
		logger.info("**** DEBUG REST toCollection()");
		return super.toCollection();
	}	
	
	
	@GET @Path("/{id}") 	/* scrum/data/projects/data/{id} 	REST-resource: project-entity*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Project getById(@PathParam("id") Integer id){ 
		Project project = super.getById(id);
		logger.info("**** DEBUG REST getById(" + id +") = " + project);
		return project;
	}	
	
	@POST 					/* scrum/data/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<Project> addNew(Project project) {
		super.add(project);
		return Project.toDTOs(super.toCollection());
	}
	
	@PUT @Path("/{id}") 	/* scrum/data/projects/{id} 	REST-resource: project-entity*/	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public Project add(Project project) {
		project = super.add(project);
		return project;
		//return Project.toDTOAggregate(project);
	}	
	
	@Override
	@DELETE 				/* scrum/data/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public boolean remove(Project project) {
		logger.info("DEBUG: called REMOVE - project: " + project);
		return super.remove(project);
	}
	
	@DELETE @Path("/{id}") 	/* scrum/data/projects/{id} 	REST-resource: project-entity*/	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public void remove(@PathParam("id")Integer id) {
		logger.info("DEBUG: called REMOVE - ById() for projects >>>>>>>>>>>>>> simplified ! + id");
		Project project = super.getById(id);
		super.remove(project);
	}
	
	// GET method on second repository for Release-type entities
	@GET @Path("/{projectid}/releases/{releaseid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Release getReleaseById(@PathParam("releaseid") Integer releaseid){
		logger.info("DEBUG: called getReleaseById() for projects >>>>>>>>>>>>>> simplified !");
		Release release = releaseRepository.getById(releaseid);
		return release;
		// return release.toDTO();
	}
	
	/* Other test-proposal methods ************************************************************/
	@GET @Path("/new/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
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
	
	@GET @Path("/test") // Check if resource is up ...
	@Produces({ MediaType.TEXT_PLAIN})
	public String getMessage(){
		return "ProjectSprint DataService is working...";
	}	
}