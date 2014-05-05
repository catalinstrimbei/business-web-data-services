package org.app.scrum.rest;

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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.app.patterns.EntityRepository;
import org.app.patterns.EntityRepositoryBase;
import org.app.scrum.project.Project;
import org.app.scrum.project.ProjectFactory;
import org.app.scrum.project.Release;
import org.app.scrum.services.ProjectSprintDataService;

@Path("projects") /* http://localhost:8080/ScrumREST/projects */
@Stateless @LocalBean 
public class ProjectSprintResource extends EntityRepositoryBase<Project> 
		implements ProjectSprintDataService, Serializable{
	private static Logger logger = Logger.getLogger(ProjectSprintResource.class.getName());
	
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
    
	@GET @Path("newproject/{id}") // @POST ???
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	public Project createNewProject(@PathParam("id") Integer id){
		Project project = projectFactory.buildProiect(id, "NEW Project", 3);
		this.add(project);
		return Project.toDTOAggregate(project);
	}
	
	@GET @Path("check") // Check if resource is up ...
	public String getMessage(){
		return "ProjectSprintDataService is working...";
	}	
	
	@GET @Path("project/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@Override
	public Project getById(@PathParam("id") Object id){
		Project project = super.getById(id);
		return Project.toDTOAggregate(project);
	}
	
	@GET // default Path("projects"), Project[] ?!
	@Override
	public Collection<Project> toCollection() {
		Collection<Project> projects = super.toCollection(); // !!!
		Project[] projectArray = Project.toDTOList(projects);
		return Arrays.asList(projectArray);
	}
	
	@PUT // default Path instead of @Path("project/save") 
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public Project add(Project project) {
		project = super.add(project); // !!!
		return Project.toDTOAggregate(project);
	}
	
	@DELETE // default Path instead of @Path("project/save") 
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })	
	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW) // autonomous transaction
	@Override
	public boolean remove(Project project) {
		return super.remove(project); // !!!
	}	
}