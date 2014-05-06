package org.app.scrum.rest;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.project.Project;
import org.app.scrum.project.ProjectFactory;
import org.app.scrum.project.ProjectView;
import org.app.scrum.project.Release;
import org.app.scrum.services.ProjectSprintDataService;

@Path("projects") /* http://localhost:8080/ScrumREST/projects */
@Stateless @LocalBean 
public class ProjectSprintDataServiceRest extends EntityRepositoryBase<Project> 
		implements ProjectSprintDataService, Serializable{
	private static Logger logger = Logger.getLogger(ProjectSprintDataServiceRest.class.getName());
	
	private EntityRepository<Release> releaseRepository;
	
	@Inject private ProjectFactory projectFactory;
	
//	@Inject private EntityRepository<Sprint> sprintRepository; // !!! Bug on initialization
    
	@PostConstruct public void init(){
    	releaseRepository = new EntityRepositoryBase<Release>(this.em, Release.class);
//    	sprintRepository.setEm(this.em);
    	// check injected references
		logger.info("Initialized releaseRepository : " + releaseRepository.size());
//		logger.info("Initialized sprintRepository : " + sprintRepository.size());
	}		
    
	/* scrum/projects REST-resource: projects-collection repository*/
	
	
	/* scrum/projects/{id} REST-resource: project-entity*/
	
	@GET @Path("newproject/{id}") // @POST !? WebAPI principle break
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Project createNewProject(@PathParam("id") Integer id){
		Project project = projectFactory.buildProiect(id, "NEW Project", 3);
		this.add(project);
		return Project.toDTOAggregate(project);
	}
	
	@GET @Path("test") // Check if resource is up ...
	@Produces({ MediaType.TEXT_PLAIN})
	public String getMessage(){
		return "ProjectSprint DataService is working...";
	}	
	
	@GET @Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
//	@Override // Changed argument from Object to Integer so overridden is not correct anymore
	public Project getById(@PathParam("id") Integer id){ 
		Project project = super.getById(id);
		return Project.toDTOAggregate(project);
	}
	
	@GET /* RestAPI: /scrum/projects */
	@Override
	public Collection<Project> toCollection() {
		return Project.toDTOs(super.toCollection());
	}
	
	@POST @PUT 
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public Project add(Project project) {
		project = super.add(project);
		return Project.toDTOAggregate(project);
	}
	
	@DELETE 
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public boolean remove(Project project) {
		return super.remove(project); // !!!
	}
	
	// GET method on second repository for Release-type entities
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@GET @Path("releases/{releaseid}")
	// @Path("project/{projectid}/release/{releaseid}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Release getReleaseById(@PathParam("releaseid") Integer releaseid){
		logger.info("DEBUG: called getReleaseById() for projects >>>>>>>>>>>>>> simplified !");
		Release release = releaseRepository.getById(releaseid);
		return release.toDTO();
	}
	
	/* Other test-proposal methods ************************************************************/
	// Example of DTO as view-objects on JPA-entities 
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) // read only
	@GET @Path("views")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProjectView[] getProjectViews(){
		return ProjectView.getProjectViewList(this.toCollection());
	}
	
	// dummy XML marshall Rest: http://localhost:8080/ScrumREST/projects/projectdata
	@GET @Path("/projectdata")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getProjectData() throws Exception{
		Project dto = new Project(1111, "Pro 1111");
		JAXBContext jaxbContext = JAXBContext.newInstance(Project.class);
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		marshaller.marshal(dto, os);
		String aString = new String(os.toByteArray(),"UTF-8");
		
		Response response = Response
				.status(Status.OK)
				.type(MediaType.TEXT_PLAIN)
				.entity(aString)
				.build();
		
		return response;
	}	
}

/*
URL								HTTP Request	CRUD
-------------------------------------------------------------------------
/scrum/projects					GET			 	read project collection
/scrum/projects					POST			create (save) new project
-------------------------------------------------------------------------
/scrum/projects/{id}			GET				read existing project
/scrum/projects/{id}			PUT				update existing project
/scrum/projects/{id}			DELETE			delete existing project
-------------------------------------------------------------------------
/scrum/releases/{id}			GET				read existing release

*/