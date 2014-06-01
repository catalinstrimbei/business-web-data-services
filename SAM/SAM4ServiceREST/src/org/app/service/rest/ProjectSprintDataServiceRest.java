package org.app.service.rest;

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
import org.app.service.project.Activitate;
import org.app.service.project.Proiect;
import org.app.service.project.ProjectFactory;
import org.app.service.project.ProjectView;
import org.app.service.services.ProjectSprintDataService;

@Path("proiecte") /* http://localhost:8080/SAM2ServiceREST/Proiecte/ */
@Stateless @LocalBean 
public class ProjectSprintDataServiceRest extends EntityRepositoryBase<Proiect> 
		implements ProjectSprintDataService, Serializable{
	private static Logger logger = Logger.getLogger(ProjectSprintDataServiceRest.class.getName());
	
	private EntityRepository<Activitate> activitateRepository;
	
	@Inject private ProjectFactory projectFactory;
    
	@PostConstruct public void init(){
    	activitateRepository = new EntityRepositoryBase<Activitate>(this.em, Activitate.class);
		logger.info("Initialized activitateRepository : " + activitateRepository.size());
	}	
	
	@Override
	@GET 					/* scrum/projects 		REST-resource: projects-collection*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Collection<Proiect> toCollection() {
		return Proiect.toDTOs(super.toCollection());
	}	
	
	@POST 					/* scrum/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Collection<Proiect> addNew(Proiect proiect) {
		super.add(proiect);
		return Proiect.toDTOs(super.toCollection());
	}
	
	@PUT @Path("/{IdProiect}") 	/* scrum/projects/{id} 	REST-resource: project-entity*/	
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public Proiect add(Proiect proiect) {
		proiect = super.add(proiect);
		return Proiect.toDTOAggregate(proiect);
	}	
	
//	@Override // Changed argument from Object to Integer so overridden is not correct anymore
	
	
	@GET @Path("/{IdProiect}") 	/* scrum/projects/{id} 	REST-resource: project-entity*/
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	public Proiect getById(@PathParam("IdProiect") Integer IdProiect){ 
		Proiect proiect = super.getById(IdProiect);
		return Proiect.toDTOAggregate(proiect);
	}
	
	@Override
	@DELETE 				/* scrum/projects 		REST-resource: projects-collection*/
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public boolean remove(Proiect proiect) {
		logger.info("DEBUG: called REMOVE - proiect: " + proiect);
		return super.remove(proiect);
	}
	
	@DELETE @Path("/{IdProiect}") 	/* scrum/projects/{id} 	REST-resource: project-entity*/	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public void remove(@PathParam("IdProiect")Integer IdProiect) {
		logger.info("DEBUG: called REMOVE - ById() for projects >>>>>>>>>>>>>> simplified ! + id");
		Proiect proiect = super.getById(IdProiect);
		super.remove(proiect);
	}	
	
	// GET method on second repository for Release-type entities
	@GET @Path("/{IdProiect}/activitati/{IdActivitate}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Activitate getactivitateById(@PathParam("idactivitate") Integer Idactivitate){
		logger.info("DEBUG: called getactivitateById() for projects >>>>>>>>>>>>>> simplified !");
		Activitate activitate = activitateRepository.getById(Idactivitate);
		return activitate.toDTO();
	}
	
	/* Other test-proposal methods ************************************************************/
	@GET @Path("/new/{IdProiect}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Proiect createNewProject(@PathParam("IdProiect") Integer IdProiect){
		Proiect proiect = projectFactory.buildProiect(IdProiect, "NEW Project", 3);
		this.add(proiect);
		return Proiect.toDTOAggregate(proiect);
	}
	
	@GET @Path("/test") // Check if resource is up ...
	@Produces({ MediaType.TEXT_PLAIN})
	public String getMessage(){
		return "ProjectSprint DataService is working...";
	}
	
	// Example of DTO as view-objects on JPA-entities 
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) // read only
	@GET @Path("/views")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProjectView[] getProjectViews(){
		return ProjectView.getProjectViewList(this.toCollection());
	}
	
	// dummy XML marshall Rest: http://localhost:8080/ScrumREST/projects/projectdata
	@GET @Path("/projectdata")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getProjectData() throws Exception{
		Proiect dto = new Proiect(1111, "Pro 1111");
		JAXBContext jaxbContext = JAXBContext.newInstance(Proiect.class);
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

	@Override
	public Activitate getActivitateById(Integer IdActivitate) {
		// TODO Auto-generated method stub
		return null;
	}	
}

/*
---------------------------------------------------------------------------------------------------------------------------------
URL									HTTP Req.		CRUD									Mapping								|
---------------------------------------------------------------------------------------------------------------------------------
/scrum/projects						GET			 	read project collection					toCollection()						|
/scrum/projects						POST			add/save new project					add(Project)						|
/scrum/projects						DELETE			remove existing project					remove(Project)						|
---------------------------------------------------------------------------------------------------------------------------------
/scrum/projects/{id}				GET				read existing project					getById(Integer)					|
/scrum/projects/{id}				PUT				save new or existing project			add(Project)						|
/scrum/projects/{id}				DELETE			delete existing project					remove(Integer)						|
---------------------------------------------------------------------------------------------------------------------------------
/scrum/projects/{id}/releases/{id}	GET				read existing release					getReleaseById(Integer)				|
/scrum/projects/create/{id}			GET				Create new project aggregate			createNewProject(Integer)			|
---------------------------------------------------------------------------------------------------------------------------------
*/