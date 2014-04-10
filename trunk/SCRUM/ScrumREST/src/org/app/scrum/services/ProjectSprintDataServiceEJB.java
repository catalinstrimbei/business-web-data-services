package org.app.scrum.services;

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
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

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
	@GET @Produces("application/xml")
	public Project[] getProjectList(){
		return getProjectDTOList();
	}	
	
	@Interceptors({ValidatorInterceptor.class})
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@GET @Path("project/{id}") @Produces("application/xml")
	public Project getProjectById(@PathParam("id") Integer id){
		Project project = super.getById(id);
		return getProjectDTOAggregate(project);
	}	
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	@GET @Path("project/{projectid}/release/{releaseid}") @Produces("application/xml")
	public Release getReleaseById(
			@PathParam("projectid") Integer projectid,
			@PathParam("releaseid") Integer releaseid){
		Release release = releaseRepository.getById(releaseid);
		return release.getReleaseDTO();
	}
	
	
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED) // read only
	@GET @Path("views") @Produces("application/xml")
	public ProjectView[] getProjectViews(){
		return getProjectViewList();
	}
	
	/* EJB calls*/
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Project addProject(Project project){
		// restore project
		// merge projectDTO with project
		logger.info(">>>>> DEBUG: saving project dto" + project);
		// save project
		project = this.add(project);
		logger.info(">>>>> DEBUG: project saved" + project);
		return getProjectDTOAggregate(project);
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
		Project projectDTO = project.getProjectDTO();
		List<Release> releasesDTO = getReleaseDTOList(project.getReleases());
		projectDTO.setReleases(releasesDTO);
		
		return projectDTO;
	}
	
	private Project[] getProjectDTOList(){
		List<Project> projectDTOList = new ArrayList<>();
		for(Project p: this.toCollection()){
			projectDTOList.add(p.getProjectDTO());
		}
		return projectDTOList.toArray(new Project[0]);
	}
	
	private List<Release> getReleaseDTOList(List<Release> releases){
		List<Release> releaseDTOList = new ArrayList<>();
		for(Release r: releases){
			releaseDTOList.add(r.getReleaseDTO());
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
	
	/* dummy validation rest */
	@GET @Path("/test")
	@Produces("text/html")
	public String getMessage(){
		return "ProjectSprintDataService is working...";
	}
}