package org.app.scrum.services;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.app.patterns.DataRepositoryBean;
import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.project.Project;
import org.app.scrum.project.ProjectFactory;
import org.app.scrum.project.Release;
import org.app.scrum.rest.CredentialBean;
import org.app.scrum.sprint.Sprint;

@Path("projects") /* http://localhost:8080/ScrumREST/projects */
@Stateless @LocalBean 
@Interceptors({SecurityInterceptor.class})
public class ProjectSprintDataServiceEJB extends EntityRepositoryBase<Project> 
	implements ProjectSprintDataService, Serializable{
	private static Logger logger = Logger.getLogger(ProjectSprintDataServiceEJB.class.getName());
	
	@EJB
	private TeamDataServiceEJB teamDataService;
	
	@Inject @DataRepositoryBean(entityType=Sprint.class)
	private EntityRepository<Sprint> sprintRepository;
	
	
//	@Inject @DataRepositoryBean(entityType=Release.class)
//	private EntityRepository<Release> releaseRepository;	
	/* sau fara injectie: instantiere locala ... !*/
	 private EntityRepository<Release> releaseRepository;
	
	@Inject
	private ProjectFactory projectFactory;
	
	@Inject CredentialBean credentialBean;
	
    @PostConstruct
	public void init(){
    	releaseRepository = new EntityRepositoryBase<Release>(this.em, Release.class);
    	// check injected references
		logger.info("Initialized releaseRepository : " + releaseRepository.size());
		logger.info("Initialized teamDataService : " + teamDataService.size());	
		logger.info("Initialized sprintRepository : " + sprintRepository.size());
		logger.info("Initialized credentialBean : " + credentialBean);
	}		
    
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) // read only
	@GET 
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Project[] getProjectList(){
		return getProjectDTOList();
	}	
	
	@Interceptors({ValidatorInterceptor.class})
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@GET @Path("project/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Project getProjectById(@PathParam("id") Integer id){
		Project project = super.getById(id);
		return getProjectDTOAggregate(project);
	}	
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@GET @Path("project/{projectid}/release/{releaseid}") 
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Release getReleaseById(
			@PathParam("projectid") Integer projectid,
			@PathParam("releaseid") Integer releaseid){
		Release release = releaseRepository.getById(releaseid);
		return release.newReleaseDTO();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) // read only
	@GET @Path("views")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ProjectView[] getProjectViews(){
		return getProjectViewList();
	}
	
	/* EJB calls*/
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@PUT @Path("project/save") 
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Project addProject(Project project){
		// restore project
		// merge projectDTO with project
		logger.info(">>>>> DEBUG: saving project dto" + project);
		// save project
		project = this.add(project);
		project =  getProjectDTOAggregate(project);
		logger.info(">>>>> DEBUG: project saved" + project);
		return project;
	}
	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Project createNewProject(Integer id){
		Project project = projectFactory.buildProiect(id, "NEW Project", 3);
		this.add(project);
		return getProjectDTOAggregate(project);
	}	
	
	/* DTO assembler business logic */
	private Project getProjectDTOAggregate(Project project){
		if (project == null)
			return null;
		Project projectDTO = project.newProjectDTO();
		List<Release> releasesDTO = getReleaseDTOList(project.getReleases());
		projectDTO.setReleases(releasesDTO);
		
		return projectDTO;
	}
	
	private Project[] getProjectDTOList(){
		List<Project> projectDTOList = new ArrayList<>();
		for(Project p: this.toCollection()){
			projectDTOList.add(p.newProjectDTO());
		}
		return projectDTOList.toArray(new Project[0]);
	}
	
	private List<Release> getReleaseDTOList(List<Release> releases){
		List<Release> releaseDTOList = new ArrayList<>();
		for(Release r: releases){
			releaseDTOList.add(r.newReleaseDTO());
		}
		return releaseDTOList;
	}
	
	private ProjectView[] getProjectViewList(){
		List<ProjectView> projectViewList = new ArrayList<>();
		for(Project p: this.toCollection()){
			projectViewList.add(new ProjectView(p));
		}
		return projectViewList.toArray(new ProjectView[0]);
	}	
	
	/* dummy validation rest: http://localhost:8080/ScrumREST/projects/test */
	@GET @Path("/test")
	@Produces("text/html")
	public String getMessage(){
		return "ProjectSprintDataService is working...";
	}
	
	/* dummy validation Rest: http://localhost:8080/ScrumREST/projects/showtext/test */
	@GET @Path("/showtext/{text}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Object getText(@PathParam("text") String text){
		String jsonData = "{message: " + text + "}";
		Response response = Response
				.status(Status.OK)
				.type(MediaType.APPLICATION_XML)
				.type(MediaType.APPLICATION_JSON)
				.entity(jsonData)
				.build();
		//return "\"" + response.getEntity().toString() + "\"";
		//return response.getEntity();
		return response;
	}
	
	/* dummy XML marshall Rest: http://localhost:8080/ScrumREST/projects/projectdata */
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